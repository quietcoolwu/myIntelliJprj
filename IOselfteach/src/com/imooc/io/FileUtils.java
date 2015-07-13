package com.imooc.io;

//import com.intellij.testFramework.exceptionCases.IllegalArgumentExceptionCase;

import java.io.File;
import java.io.IOException;

/**
 * Created by William on 2015/5/21.列出File类的常用操作,工具类
 */
public class FileUtils {
    /**
     * 列出指定目录下(包括子目录的所有文件
     *
     * @param dir
     * @throws IOException
     */

    public static void listDirectory(File dir) throws IOException {
        if (!dir.exists()) {
            throw new IllegalArgumentException("目录:" + dir + "不存在");
        } else if (!dir.isDirectory()) {
            throw new IllegalArgumentException(dir + "不是目录");
        }

        /*String[] filenames = dir.list();//返回字符串数组,直接子的名称不包含子目录下的内容
        for (String filename : filenames) {
            System.out.println(dir + filename);
        }*/

        //如果要遍历子目录下的内容需要构成File对象做递归操作,File提供了API能实现吗?
        File[] files = dir.listFiles();//返回子目录抽象
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isDirectory()) {
                    //递归
                    listDirectory(file);
                } else {
                    System.out.println(file);
                }


            }
        }
    }
}
