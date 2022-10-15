package com.boot.basic.quartz.service.impl;

import com.boot.basic.quartz.service.ITestQuartzService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author HTuoZhou
 */
@Service
@Slf4j
public class TestQuartzServiceImpl implements ITestQuartzService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    @SneakyThrows(Exception.class)
    public void test() {
        Thread.sleep(3 * 1000);
    }
}
