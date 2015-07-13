package com.imooc.io;

import java.io.*;

/**
 * Created by William on 2015/5/26.
 */
public class BrAndBwOrPwDemo {


    public static void main(String[] args) throws IOException {
        //对文件读写操作
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("G:\\ABC\\yob.txt")));

//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("G:\\ABC\\yob_hh.txt")));

        PrintWriter pw = new PrintWriter("G:\\ABC\\yob_pw.txt");

        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
//            bw.write(line);
//            //需要单独写出换行操作
//            bw.newLine();
//            bw.flush();
            pw.println(line);
            pw.flush();
        }
        br.close();
        pw.close();
        //bw.close();

    }
}
