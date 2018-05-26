package org.core.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoop;

import java.util.concurrent.TimeUnit;

public class ConnectionListener implements ChannelFutureListener {
    private NettyClientBootstrap client;
    public ConnectionListener(NettyClientBootstrap client) {
        this.client = client;
    }

    @Override
    public void operationComplete(ChannelFuture channelFuture) throws Exception {
        if (!channelFuture.isSuccess()) {
            System.out.println("Reconnect");
            final EventLoop loop = channelFuture.channel().eventLoop();
            loop.schedule(new Runnable() {
                @Override
                public void run() {
                   client.run(new Bootstrap(), loop);
                }
            }, 1L, TimeUnit.SECONDS);
        }
    }
}