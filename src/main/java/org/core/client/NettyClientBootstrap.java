package org.core.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClientBootstrap {
    private int port;
    private String host;

    public NettyClientBootstrap(int port, String host) throws Exception {
        this.host = host;
        this.port = port;
    }

    private Bootstrap start(Bootstrap bootstrap, EventLoopGroup eventLoopGroup) {
        if (bootstrap != null) {
            final NettyClientHandler handler = new NettyClientHandler(this);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.option(ChannelOption.TCP_NODELAY, true);
            bootstrap.group(eventLoopGroup);
            bootstrap.remoteAddress(this.host, this.port);
            bootstrap.handler(new MyChannelInitializer(handler));
            bootstrap.connect(this.host, this.port).addListener(new ConnectionListener(this));
        }
        return bootstrap;
    }

    public Bootstrap run(Bootstrap bootstrap, EventLoopGroup evetnLoopGroup) {
        Bootstrap runBootstrap = start(bootstrap, evetnLoopGroup);
        return runBootstrap;
    }

}