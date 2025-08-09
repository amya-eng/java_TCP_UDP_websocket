package com.itheima.p10;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

// TCP通信快速入门案例，实现一发一收然后关闭
public class Server {
    public static void main(String[] args) throws IOException
    {
        System.out.println("---------服务端启动成功-------");
        // 1. 创建一个ServerSocket对象，构造方法中指定端口号
        ServerSocket ss = new ServerSocket(10086);

        // 2. 调用accept方法，阻塞等待客户端的连接请求，并返回一个Socket对象
        Socket socket = ss.accept();           // 服务端拿到通信管道

        // 3. 从socket管道中得到一个字节输入流
        InputStream is = socket.getInputStream();

        // 4. 把原始的字节输入流包装成数据输入流
        DataInputStream dis = new DataInputStream(is);

        // 阻塞等待，读取数据
        System.out.println(dis.readUTF());         // 与客户端一一对应
        System.out.println(socket.getRemoteSocketAddress());

        //关闭资源
        dis.close();
        socket.close();
    }
}
