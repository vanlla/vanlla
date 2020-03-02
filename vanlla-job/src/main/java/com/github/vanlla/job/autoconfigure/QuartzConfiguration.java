package com.github.vanlla.job.autoconfigure;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 配置
 *
 * @author Vanlla
 * @since 1.0
 */
@Configuration
public class QuartzConfiguration {


    /**
     * 初始化注入Scheduler
     *
     * @return
     * @throws SchedulerException
     */
    @Bean
    public Scheduler scheduler() throws SchedulerException {
        return new StdSchedulerFactory().getScheduler();
    }


}
