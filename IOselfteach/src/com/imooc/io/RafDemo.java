package com.imooc.io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

/**
 * Created by William on 2015/5/21.
 */
public class RafDemo {

    public static void main(String[] args) throws IOException {
        File demo = new File("Demo");
        if (!demo.exists()) {
            demo.mkdir();
        }
        File file = new File(demo, "raf.dat");
        if (!file.exists()) {
            file.createNewFile();
        }
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        System.out.println(raf.getFilePointer());

        raf.write('A');//write只写一个字节(后八位)
        System.out.println(raf.getFilePointer());
        raf.write('B');//指针2

        int i = 0x7fffffff;
        //用write方法每次只能写一个字节,需要写I就要写四次
        raf.write(i >>> 24);//右移24位,写高八位 指针3
        raf.write(i >>> 16);//指针4
        raf.write(i >>> 8);//指针5
        raf.write(i);//指针6
        System.out.println(raf.getFilePointer());

        //可以直接写int,等效于上段包装后实现
        raf.writeInt(i);

        String s = "中";
        byte[] utf_8 = s.getBytes("utf-8");
        raf.write(utf_8);
        System.out.println(raf.length());

        //读文件,必须把指针移到头部
        raf.seek(0);
        //一次性读取到字节数组中
        byte[] buf = new byte[(int) raf.length()];//long要强转
        raf.read(buf);
        System.out.println(Arrays.toString(buf));
        String s1 = new String(buf);
        System.out.println(s1);//中文必为乱码,除非只包装utf-8中文对应的三字节(一个字),而java为utf-16be
        for (byte b : buf) {
            System.out.println(Integer.toHexString(b & 0xff) + ' ');
        }
        raf.close();//流程结束必须关闭

    }
}
