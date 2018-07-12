package com.wecash.fanxing;

/**
 * Created by chengtong on 2018/1/18.
 * 泛型接口：
 将泛型原理用于接口实现中，就是泛型接口。

 泛型接口的格式：泛型接口格式类似于泛型类的格式，接口中的方法的格式类似于泛型方法的格式。泛型接口：
 将泛型原理用于接口实现中，就是泛型接口。

 泛型接口的格式：泛型接口格式类似于泛型类的格式，接口中的方法的格式类似于泛型方法的格式。
 */
public class Generic2 implements MyInteface<String> {
    @Override
    public String read(String s) {
        return null;
    }
    public static void main(String[] args) {
        Generic2 g = new Generic2();
        System.out.println(g.read("hahaha"));
    }
}
