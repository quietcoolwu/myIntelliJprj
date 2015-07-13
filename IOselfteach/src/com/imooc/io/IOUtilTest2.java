package com.imooc.io;

import java.io.IOException;

/**
 * Created by William on 2015/5/25.
 */
public class IOUtilTest2 {


    public static void main(String[] args) {
        try {
            long start = System.currentTimeMillis();
            IOUtil.printHexByByteArray("G:\\ABC\\test1.mp3");//将函数改成printHex时间上会怎样?--单字节读取不适合读大文件,效率很低
            System.out.println("@@");
            long end = System.currentTimeMillis();

            System.out.println(end - start);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
