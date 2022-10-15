package com.boot.basic.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Objects;

/**
 * @author HTuoZhou
 */
@Slf4j
public class NettyServerHandler extends SimpleChannelInboundHandler<NettyMessage> {

    private INettyService nettyService;

    public NettyServerHandler() {

    }

    public NettyServerHandler(INettyService nettyService) {
        this.nettyService = nettyService;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("客户端【{}】加入连接", ctx.channel().remoteAddress());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("客户端【{}】断开连接", ctx.channel().remoteAddress());

        Map<String, NettyClientManager.NettyClient> map = NettyClientManager.getInstance().getMap();
        for (String key : map.keySet()) {
            NettyClientManager.NettyClient client = map.get(key);
            if (Objects.equals(client.getCtx(), ctx)) {
                log.info("移除客户端【{}】信息，移除之前：{}", ctx.channel().remoteAddress(), map);
                map.remove(key);
                log.info("移除客户端【{}】信息，移除之后：{}", ctx.channel().remoteAddress(), map);
            }
        }
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            if (idleStateEvent.state().equals(IdleState.READER_IDLE)) {
                log.info("服务端【{}】读空闲，通道关闭", ctx.channel().remoteAddress());
                ctx.channel().close();
            }
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, NettyMessage msg) throws Exception {
        log.info("读取客户端【{}】消息：{}", ctx.channel().remoteAddress(), msg);

        String clientInstruction = msg.getClientInstruction();

        switch (clientInstruction) {
            case NettyInstruction.LOGIN_INSTRUCTION:
                login(ctx, msg);
                break;
            case NettyInstruction.HEART_INSTRUCTION:
                heart(ctx, msg);
                break;
            default:
                nettyService.test();
                break;
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("服务端【{}】发生异常：{}", ctx.channel().remoteAddress(), cause);
    }

    public void login(ChannelHandlerContext ctx, NettyMessage msg) {
        log.info("读取客户端【{}】登录信息：{}", ctx.channel().remoteAddress(), msg);

        String clientUniqueId = msg.getClientUniqueId();
        String clientIp = ctx.channel().remoteAddress().toString();

        Map<String, NettyClientManager.NettyClient> map = NettyClientManager.getInstance().getMap();
        log.info("添加客户端【{}】信息，添加之前：{}", ctx.channel().remoteAddress(), map);

        // 客户端对象信息
        NettyClientManager.NettyClient client = new NettyClientManager.NettyClient();
        client.setClientUniqueId(clientUniqueId)
                .setClientIp(clientIp)
                .setCtx(ctx)
                .setNettyServerHandler(this);
        map.put(clientUniqueId, client);
        log.info("添加客户端【{}】信息，添加之后：{}", ctx.channel().remoteAddress(), map);

        NettyMessage sendMsg = new NettyMessage();
        sendMsg.setClientInstruction(NettyInstruction.LOGIN_INSTRUCTION)
                .setClientUniqueId(clientUniqueId);

        ctx.writeAndFlush(sendMsg);
        log.info("发送登录指令给客户端【{}】：{}", ctx.channel().remoteAddress(), sendMsg);
    }

    public void heart(ChannelHandlerContext ctx, NettyMessage msg) {
        log.info("读取客户端【{}】心跳信息：{}", ctx.channel().remoteAddress(), msg);

        String clientUniqueId = msg.getClientUniqueId();

        NettyMessage sendMsg = new NettyMessage();
        sendMsg.setClientInstruction(NettyInstruction.LOGIN_INSTRUCTION)
                .setClientUniqueId(clientUniqueId);

        ctx.writeAndFlush(sendMsg);
        log.info("发送心跳指令给客户端【{}】：{}", ctx.channel().remoteAddress(), sendMsg);
    }

}
