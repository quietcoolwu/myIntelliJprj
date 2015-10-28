package com.imooc.myReflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 获取类的全部信息,包括所属类的成员函数和函数成员变量
 * Created by William on 2015/10/28.
 */
public class ClassUtil {

    public static void printClassMethodMessage(Object object) {
        /**
         * 获取成员函数信息
         */
        //传入obj对象
        //要获取类的信息,首先要获取类的类类型--classtype
        /*
        1.类名.class
        2.对象.getClass
        3.Class.forName
         */
        Class c = object.getClass();//传递的是哪个子类的对象,c就是该子类的类类型,本地方法(native),JNI
        //获取类的名称
        System.out.println("1.类的名称是: " + c.getName());
        /*
        Method类,方法对象
        一个成员方法就是一个method对象
        getMethods获取所有public函数包括父类继承来的
        getDeclaredMethod获取的是所有该类自己声明的方法,与访问权限扩展无关
         */
        Method[] ms = c.getDeclaredMethods();
        //c.getDeclaredMethods();
        for (int i = 0; i < ms.length; i++) {
            //得到方法的返回值类型的类类型--比如String.class
            Class returnType = ms[i].getReturnType();
            //得到方法名

            System.out.print(returnType.getName() + "<--成员函数返回类的类类型 ");
            System.out.print(ms[i].getName() + "<--成员函数方法名(");


            //获取参数类型--->得到的是参数列表的类型的类类型
            Class[] paramTypes = ms[i].getParameterTypes();
            for (Class class1 : paramTypes) {
                System.out.print(class1.getName() + "<--成员函数参数所属类的类类型,");

            }
            System.out.println(")");
            //System.out.println("..)");
            //System.out.println();


        }
    }

    public static void printFieldMessage(Object objects) {
        /**
         * 获取成员变量信息
         */
         /*
            成员变量也是对象
            成员变量作为对象属于java.lang.reflect.Filed类
            它封装了关于成员变量的操作
            getFields()方法获取的是所有的public的成员变量的信息
            getDeclaredFields获取的是该类自己生命的成员变量的信息
             */
        //Field[] fs = c.getFields();
        //printFieldMessage(c);
        Class c = objects.getClass();
        System.out.println("2.类的名称是: " + c.getName());
        Field[] fs = c.getDeclaredFields();
        //c.getFields();

        for (Field field : fs) {
            //得到成员变量的类的类类型
            Class fieldType = field.getType();//int.class...

            String typeName = fieldType.getName();
            //System.out.println(typeName+"");
            //得到成员变量的名字
            String fieldName = field.getName();
            System.out.print(typeName + "<--成员变量所属类的类类型  ");
            System.out.println(fieldName + "<--成员变量名");
        }
    }

    /**
     * 打印对象构造函数的信息
     *
     * @param object
     */
    public static void printConstructorMessage(Object object) {
        Class c = object.getClass();

        /*
        构造函数也是对象
        java.lang.constructor中封装了构造函数的信息
        getConstructors获取所有public的构造方法
         */

        Constructor[] cs = c.getDeclaredConstructors();
        for (Constructor constructor : cs) {
            System.out.print(constructor.getName() + "<--构造函数名(");
            //获取构造函数的参数列表
            Class[] paramtypes = constructor.getParameterTypes();
            for (Class class1 : paramtypes) {
                System.out.print(class1.getName() + ",");
            }
            System.out.println(")<--构造函数参数所属类类型");
        }
    }
}
