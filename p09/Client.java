package com.itheima.p09;

// UDP通信案例: 多个客户端多次发，服务器多次收，client可以开多个实例（IDEA中配置）

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        // 创建一个发送数据的Socket对象
        DatagramSocket socket = new DatagramSocket();          // 随机分配端口
        // 数据发送
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("请输入待发送数据:");
            String msg = sc.nextLine();

            // 退出
            if("exit".equals(msg)) {
                System.out.println("欢迎下次光临！退出成功");
                socket.close();
                break;
            }

            byte[] bytes = msg.getBytes();
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length, InetAddress.getLocalHost(), 10086);
            // 发送数据
            socket.send(packet);
        }

    }
}
