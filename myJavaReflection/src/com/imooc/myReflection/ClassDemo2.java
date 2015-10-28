package com.imooc.myReflection;

/**
 * Created by William on 2015/10/27.
 */
public class ClassDemo2 {

    public static void main(String[] args) {
        //int的类类型
        Class c1 = int.class;
        Class c2 = String.class;//String的类类型-String类字节码表示
        Class c3 = double.class;
        Class c4 = Double.class;
        Class c5 = void.class;
        System.out.println(c5.getName());
        System.out.println(c2.getName());
        System.out.println(c2.getSimpleName());

    }
}
