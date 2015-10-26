package com.imooc.myReflection;

/**
 * Created by William on 2015/10/27.
 */
class Office {
    //虽然通不过编译,但JVM试图调用了Word吗
    public static void main(String[] args) {
        if ("com.imooc.myReflection.Word".equals(args[0])) {
            //new对象是静态加载类,编译时就需要加载所有可能使用的类,100个类当中1个出问题剩下的全用不了
            //通过动态加载类解决
            Word word = new Word();
            word.start();
        }


//        if ("com.imooc.myReflection.Excel".equals(args[0])) {
//            com.imooc.myReflection.Excel excel = new com.imooc.myReflection.Excel();
//            excel.start();
//        }
    }
}
