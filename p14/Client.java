package com.itheima.p14;


import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

// TCP通信入门案例，多个客户端多发多收

public class Client {
    public static void main(String[] args) throws IOException {
        // 1. 创建一个Socket对象，构造方法中绑定服务器的IP地址和端口号
        Socket socket = new Socket(InetAddress.getLocalHost(), 10086);
        // 2. 从socket管道种得到一个字节输出流
        OutputStream os = socket.getOutputStream();

        // 3. 把低级的字节输出流包装成高级的打印流
        DataOutputStream dos = new DataOutputStream(os);

        Scanner sc = new Scanner(System.in);
        // 4. 写数据
        while(true) {
            System.out.println("请输入待发送数据：");
            String line = sc.nextLine();
            if("exit".equals(line)) {
                System.out.println("退出成功！");
                break;
            }
            dos.writeUTF(line);
            dos.flush();            // 刷新, 防止数据滞留：表示立即发送
        }

        dos.close();             // 同时关闭了os
        socket.close();
    }

}

