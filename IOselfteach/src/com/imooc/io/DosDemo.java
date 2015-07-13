package com.imooc.io;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 写入普通数据类型
 * Created by William on 2015/5/25.
 */
public class DosDemo {


    public static void main(String[] args) throws IOException {

        String file = "demo/dos.dat";
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(file));

        dos.writeInt(10);
        dos.writeInt(-10);
        dos.writeLong(10l);
        dos.writeDouble(10.5);
        dos.writeUTF("中国");
        //默认为utf-16be写出
        dos.writeChars("中国");
        dos.close();
        IOUtil.printHex(file);

    }
}
