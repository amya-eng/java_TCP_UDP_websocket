package com.itheima.p16;

import com.itheima.p16.ServerReaderThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

// TCP通信：B/S架构(HTTP基于TCP传输)

public class Server {
    public static void main(String[] args) throws IOException
    {
        System.out.println("---------服务端启动成功-------");
        // 1. 创建一个ServerSocket对象，构造方法中指定端口号
        ServerSocket ss = new ServerSocket(10086);        // 默认ip地址为本机

        while (true) {
            // 2. 调用accept方法，阻塞等待客户端的连接请求，并返回一个Socket对象
            Socket socket = ss.accept();
            System.out.println("一个客户端已连接: " + socket.getRemoteSocketAddress());

            new ServerReaderThread(socket).start();
        }


    }
}

/**
 * 运行之后连接一次，终端打印多条连接信息如下：
 * ---------服务端启动成功-------
 * 一个客户端已连接: /127.0.0.1:36966
 * 一个客户端已连接: /127.0.0.1:36967
 * 一个客户端已连接: /127.0.0.1:36974
 * 一个客户端已连接: /127.0.0.1:36980
 * 一个客户端已连接: /127.0.0.1:36981
 * 一个客户端已连接: /127.0.0.1:36982
 * 一个客户端已连接: /127.0.0.1:36983
 *
 * 原因：
 * 对于 HTTP 1.0，默认是 “短连接”：一次请求 - 响应后就关闭 TCP 连接。
 * 如果浏览器需要发送多个请求（如主请求 + 图标请求），会分别建立多个 TCP 连接，每个连接对应一个请求，
 * 因此服务器会记录多次连接。
 */


