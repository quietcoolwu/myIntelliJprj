package com.imooc.myReflection;

/**
 * Created by William on 2015/10/26.
 */
public class ClassDemo1 {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        //Foo的对象实例
        Foo foo1 = new Foo();
        //Foo这个类也是一个实例对象,Class类的实例对象如何表示?
        //Private constructor. Only the Java Virtual Machine creates Class objects.
        //任何一个类都是Class类的实例对象,有三种表示方式
        //任何一个类都有一个隐含的静态成员变量class
        Class c1 = Foo.class;

        //第二种表达方式,已经知道该类的对象通过getClass方法获得
        Class c2 = foo1.getClass();
        //foo1是foo的实例对象,c1c2又是class的实例对象

        //c1c2表示了Foo类的类的类型(classtype),Foo就是class类的实例,一切皆对象,类也是对象是class类的实例对象,这个对象为该类的类类型
        System.out.println(c1 == c2);
        //不管c1还是c2都代表了Foo类的类类型,一个类只可能是Class类的一个实例对象

        //第三种表达方式
        Class c3 = null;
        try {
            c3 = Class.forName("com.imooc.myReflection.Foo");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(c2 == c3);

        //完全可以通过类的类类型创建该类的对象---->通过c1 or c2 or c3创建父类对象,需要强制类型转换
        Foo foo2 = (Foo) c1.newInstance();//需要调用无参数的构造方法
        System.out.println(foo1 == foo2);
        foo2.print();

    }
}

class Foo {
    void print() {
        System.out.println("foo now!");
    }
}
