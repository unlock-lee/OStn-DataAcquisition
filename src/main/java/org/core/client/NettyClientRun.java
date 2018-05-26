package org.core.client;

/**
 * 客户端主入口
 */
public class NettyClientRun {

    public static void main(String[] args) throws Exception {
        NettyClientBootstrap clientBootstrap = new NettyClientBootstrap(8021, "127.0.0.1");
        clientBootstrap.run();

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
