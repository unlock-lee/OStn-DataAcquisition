package org.core.client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.core.server.EchoServerHandler;

/**
 * 初始化通道参数
 */
public class MyChannelInitializer extends ChannelInitializer {

    private NettyClientHandler handler;

    public MyChannelInitializer(NettyClientHandler handler){
        this.handler = handler;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ch.pipeline().addLast("http-codec", new HttpServerCodec());
        ch.pipeline().addLast("aggregator", new HttpObjectAggregator(65536));
        ch.pipeline().addLast(new StringDecoder(), new StringEncoder(), handler);
        ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
        ch.pipeline().addLast("handler", new EchoServerHandler());
    }
}
