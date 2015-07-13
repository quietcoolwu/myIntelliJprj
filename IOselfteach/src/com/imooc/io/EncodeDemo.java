package com.imooc.io;

/**
 * Created by William on 2015/5/21.
 */
public class EncodeDemo {

    public static void main(String[] args) throws Exception {
        String s = "慕课ABC";
        byte[] bytes1 = s.getBytes();//用项目默认编码转换
        for (byte b : bytes1) {
            //把字节转换成int以HEX显示
            System.out.println(Integer.toHexString(b & 0xff));//去掉前24位只留后八位
        }
        System.out.println();
        //gbk编码中文占用两个字节英文占用一个字节,utf-8中文占用三个字节,英文占用一个字节
        byte[] bytes2 = s.getBytes("gbk");
        for (byte b : bytes2) {
            System.out.println(Integer.toHexString(b & 0xff));
        }

        System.out.println();
        byte[] bytes3 = s.getBytes("utf-16");
        for (byte b : bytes3) {
            System.out.println(Integer.toHexString(b & 0xff));
        }

        //java是双字节编码utf-16be
        byte[] bytes4 = s.getBytes("utf-16be");
        for (byte b : bytes4) {
            System.out.println(Integer.toHexString(b & 0xff) + "  ");//utf-16be中文占两个字节,英文一样
        }

        /*
        当字节序列是某种编码时,想把字节序列变成字符串,也许要用这种方式,否则会乱码
         */
        String str1 = new String(bytes4);
        System.out.println(str1);//出现乱码
        String str2 = new String(bytes4, "utf-16be");
        System.out.println(str2);//恢复正常

        /*
        文本文件就是字节序列
        可以是任意编码的字节序列
        如果在中文电脑上直接创建文本文件,只能认识ANSI
        联通,联是一种巧合,正好符合utf-8的规则
         */


    }


}
