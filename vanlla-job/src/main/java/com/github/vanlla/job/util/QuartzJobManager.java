package com.github.vanlla.job.util;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.vanlla.core.util.SpringContextUtil;
import com.github.vanlla.job.entity.QuartzJobEntity;
import com.github.vanlla.job.listener.DefaultJobListener;
import com.github.vanlla.job.service.IQuartzJobService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * 任务调度
 *
 * @author Vanlla
 * @since 1.0
 */
@Component
public class QuartzJobManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuartzJobManager.class);

    public static final String DEFAULT_JOB_GROUP = "default_job_group";
    public static final String DEFAULT_TRIGGER_GROUP = "default_trigger_group";

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private IQuartzJobService quartzJobService;

    @Autowired
    private DefaultJobListener defaultJobListener;


    private void addAllJob() throws ClassNotFoundException, SchedulerException {
        List<QuartzJobEntity> jobList = quartzJobService.list(new QueryWrapper<QuartzJobEntity>().eq("enable", 1));

        if (ObjectUtil.isEmpty(jobList)) {
            LOGGER.debug("数据库不存在可用的定时任务");
            return;
        }

        for (QuartzJobEntity job : jobList) {
            this.addJob(job);
        }

        LOGGER.debug("初始化定时任务结束");
    }


    /**
     * 添加定时任务
     *
     * @param quartzJob
     * @throws ClassNotFoundException
     * @throws SchedulerException
     */
    public void addJob(QuartzJobEntity quartzJob) throws ClassNotFoundException, SchedulerException {
        JobKey jobKey = new JobKey(quartzJob.getName(), DEFAULT_JOB_GROUP);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail != null) {
            LOGGER.debug("调度器已存在该定时任务，更新该任务");
            this.modifyJob(quartzJob);
            return;
        }


        LOGGER.debug("当前机器运行：" + SpringContextUtil.getHostAndPort());
        if (!isAllow(quartzJob.getIp())) {
            LOGGER.debug(quartzJob.getName() + "绑定的IP:HOST,与当前实例不一致，不进行添加");
            return;
        }
        //添加定时任务
        this.addCronTriggerJob(quartzJob.getName(), quartzJob.getCronExpression(), quartzJob.getClassName(), quartzJob.getId());

    }

    /**
     * 当hostAndIp不为空并且和当前实例运行的HostAndIP不一致，返回false
     *
     * @param hostAndPort
     * @return
     */
    private boolean isAllow(String hostAndPort) {

        //为空表示不限制ip和端口
        if (StrUtil.isBlank(hostAndPort)) {
            return true;
        }

        String[] hostAndPorts = StrUtil.split(hostAndPort, ":");
        boolean allowPort = hostAndPorts.length == 2 && StrUtil.equals(SpringContextUtil.getPort() + "", hostAndPorts[1]);
        boolean allowHost = CollectionUtil.contains(NetUtil.localIpv4s(), hostAndPorts[0]);
        if (allowHost && allowPort) {
            return true;
        }
        return false;
    }

    /**
     * 添加定时任务
     *
     * @param jobName
     * @param cronExpression
     * @param className
     * @throws ParseException
     * @throws ClassNotFoundException
     * @throws SchedulerException
     */
    private void addCronTriggerJob(String jobName, String cronExpression, String className, Serializable id) throws ClassNotFoundException, SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob((Class<? extends Job>) Class.forName(className)).withIdentity(jobName, DEFAULT_JOB_GROUP).build();
        jobDetail.getJobDataMap().put("pk", id);
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(jobName, DEFAULT_TRIGGER_GROUP)
                .withSchedule(cronScheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, cronTrigger);
        //添加job执行监听器
        scheduler.getListenerManager().addJobListener(defaultJobListener);
        //启动
        this.start();

    }


    /**
     * 修改定时任务
     *
     * @param quartzJob
     * @return
     * @throws SchedulerException
     */
    public boolean modifyJob(QuartzJobEntity quartzJob) throws SchedulerException {
        //不允许执行的时候,删除定时任务
        if (!isAllow(quartzJob.getIp())) {
            this.deleteJob(quartzJob.getName());
            return true;
        }
        return this.modifyJob(quartzJob.getName(), quartzJob.getCronExpression());
    }

    /**
     * 修改任务
     *
     * @param name
     * @param cronExpression
     * @return
     * @throws SchedulerException
     */
    private boolean modifyJob(String name, String cronExpression) throws SchedulerException {
        JobKey jobKey = new JobKey(name, DEFAULT_JOB_GROUP);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null) {
            LOGGER.debug("调度器不存在该定时任务");
            return false;
        }
        Date date = null;
        TriggerKey triggerKey = new TriggerKey(name, DEFAULT_TRIGGER_GROUP);
        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        String oldTime = cronTrigger.getCronExpression();
        if (!oldTime.equalsIgnoreCase(cronExpression)) {
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(name, DEFAULT_TRIGGER_GROUP)
                    .withSchedule(cronScheduleBuilder).build();
            date = scheduler.rescheduleJob(triggerKey, trigger);
        }
        return date != null;
    }

    /**
     * 删除某个任务
     *
     * @param name 名称
     */
    public void deleteJob(String name) throws SchedulerException {
        JobKey jobKey = new JobKey(name, DEFAULT_JOB_GROUP);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail != null) {
            scheduler.deleteJob(jobKey);
        }
    }

    /**
     * 暂停所有任务
     *
     * @throws SchedulerException
     */
    public void pauseAllJob() throws SchedulerException {
        scheduler.pauseAll();
    }

    /**
     * 恢复所有任务
     *
     * @throws SchedulerException
     */
    public void resumeAllJob() throws SchedulerException {
        scheduler.resumeAll();
    }

    /**
     * 暂停某个任务
     *
     * @param name
     */
    public void pauseJob(String name) throws SchedulerException {
        JobKey jobKey = new JobKey(name, DEFAULT_JOB_GROUP);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail != null) {
            scheduler.pauseJob(jobKey);
        }
    }

    /**
     * 恢复某个任务
     *
     * @param name
     */
    public void resumeJob(String name) throws SchedulerException {
        JobKey jobKey = new JobKey(name, DEFAULT_JOB_GROUP);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail != null) {
            scheduler.resumeJob(jobKey);
        }
    }


    /**
     * 启动
     *
     * @throws SchedulerException
     */
    public void start() throws SchedulerException {
        if (!scheduler.isShutdown()) {
            scheduler.start();
        }
    }

    /**
     * 应用启动添加定时任务
     *
     * @param contextRefreshedEvent
     */
    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //添加定时任务
        LOGGER.debug("..................................contextRefreshedEvent.................................. start");
        try {
            this.addAllJob();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        LOGGER.debug("..................................contextRefreshedEvent.................................. end");
    }

}
