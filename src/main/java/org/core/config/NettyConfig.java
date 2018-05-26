package org.core.config;

import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 存储整个工程的全局配置
 * Created by ChangLee on 2018/5/22.
 */
public class NettyConfig {

    //存储每一个客户端连接过来的Channel对象
    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

}
