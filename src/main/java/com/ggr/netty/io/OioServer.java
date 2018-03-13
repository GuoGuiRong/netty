package com.ggr.netty.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 传统的IO
 */
public class OioServer {

    public static void main(String[] args) {

        ServerSocket serverSocket = null;

        try {
            //创建一个Socket服务，监听10000端口
            serverSocket = new ServerSocket(10000);
            System.out.println("服务器启动...");
            while(true){
               final Socket socket = serverSocket.accept();
               System.out.println("来了一个新的客户端连接");
               handler(socket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 服务handler
     */
    public static void handler(Socket socket){

        byte[] buffer = new byte[1024];
        InputStream inputStream = null;
        try {
            inputStream = socket.getInputStream();
            while(true){
                int read = inputStream.read(buffer);
                if(read!=-1){
                    System.out.println(new String(buffer,0,read));
                }
            }
        } catch (IOException e) {
            System.out.println("获取Socket的输入流失败！");
            e.printStackTrace();
        }
    }
}
