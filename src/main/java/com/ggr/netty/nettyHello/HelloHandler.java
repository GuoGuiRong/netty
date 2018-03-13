package com.ggr.netty.nettyHello;

import org.jboss.netty.channel.*;

/**
 * Created by GuiRunning on 2018/3/13.
 */
public class HelloHandler extends SimpleChannelHandler {

    /**
     * 接受消息
     * @param ctx
     * @param e
     * @throws Exception
     */
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        String s = (String)e.getMessage();
        System.out.println(s);
       //回写数据
        if(ctx.getChannel().isWritable()) {
            ctx.getChannel().write("hi");
            super.messageReceived(ctx, e);
        }
    }

    /**
     * 捕获异常
     * @param ctx
     * @param e
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        System.out.println("exceptionCaught");
        ctx.getChannel().close();
        System.out.println("连接关闭");
        //super.exceptionCaught(ctx, e);
    }

    /**
     * 新的连接进来
     * @param ctx
     * @param e
     * @throws Exception
     */
    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelConnected");
        super.channelConnected(ctx, e);
    }

    /**
     * 连接已经建立后，通道断开连接
     * @param ctx
     * @param e
     * @throws Exception
     */
    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelDisconnected");
        super.channelDisconnected(ctx, e);
    }

    /**
     * 连接断开(包含了连接不上的情况)
     * @param ctx
     * @param e
     * @throws Exception
     */
    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelClosed");
        super.channelClosed(ctx, e);
    }
}
