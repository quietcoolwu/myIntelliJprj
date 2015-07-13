package com.imooc.io;

import java.io.*;

/**
 * Created by William on 2015/5/26.
 */
public class IsrAndOswDemo {


    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream("G:\\ABC\\test3.txt"), "utf-8");
        FileOutputStream out = new FileOutputStream("G:\\ABC\\test3_1.txt");
        OutputStreamWriter osw = new OutputStreamWriter(out, "utf-8");

//        int c;
//        while((c = isr.read())!=-1){
//            System.out.println((char)c);
//        }
        char[] buffer = new char[8 * 1024];
        int c;
        //批量读取,从0位置开始放,最多放buffer.length个,返回的是读到字符的个数

        while ((c = isr.read(buffer, 0, buffer.length)) != -1) {
            String s = new String(buffer, 0, c);
            System.out.println(s);
            osw.write(buffer, 0, c);
            osw.flush();
        }
        isr.close();
        osw.close();


    }
}
