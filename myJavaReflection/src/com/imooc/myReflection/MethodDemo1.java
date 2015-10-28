package com.imooc.myReflection;

import java.lang.reflect.Method;

/**
 * Created by William on 2015/10/28.
 */
public class MethodDemo1 {
    public static void main(String[] args) {
        //获取两种print方法之一(比如带;两个整形的)
        //1.要获取一个方法就是获取类的的信息,获取类的信息首先要获取类的类类型
        A a1 = new A();
        Class c = a1.getClass();
        /*
        2.获取方法,必须要有名称和参数列表
        getMethod获取的是public方法
         */
        try {
            Method method = c.getMethod("print", int.class, int.class);

            //方法的反射操作
            //通常办法a1.print(10,20);
            //反射指的是用method对象进行方法调用,和a1.print()效果完全相同
            //方法如果没有返回值返回null
            //invoke(obj,args...)
            Object object = method.invoke(a1, new Object[]{10, 20});

            System.out.println("================");
            //获取方法对象
            Method method1 = c.getMethod("print", new Class[]{String.class, String.class});
//            a1.print("hello", "world");
            object = method1.invoke(a1, "hello", "world");
            object = method1.invoke(a1, new Object[]{"fuck", "dick"});
            Method method2 = c.getMethod("print");
            Method method3 = c.getMethod("print", new Class[]{});
            method2.invoke(a1, new Object[]{});
            method3.invoke(a1);

        } catch (Exception e) {
            //万一带int.class x2的方法不存在呢?所以要try-catch
            e.printStackTrace();
        }

    }
}

class A {
    public void print(int a, int b) {
        System.out.println(a + b);
    }

    public void print() {
        System.out.println("Dick Pussy");
    }

    public void print(String a, String b) {
        System.out.println(a.toUpperCase() + "," + b.toLowerCase());
    }
}
