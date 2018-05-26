package org.core.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

/**
 * 客户端主入口
 */
public class NettyClientRun {

    private static EventLoopGroup loop = new NioEventLoopGroup();

    public static void main(String[] args) throws Exception {
        NettyClientBootstrap clientBootstrap = new NettyClientBootstrap(8021, "127.0.0.1");
        clientBootstrap.run(new Bootstrap(),loop);

        /*NettyClientBootstrap clientBootstrap1 = new NettyClientBootstrap(8022, "127.0.0.1");
        clientBootstrap1.run();

        NettyClientBootstrap clientBootstrap2 = new NettyClientBootstrap(8023, "127.0.0.1");
        clientBootstrap2.run();

        NettyClientBootstrap clientBootstrap3 = new NettyClientBootstrap(8024, "127.0.0.1");
        clientBootstrap3.run();

        NettyClientBootstrap clientBootstrap1 = new NettyClientBootstrap(8021, "127.0.0.1");
        clientBootstrap1.run();*/
    }

}
