package com.imooc;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * URL常用方法
 * Created by William on 2015/6/16.
 */
public class Test02 {
    public static void main(String[] args) {
        try {
            URL imooc = new URL("http://www.imooc.com");
            URL url = new URL(imooc, "/index.html?username=tom#test");//?后面表示参数，#后面表示锚点
            System.out.println("协议：" + url.getProtocol());
            System.out.println("主机地址：" + url.getHost());
            //未指定端口号，使用默认，则getPort返回值为-1
            System.out.println("端口:" + url.getPort());
            System.out.println("文件路径:" + url.getPath());

            System.out.println("文件名：" + url.getFile());
            System.out.println("relative path:" + url.getRef());

            System.out.println("search string" + url.getQuery());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }


}
