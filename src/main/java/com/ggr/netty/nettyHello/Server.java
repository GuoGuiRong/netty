package com.ggr.netty.nettyHello;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by GuiRunning on 2018/3/13.
 */
public class Server {
    public static void main(String[] args) {
        //创建一个服务类
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        //创建2个线程池分别负责监听端口和处理通道读写任务
        ExecutorService boss = Executors.newCachedThreadPool();
        ExecutorService work = Executors.newCachedThreadPool();

        //设置Nio工厂
        serverBootstrap.setFactory(new NioServerSocketChannelFactory(boss,work));


        //设置管道工厂
        serverBootstrap.setPipelineFactory(new ChannelPipelineFactory() {

            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                pipeline.addLast("decoder",new StringDecoder());
                pipeline.addLast("encoder",new StringEncoder());
                pipeline.addLast("hellhandler",new HelloHandler());
                return pipeline;
            }
        });

        serverBootstrap.bind(new InetSocketAddress(10010));
        System.out.println("服务器启动...");

    }
}
