package com.imooc.myReflection;

/**
 * Created by William on 2015/10/27.
 */

public class OfficeActive {

    public static void main(String[] args) {
        try {
            //动态加载类,在运行时刻加载,返回加载的类类型
            Class c = Class.forName(args[0]);
            //通过类类型创建对象,但是--往多个中的哪个类转换?
            //给多个类共同制定标准接口比如叫Officeable
            //每次不用重新编译,直接传入动态加载即可
            OfficeAble oa = (OfficeAble) c.newInstance();
            oa.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
