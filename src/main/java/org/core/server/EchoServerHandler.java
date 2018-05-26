package org.core.server;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.UnsupportedEncodingException;

public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    /*
     * channelAction
     *
     * channel 通道 action 活跃的
     *
     * 当客户端主动链接服务端的链接后，这个通道就是活跃的了。也就是客户端与服务端建立了通信通道并且可以传输数据
     *
     */
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().localAddress().toString() + " 通道已激活！");
    }

    /*
     * channelInactive
     *
     * channel 通道 Inactive 不活跃的
     *
     * 当客户端主动断开服务端的链接后，这个通道就是不活跃的。也就是说客户端与服务端的关闭了通信通道并且不可以传输数据
     *
     */
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().localAddress().toString() + " 通道不活跃！");
        // 关闭流

    }

    /**
     * @param buf
     * @return
     * @author Taowd
     * TODO  此处用来处理收到的数据中含有中文的时  出现乱码的问题
     * 2017年8月31日 下午7:57:28
     */
    private String getMessage(ByteBuf buf) {
        byte[] con = new byte[buf.readableBytes()];
        buf.readBytes(con);
        try {
            return new String(con, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 功能：读取服务器发送过来的信息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 第一种：接收字符串时的处理
        ByteBuf buf = (ByteBuf) msg;
        String rev = getMessage(buf);
        System.out.println("客户端收到服务器数据:" + rev);
        System.out.println("客户端收到服务器数据IP："+ ctx.channel().localAddress());
    }

    /**
     * 功能：读取完毕客户端发送过来的数据之后的操作
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务端接收数据完毕..");
        // 第一种方法：写一个空的buf，并刷新写出区域。完成后关闭sock channel连接。
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER);
        // ctx.flush();
        // ctx.flush(); //
        // 第二种方法：在client端关闭channel连接，这样的话，会触发两次channelReadComplete方法。
        // ctx.flush().close().sync(); // 第三种：改成这种写法也可以，但是这中写法，没有第一种方法的好。
    }

    /**
     * 功能：服务端发生异常的操作
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        System.out.println("异常信息：\r\n" + cause.getMessage());
    }
}
