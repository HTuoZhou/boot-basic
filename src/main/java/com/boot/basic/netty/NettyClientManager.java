package com.boot.basic.netty;

import io.netty.channel.ChannelHandlerContext;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author HTuoZhou
 */
@Data
public class NettyClientManager {

    private NettyClientManager() {
    }

    private static volatile NettyClientManager instance;

    public Map<String, NettyClient> map = new ConcurrentHashMap<>();

    public static NettyClientManager getInstance() {
        if (instance == null) {
            synchronized (NettyClientManager.class) {
                if (instance == null) {
                    instance = new NettyClientManager();
                }
            }
        }
        return instance;
    }

    @Data
    @Accessors(chain = true)
    public static class NettyClient {
        // 设备唯一ID
        private String clientUniqueId;
        // 设备IP
        private String clientIp;
        // 设备上下文
        private ChannelHandlerContext ctx;
        // 设备处理器
        private NettyServerHandler nettyServerHandler;
    }

}
