package com.imooc.io;

import java.io.Serializable;

/**
 * Created by William on 2015/5/26.
 * 实现序列化接口
 */
public class Student implements Serializable {

    private String stuNo;
    private String stuName;
    private transient int stuAge;//加上transient不会进行jvm默认序列化,但可以自己完成序列化


    public Student(String stuNo, String stuName, int stuAge) {
        this.stuNo = stuNo;
        this.stuName = stuName;
        this.stuAge = stuAge;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public int getStuAge() {
        return stuAge;
    }

    public void setStuAge(int stuAge) {
        this.stuAge = stuAge;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuNo='" + stuNo + '\'' +
                ", stuName='" + stuName + '\'' +
                ", stuAge=" + stuAge +
                '}';
    }

    private void writeObject(java.io.ObjectOutputStream s)
            throws java.io.IOException {
        s.defaultWriteObject();//把jvm默认能进行序列化的元素序列化
        s.writeInt(stuAge);//自己完成stuAge的序列化
        //ArrayList
    }

    private void readObject(java.io.ObjectInputStream s)
            throws java.io.IOException, ClassNotFoundException {
        s.defaultReadObject();//把jvm默认能反序列话的元素进行反序列化
        this.stuAge = s.readInt();//自己完成反序列化操作
    }


}
