package com.itheima.p15;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ClientReaderThread extends  Thread{
    private Socket socket;              // 接收客户端管道
    public ClientReaderThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream is = socket.getInputStream();
            DataInputStream dis = new DataInputStream(is);
            while(true) {
                try {
                    String msg = dis.readUTF();
                    System.out.println(msg);
                    System.out.println("-------------------------");

                } catch (IOException e) {
                    System.out.println("自己下线了: " + socket.getRemoteSocketAddress());
                    dis.close();
                    socket.close();
                    break;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
