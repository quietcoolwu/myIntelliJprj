package com.imooc.io;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by William on 2015/5/26.
 */
public class FrAndFwDemo {


    public static void main(String[] args) throws IOException {

        FileReader fr = new FileReader("G:\\ABC\\test3.txt");
        FileWriter fw = new FileWriter("G:\\ABC\\test3_2.txt", true);//追加启用
        char[] buffer = new char[8 * 1024];
        int c;
        while ((c = fr.read(buffer, 0, buffer.length)) != -1) {
            fw.write(buffer, 0, c);
            fw.flush();

        }
        fr.close();
        fw.close();
    }
}
