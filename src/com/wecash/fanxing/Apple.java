package com.wecash.fanxing;

import com.fasterxml.jackson.core.type.TypeReference;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: tong.cheng
 * Date: 2020-07-01
 * Time: 18:05
 */
public class Apple<T> {

    public void test(Class<T> clazz) {
        Class class1 =  clazz;
        TypeReference t1 = new TypeReference<T>() {};
        System.out.println(t1.getType());
        TypeReference t2 = new TypeReference<Teacher>(){};
    }

    public static void main(String[] args) {
        Apple<Teacher> apple = new Apple<Teacher>();
        apple.test(Teacher.class);
    }
}
