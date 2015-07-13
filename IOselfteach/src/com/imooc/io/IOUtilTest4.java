package com.imooc.io;

import java.io.File;
import java.io.IOException;

/**
 * Created by William on 2015/5/26.
 */
public class IOUtilTest4 {


    public static void main(String[] args) {

        try {
            long start = System.currentTimeMillis();
            //比较bybyte,bybuffer和copyfile(批量读取)的时间
            IOUtil.copyFile(new File("G:\\ABC\\test2.mp3"), new File("G:\\ABCD\\test2_moved5.mp3"));
            long end = System.currentTimeMillis();
            System.out.println(end - start);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
