package com.imooc;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * UDP客户端实现
 * Created by William on 2015/6/25.
 */
public class UDPClient {

    public static void main(String[] args) throws IOException {
        /*
        向服务器端发送数据
         */
        //1.定义服务器地址,服务器端口号,数据
        InetAddress address = InetAddress.getByName("localhost");
        int port = 8800;
        //将用户数据转换为字节数组
        byte[] data = "用户名:admin;密码:123".getBytes();
        //2.创建数据报,包含发送的数据信息
        DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
        //3.创建dgsocket对象
        DatagramSocket socket = new DatagramSocket();
        //4.向服务器端发送数据报
        socket.send(packet);

        //只写以上的代码接收服务器相应的代码

        /*
        接收响应数据
         */
        //1.创建数据报,接收服务器端响应数据
        byte[] data2 = new byte[1024];
        DatagramPacket packet2 = new DatagramPacket(data2, data2.length);
        //2.接收服务器响应的数据
        socket.receive(packet2);
        //3.读取数据
        String reply = new String(data2, 0, packet2.getLength());
        System.out.println("我是客户端,服务器说:" + reply);
        //4.关闭资源
        socket.close();

    }
}
