package com.github.vanlla.job.job;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.vanlla.core.util.HolidayUtils;
import com.github.vanlla.core.util.SpringContextUtil;
import com.github.vanlla.job.entity.QyWechatRobotEntity;
import com.github.vanlla.job.service.IQyWechatRobotService;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.List;

/**
 * 工作日考勤提醒机器人
 *
 * @author Vanlla
 * @since 1.0
 */
public class QyWechatRobot extends AbstractJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(QyWechatRobot.class);

    private IQyWechatRobotService qyWechatRobotService = SpringContextUtil.getBean("qyWechatRobotService", IQyWechatRobotService.class);


    public static void sendMessage(String url, String content, int count) throws Exception {
        String result = HttpUtil.post(url, content);
        //是否调用成功
        boolean isSuccess = ObjectUtil.isNotEmpty(result) && 0 == JSON.parseObject(result).getInteger("errcode");
        if (isSuccess) {
            return;
        } else if (count < 3) {
            sendMessage(url, content, count + 1);
        } else {
            throw new Exception("接口调用失败");
        }
    }


    @Override
    public void executeInternal(JobExecutionContext jobExecutionContext) throws Exception {

        List<QyWechatRobotEntity> robots = qyWechatRobotService.list(new QueryWrapper<QyWechatRobotEntity>().eq("enable", 1));
        if (ObjectUtil.isEmpty(robots)) {
            LOGGER.debug("没有需要提醒的机器人");
            return;
        }

        for (QyWechatRobotEntity robot : robots) {
            //机器人设置的时间和定时任务触发的时间
            if (DateUtil.formatTime(jobExecutionContext.getScheduledFireTime()).equals(DateUtil.formatTime(robot.getExecuteTime())) && HolidayUtils.isWorkDay(Calendar.getInstance().getTime())) {
                sendMessage(robot.getUrl(), robot.getContent(), 1);
            }
        }
    }

}
