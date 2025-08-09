package com.itheima.p07;

// UDP通信快速入门案例: 客户端发一次，服务端收一次，然后两者均关闭。

import java.io.IOException;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        // 创建一个发送数据的Socket对象
        DatagramSocket socket = new DatagramSocket();          // 随机分配端口
        // 创建一个数据发送
        byte[] bytes = "hello,I'm a simple UDP...".getBytes();
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length, InetAddress.getLocalHost(), 10086);

        // 发送数据
        socket.send(packet);

        //释放资源
        System.out.println("发送完毕...");
        socket.close();
    }
}
