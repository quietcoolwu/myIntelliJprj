package com.imooc.io;

import java.io.File;
import java.io.IOException;

/**
 * Created by William on 2015/5/21.
 */
public class FileDemo {

    public static void main(String[] args) {
        //了解构造函数的情况 查帮助
        File file = new File("G:\\ABC\\hh");

        System.out.println(file.exists());
        if (!file.exists()) {
            file.mkdir();//有多级目录时用file.mkdirs()
        } else {
            file.delete();
        }
        //判断是否是目录,是则返回TRUE
        System.out.println(file.isDirectory());
        //判断是否是文件
        System.out.println(file.isFile());

        File file2 = new File("e:\\hh3.txt");
        //File file2 = new File("e:\\", "hh3.txt");
        if (!file2.exists()) {
            try {
                file2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            file2.delete();
        }

        //常用FILE API
        System.out.println(file);
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getName());
        System.out.println(file2.getName());
        System.out.println(file.getParent());
        System.out.println(file2.getParent());
        System.out.println(file.getParentFile().getAbsolutePath());

    }
}
