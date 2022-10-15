package com.boot.basic.netty;

import com.boot.basic.common.base.SystemProperties;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author HTuoZhou
 */
@Slf4j
public class NettyServer {

    private Environment environment;
    private INettyService nettyService;

    public NettyServer() {
    }

    public NettyServer(Environment environment, INettyService nettyService) {
        this.environment = environment;
        this.nettyService = nettyService;
    }

    /**
     * 编写run方法，处理客户端请求
     */
    public void run() {
        String nettyPort = Objects.requireNonNull(environment.getProperty(SystemProperties.NETTY_PORT));

        // 创建BossGroup和WorkGroup
        // 说明
        // 1、创建两个线程组bossGroup和workerGroup
        // 2、bossGroup只是处理连接请求，真正和客户端的业务处理，会交给workerGroup完成
        // 3、两个都是无限循环
        // 4、bossGroup和workerGroup含有的子线程(NioEventLoop)个数默认是CPU核数*2
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // 创建服务器端的启动对象，配置参数
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            // 使用链式编程来进行设置
            // 设置两个线程组
            serverBootstrap.group(bossGroup, workerGroup)
                    // 使用NioSocketChannel，作为服务器的通道实现
                    .channel(NioServerSocketChannel.class)
                    // 设置线程队列的等待连接个数
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // 设置保持活动连接状态
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    // .handler() 给我们的bossGroup->NioEventLoop对应的管道设置处理器
                    //给我们的workerGroup->NioEventLoop对应的管道设置处理器
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        // 给pipeline设置处理器
                        @Override
                        protected void initChannel(SocketChannel socketChannel) {
                            socketChannel.pipeline().addLast(new NettyEncoder());
                            socketChannel.pipeline().addLast(new NettyDecoder());
                            socketChannel.pipeline().addLast(new IdleStateHandler(180, 0, 0, TimeUnit.SECONDS));
                            socketChannel.pipeline().addLast(new NettyServerHandler(nettyService));
                        }
                    });

            // 绑定端口nettyPort并且同步
            ChannelFuture cf = serverBootstrap.bind(Integer.parseInt(nettyPort)).sync();

            log.info("服务端准备就绪,绑定端口【{}】", nettyPort);

            // 给cf注册监听器 监听我们关心的事件
            cf.addListener((ChannelFutureListener) future -> {
                if (cf.isSuccess()) {
                    log.info("服务端绑定端口【{}】成功", nettyPort);
                }
            });

            // 对关闭通道进行监听
            cf.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
