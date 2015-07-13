package com.mooc163.java2;

import java.util.Scanner;

/**
 * 起别名
 * Created by William on 2015/7/7.
 */
public class otherName {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] a = new int[10];
        a[0] = 5;
        int[] b = a;
        System.out.println(a[0]);

        b[0] = 16;
        //a和b都是数组的管理者,指向同一组10个的内存空间
        System.out.println(a[0]);
    }
}
