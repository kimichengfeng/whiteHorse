package com.wecash.fanxing;

/**
 * Created by chengtong on 2018/1/18.
 */
public class Teacher {
    String name;
    int age;
    public Teacher() {
    }

    public Teacher(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
