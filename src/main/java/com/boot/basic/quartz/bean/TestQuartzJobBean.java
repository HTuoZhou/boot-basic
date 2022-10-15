package com.boot.basic.quartz.bean;

import com.boot.basic.common.constant.LocalDateTimePatternConstant;
import com.boot.basic.quartz.service.ITestQuartzService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author HTuoZhou
 */
@Slf4j
public class TestQuartzJobBean extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("定时任务开始执行，执行时间：{}", DateTimeFormatter.ofPattern(LocalDateTimePatternConstant.NORM_DATETIME_PATTERN).format(LocalDateTime.now()));

        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        ITestQuartzService testQuartzService = (ITestQuartzService) jobDataMap.get("testQuartzService");
        testQuartzService.test();

        log.info("定时任务结束执行，执行时间：{}", DateTimeFormatter.ofPattern(LocalDateTimePatternConstant.NORM_DATETIME_PATTERN).format(LocalDateTime.now()));
    }
}
