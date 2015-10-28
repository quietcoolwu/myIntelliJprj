package com.imooc.myReflection;

/**
 * Created by William on 2015/10/28.
 */
public class ClassDemo5 {

    public static void main(String[] args) {
        ClassUtil.printConstructorMessage("hello");
        System.out.println("==============");
        ClassUtil.printConstructorMessage(new Integer(100));
    }
}
