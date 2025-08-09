package com.itheima.p15;

import java.io.*;
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

                    //把 收到的消息 发给所有客户端
                    sendMessage(msg);
                } catch (IOException e) {
                    System.out.println("客户端已退出: " + socket.getRemoteSocketAddress());
                    Server.onLineSockets.remove(socket);           // 将管道从集合中移除
                    dis.close();
                    socket.close();
                    break;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(String msg) throws IOException {
        // 发送给全部的socket管道
        for (Socket socket : Server.onLineSockets) {
            OutputStream os = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeUTF(msg);
            dos.flush();
        }
    }

}
