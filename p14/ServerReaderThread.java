package com.itheima.p14;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ServerReaderThread extends Thread{
    private Socket socket;

    public ServerReaderThread(Socket socket) {
        this.socket = socket;                    // 把socket管道交给独立的线程
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
                    System.out.println("客户端已退出: " + socket.getRemoteSocketAddress());
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
