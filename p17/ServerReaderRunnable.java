package com.itheima.p17;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

// 定义任务类
public class ServerReaderRunnable implements Runnable{
    private Socket socket;

    public ServerReaderRunnable(Socket socket) {
        this.socket = socket;                    // 把socket管道交给独立的线程
    }

    @Override
    public void run() {
        try {
            OutputStream os = socket.getOutputStream();
            // 必须返回正确的HTTP协议格式，否则浏览器会认为服务器出现了问题
            // 这里就不适合用数据输出流dos，而用打印流
            PrintStream ps = new PrintStream(os);
            ps.println("HTTP/1.1 200 OK");
            ps.println("Content-Type:text/html;charset=UTF-8");        // 类型：文本内容
            ps.println();            // 必须换行，区分头和内容
            ps.println("<html>");
            ps.println("<head><title>服务器响应</title></head>");
            ps.println("<body>");
            ps.println("<h3>服务器响应</h3>");
            ps.println("</body>");
            ps.close();
            socket.close();                      // 短连接，用完就关闭
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

