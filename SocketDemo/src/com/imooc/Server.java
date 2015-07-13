package com.imooc;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 实现基于TCP协议的Socket通信--Server端，实现用户登录，服务端要早于客户端启动
 * Created by William on 2015/6/17.
 */
public class Server {


    public static void main(String[] args) {
        //1.创建服务器端Socket,即ServerSocket，制定要绑定的端口，炳并侦听此端口
        try {
            ServerSocket ss = new ServerSocket(8888);//1023之后
            Socket socket1 = null;
            //记录客户端的数量
            int count = 0;

            System.out.println("服务器即将启动，等待客户端连接");
            //循环侦听等待客户端连接
            while (true) {
                //2.调用accept方法等待客户端的连接
                socket1 = ss.accept();
                //创建新线程
                ServerThread serverThread = new ServerThread(socket1);
                //启动线程
                serverThread.start();
                count++;//统计客户端的数量
                System.out.println("客户端的数量：" + count);
                InetAddress address = socket1.getInetAddress();
                System.out.println("当前客户端的IP：" + address.getHostAddress());

            }
            /*
            //3.获取输入流，并读取客户端信息
            //字节输入流
            InputStream is = socket1.getInputStream();
            //包装为字符流
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String info = null;
            while((info = br.readLine()) != null){//循环读取客户端信息
                System.out.println("我是服务器，客户端说：" + info);


            }
            socket1.shutdownInput();//关闭输入流

            //4.获取输出流,响应客户端请求
            OutputStream os = socket1.getOutputStream();
            PrintWriter pw = new PrintWriter(os);//包装为打印流
            pw.write("欢迎您!");
            pw.flush();//刷新缓存



            //5.关闭相关资源
            pw.close();
            os.close();

            br.close();
            isr.close();
            is.close();
            socket1.close();
            ss.close();*/

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
