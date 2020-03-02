package com.github.vanlla.job.listener;

import com.github.vanlla.job.entity.QuartzJobEntity;
import com.github.vanlla.job.entity.QuartzJobLogEntity;
import com.github.vanlla.job.service.IQuartzJobLogService;
import com.github.vanlla.job.service.IQuartzJobService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务监听器
 *
 * @author Vanlla
 * @since 1.0
 */
@Component
public class DefaultJobListener implements JobListener {

    @Autowired
    private IQuartzJobLogService quartzJobLogService;

    @Autowired
    private IQuartzJobService quartzJobService;

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultJobListener.class);

    @Override
    public String getName() {
        return "DefaultJobListener";
    }

    /**
     * 任务执行之前执行
     *
     * @param jobExecutionContext
     */
    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
        Serializable jobId = (Serializable) jobExecutionContext.getJobDetail().getJobDataMap().get("pk");
        this.updateJobStatus(jobId, 1);
    }

    /**
     * 任务取消执行
     *
     * @param jobExecutionContext
     */
    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {

    }

    /**
     * 任务执行完之后执行
     *
     * @param jobExecutionContext
     * @param e
     */
    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {

        Serializable jobId = (Serializable) jobExecutionContext.getJobDetail().getJobDataMap().get("pk");
        Integer result = e == null ? 1 : 0;
        QuartzJobLogEntity log = new QuartzJobLogEntity();
        log.setStartTime(jobExecutionContext.getFireTime());
        log.setCompleteTime(new Date());
        log.setJobId((Integer) jobId);
        log.setResult(result);
        log.setException(e == null ? null : jobExecutionContext.getJobDetail().getJobDataMap().getString("exception"));
        this.updateJobStatus(jobId, 0);
        //正常执行暂时不开启日志
        if (e != null) {
            quartzJobLogService.save(log);
        }
    }

    /**
     * 更新任务执行状态
     *
     * @param jobId
     * @param status
     */
    private void updateJobStatus(Serializable jobId, Integer status) {
        QuartzJobEntity job = new QuartzJobEntity();
        job.setId((Integer) jobId);
        job.setStatus(status);
        quartzJobService.saveOrUpdate(job);
    }


}
