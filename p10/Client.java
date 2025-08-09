package com.itheima.p10;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

// TCP通信快速入门案例，实现一发一收然后关闭

public class Client {
    public static void main(String[] args) throws IOException {
        // 1. 创建一个Socket对象，构造方法中绑定服务器的IP地址和端口号
        Socket socket = new Socket(InetAddress.getLocalHost(), 10086);
        // 2. 从socket管道种得到一个字节输出流
        OutputStream os = socket.getOutputStream();

        // 3. 把低级的字节输出流包装成高级的打印流
        DataOutputStream dos = new DataOutputStream(os);

        // 4. 写数据
        dos.writeUTF("你好吗？");
        dos.close();             // 同时关闭了os
        socket.close();
    }

}
