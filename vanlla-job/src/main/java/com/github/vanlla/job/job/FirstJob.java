package com.github.vanlla.job.job;


import org.quartz.JobExecutionContext;

import java.io.FileNotFoundException;


/**
 * First Job
 *
 * @author Vanlla
 * @since 0.1
 */
public class FirstJob extends AbstractJob {

    @Override
    public void executeInternal(JobExecutionContext jobExecutionContext) throws Exception {
        System.out.println("this is first job");
        throw new FileNotFoundException("firstFile not found");
    }
}
