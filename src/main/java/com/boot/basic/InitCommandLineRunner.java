package com.boot.basic;

import com.boot.basic.netty.INettyService;
import com.boot.basic.netty.NettyServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * @author HTuoZhou
 */
@Component
@Slf4j
public class InitCommandLineRunner implements CommandLineRunner {

    private final Environment environment;
    private final INettyService nettyService;
    private final ThreadPoolTaskExecutor threadPoolTaskExecutor;

    public InitCommandLineRunner(Environment environment, INettyService nettyService, ThreadPoolTaskExecutor threadPoolTaskExecutor) {
        this.environment = environment;
        this.nettyService = nettyService;
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("项目启动初始化开始");
        // TODO
        log.info("项目启动初始化结束");

        log.info("项目启动线程执行netty服务");
        threadPoolTaskExecutor.execute(() -> {
            new NettyServer(environment, nettyService).run();
        });
    }

}
