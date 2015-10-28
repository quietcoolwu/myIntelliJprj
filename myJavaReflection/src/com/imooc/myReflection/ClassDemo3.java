package com.imooc.myReflection;

/**
 * Created by William on 2015/10/28.
 */
public class ClassDemo3 {

    public static void main(String[] args) {
        String s = "hello";
        //ClassUtil.printClassMessage(s);


        Integer n1 = 100;


        ClassUtil.printClassMethodMessage(s);
        System.out.println("=================");
        ClassUtil.printFieldMessage(s);
        System.out.println("=================");
        ClassUtil.printClassMethodMessage(n1);
        System.out.println("=================");
        ClassUtil.printFieldMessage(n1);
    }
}
