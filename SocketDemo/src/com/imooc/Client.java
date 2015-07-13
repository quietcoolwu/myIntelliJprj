package com.imooc;

import java.io.*;
import java.net.Socket;

/**
 * TCP通信客户端
 * Created by William on 2015/6/17.
 */
public class Client {


    public static void main(String[] args) {
        //1.创建客户端socket, 指定服务器地址和端口
        try {
            Socket socket2 = new Socket("localhost", 8888);
            //2.获取输出流,用来向服务器端发送登录信息
            OutputStream os = socket2.getOutputStream();//字节输出流
            PrintWriter pw = new PrintWriter(os);
            pw.write("用户名：admin1；密码：456");
            pw.flush();//刷新缓存
            //关闭socket输出流
            socket2.shutdownOutput();
            //获取输入流,用来读取服务器端的相应
            InputStream is = socket2.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String info = null;
            while ((info = br.readLine()) != null) {//循环读取服务器端信息
                System.out.println("我是客户端,服务器说：" + info);
            }

            //关闭其他资源
            br.close();
            is.close();

            pw.close();
            os.close();
            socket2.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
