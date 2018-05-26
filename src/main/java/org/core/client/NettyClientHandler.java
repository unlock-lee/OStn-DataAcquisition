package org.core.client;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoop;
import io.netty.channel.SimpleChannelInboundHandler;
import org.core.config.NettyConfig;
import org.core.kafka.util.ProducerUtil;

import java.util.concurrent.TimeUnit;

public class NettyClientHandler extends SimpleChannelInboundHandler<Object> {

    private NettyClientBootstrap client;

    public NettyClientHandler(NettyClientBootstrap client) {
        this.client = client;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        //获取到的message信息直接丢给中间件去处理
        //Netty在这里只负责信息的收发 不进行业务处理
        ProducerUtil.producerSend(o);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //当有客户端连接时则将通道加入
        NettyConfig.channels.add(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //加入重连监听后不在移除通道
        //NettyConfig.channels.remove(ctx.channel());
        //设置30秒进行一次重连
        final EventLoop eventLoop = ctx.channel().eventLoop();
        eventLoop.schedule(new Runnable() {
            @Override
            public void run() {
                client.run(new Bootstrap(), eventLoop);
            }
        }, 30L, TimeUnit.SECONDS);
        super.channelInactive(ctx);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}