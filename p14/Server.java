package com.itheima.p14;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

// TCP通信,多个客户端多发多收

public class Server {
    public static void main(String[] args) throws IOException
    {
        System.out.println("---------服务端启动成功-------");
        // 1. 创建一个ServerSocket对象，构造方法中指定端口号
        ServerSocket ss = new ServerSocket(10086);

        while (true) {
            // 2. 调用accept方法，阻塞等待客户端的连接请求，并返回一个Socket对象
            Socket socket = ss.accept();
            System.out.println("一个客户端已连接: " + socket.getRemoteSocketAddress());

            new ServerReaderThread(socket).start();
        }


    }
}


