package com.imooc.io;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * Created by William on 2015/5/26.
 */
public class ObjSerializeDemo1 {


    public static void main(String[] args) throws Exception {
        String file = "demo/obj.dat";
        //对象的序列化
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
//        Student stu = new Student("10001", "张三", 20);
//        oos.writeObject(stu);
//        oos.flush();
//        oos.close();


        //对象的反序列化
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        Student stu_out = (Student) ois.readObject();
        System.out.println(stu_out);
        ois.close();
    }
}
