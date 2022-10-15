package com.boot.basic.quartz.config;

import com.boot.basic.common.constant.CronConstant;
import com.boot.basic.quartz.bean.TestQuartzJobBean;
import com.boot.basic.quartz.service.ITestQuartzService;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author HTuoZhou
 */
@Configuration
public class TestQuartzConfig {

    private final ITestQuartzService testQuartzService;

    public TestQuartzConfig(ITestQuartzService testQuartzService) {
        this.testQuartzService = testQuartzService;
    }

    @Bean
    public JobDetail testJobDetail() {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("testQuartzService", testQuartzService);

        return JobBuilder.newJob(TestQuartzJobBean.class)
                .withIdentity("testJobDetail")
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger testJobTrigger() {
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(CronConstant.PER_DAY);
        return TriggerBuilder.newTrigger()
                .forJob(testJobDetail())
                .withIdentity("testJobTrigger")
                .withSchedule(cronScheduleBuilder)
                .build();
    }

}
