package com.ggr.netty.nettyHello;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by GuiRunning on 2018/3/13.
 */
public class Client {
    public static void main(String[] args) {
        //客户端服务类
        ClientBootstrap bootstrap = new ClientBootstrap();

        ExecutorService boss = Executors.newCachedThreadPool();
        ExecutorService work = Executors.newCachedThreadPool();

        bootstrap.setFactory(new NioClientSocketChannelFactory(boss,work));
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                pipeline.addLast("decoder",new org.jboss.netty.handler.codec.string.StringDecoder());
                pipeline.addLast("encoder",new org.jboss.netty.handler.codec.string.StringEncoder());
                pipeline.addLast("hihandler",new HiHandler());
                return pipeline;
            }
        });

        ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress("192.168.213.1",10010));
        System.out.println("Client start...");
        Channel channel = channelFuture.getChannel();
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("请输入");
            if(channel.isWritable()) {
                channel.write(scanner.next());
            }
        }
    }
}
