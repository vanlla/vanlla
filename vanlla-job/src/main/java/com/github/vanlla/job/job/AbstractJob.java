package com.github.vanlla.job.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * 所有定时任务继承该类
 *
 * @author Vanlla
 * @since 0.1
 */
public abstract class AbstractJob implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            this.executeInternal(jobExecutionContext);
            jobExecutionContext.getJobDetail().getJobDataMap().remove("exception");
        } catch (Exception e) {
            LOGGER.error(jobExecutionContext.getJobDetail().getKey().getName() + "，运行出现异常，请查看定时任务日志", e);
            jobExecutionContext.getJobDetail().getJobDataMap().put("exception", getExcetionDetail(e));
        }
    }

    public abstract void executeInternal(JobExecutionContext jobExecutionContext) throws Exception;

    private String getExcetionDetail(Throwable e) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        e.printStackTrace(new PrintStream(outputStream));
        return outputStream.toString();
    }
}
