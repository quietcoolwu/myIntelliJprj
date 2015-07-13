package com.imooc.io;


import java.io.IOException;

/**
 * Created by William on 2015/5/22.
 */
public class IOUtilTest1 {

    public static void main(String[] args) {
        try {
            IOUtil.printHex("G:\\ABC\\Solution.java");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
