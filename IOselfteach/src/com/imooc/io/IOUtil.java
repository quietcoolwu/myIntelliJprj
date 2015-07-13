package com.imooc.io;

//import org.omg.CORBA.PUBLIC_MEMBER;

import java.io.*;
//import java.io.FileNotFoundException;


/**
 * 读取指定文件内容,按照HEX进制输出到控制台
 * 并且每输出10个BYTE换行
 * Created by William on 2015/5/22.
 */
public class IOUtil {
    public static void printHex(String fileName) throws IOException {
        //把文件作为字节流进行读操作
        FileInputStream in = new FileInputStream(fileName);//文件输入流方法1--逐字节读取
        int b;
        int i = 1;
        while ((b = in.read()) != -1) {
            if (b <= 0xf) {
                //单位数前面补0
                System.out.println("0");
            }
            System.out.println(Integer.toHexString(b) + "::");
            if (i++ % 10 == 0) {
                System.out.println("!!");
            }

        }
        in.close();


    }


    /*
    批量读取效率高
     */
    public static void printHexByByteArray(String fileName) throws IOException {
        FileInputStream in = new FileInputStream(fileName);//文件输入流
        byte[] buf = new byte[20 * 1024];//20k
        //从in中批量读取字节放入buf字节数组中,从0放,最多放buf.length个,返回读到字节的个数
//        int bytes = in.read(buf, 0, buf.length);//一次性读完说明字节数组足够大
//        int j = 1;
//        for (int i = 0; i <bytes; i++) {
//            if(buf[i] <= 0xf){
//                System.out.println("0");
//
//            }else{
//                System.out.println(Integer.toHexString(buf[i])+"%%");
//            }
//
//            if(j++%10==0){
//                System.out.println("$$");
//            }
//
//
//        }


        int bytes = 0;
        int j = 0;
        while ((bytes = in.read(buf, 0, buf.length)) != -1) ;
        {
            for (int i = 0; i < buf.length; i++) {
                System.out.println(Integer.toHexString(buf[i] & 0xff) + "%%");//byte 8位 int32位,避免转换错误将高24位清0
                if (j++ % 10 == 0) {
                    System.out.println("$$");
                }
            }
        }


        in.close();//读取完之后关闭
    }


    /**
     * 文件拷贝,字节批量读取
     *
     * @param srcFile
     * @param destFile
     * @throws IOException
     */

    public static void copyFile(File srcFile, File destFile) throws IOException {
        if (!srcFile.exists()) {
            throw new IllegalArgumentException("文件不存在!");

        }
        if (!srcFile.isFile()) {
            throw new IllegalArgumentException(srcFile + "不是文件!");
        }

        FileInputStream in = new FileInputStream(srcFile);
        FileOutputStream out = new FileOutputStream(destFile);

        byte[] buf = new byte[8 * 1024];
        int b;
        while ((b = in.read(buf, 0, buf.length)) != -1) {
            out.write(buf, 0, b);
            out.flush();

        }
        in.close();
        out.close();

    }


    /**
     * 进行文件拷贝(带缓冲的字节流)
     *
     * @param srcFile
     * @param destFile
     * @throws IOException
     */

    public static void copyFileByBuffer(File srcFile, File destFile) throws IOException {
        if (!srcFile.exists()) {
            throw new IllegalArgumentException("文件不存在!");

        }
        if (!srcFile.isFile()) {
            throw new IllegalArgumentException(srcFile + "不是文件!");
        }

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcFile));

        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFile));

        int c;
        while ((c = bis.read()) != -1) {
            bos.write(c);
            bos.flush();//必须刷新缓冲区
        }
        bis.close();
        bos.close();


    }


    /**
     * 单字节,不带缓冲拷贝
     *
     * @param srcFile
     * @param destFile
     * @throws IOException
     */
    public static void copyFileByByte(File srcFile, File destFile) throws IOException {
        if (!srcFile.exists()) {
            throw new IllegalArgumentException("文件不存在!");

        }
        if (!srcFile.isFile()) {
            throw new IllegalArgumentException(srcFile + "不是文件!");
        }

        FileInputStream in = new FileInputStream(srcFile);
        FileOutputStream out = new FileOutputStream(destFile);
        int c;
        while ((c = in.read()) != -1) {
            out.write(c);
            out.flush();//此处bybyte方法不带缓冲,所以不一定要flush
        }
        in.close();
        out.close();

    }
}
