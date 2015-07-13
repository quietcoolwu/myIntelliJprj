package com.imooc.io;

import java.io.File;
import java.io.IOException;

/**
 * 文件拷贝测试
 * Created by William on 2015/5/25.
 */
public class IOUtilTest3 {


    public static void main(String[] args) {
        try {
            IOUtil.copyFile(new File("G:\\ABC\\test1.mp3"), new File("G:\\ABCD\\test1_moved.mp3"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
