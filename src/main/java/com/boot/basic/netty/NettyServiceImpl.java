package com.boot.basic.netty;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author HTuoZhou
 */
@Service
@Slf4j
public class NettyServiceImpl implements INettyService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void test() {

    }
}
