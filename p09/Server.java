package com.itheima.p09;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

// UDP通信案例: 多个客户端多次发，服务器多次收

public class Server {
    public static void main(String[] args) throws IOException {
        // 创建服务端对象
        DatagramSocket socket = new DatagramSocket(10086);

        // 创建一个数据包对象，用于接收数据
        byte[] buffer = new byte[1024 * 64];        // 1包< 64KB
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        while (true) {
            //接受数据
            socket.receive(packet);
            int len = packet.getLength();
            String rs = new String(buffer, 0, len);
            System.out.println(rs);

            // 获取客户端的ip地址和端口号
            System.out.println(packet.getAddress().getHostAddress());
            System.out.println(packet.getPort());
            System.out.println("--------------------------");
        }

        // 服务端不关闭socket
    }
}
