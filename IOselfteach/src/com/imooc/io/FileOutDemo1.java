package com.imooc.io;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by William on 2015/5/25.
 */
public class FileOutDemo1 {

    public static void main(String[] args) throws IOException {
        //如果该文件不存在则直接创建,如存在则删除后创建,+true时则为追加
        FileOutputStream out = new FileOutputStream("demo/out.dat");//文件流输出方法1
        out.write('A');//写出A字符的低八位
        out.write('B');//写出B字符低八位
        int a = 10;//write方法只能写低八位,写一个整数int要写四次
        out.write(a >>> 24);
        out.write(a >>> 16);
        out.write(a >>> 8);
        out.write(a);
        byte[] utf_8 = "中国".getBytes("utf-8");
        out.write(utf_8);
        out.close();

        IOUtil.printHex("demo/out.dat");
    }
}
