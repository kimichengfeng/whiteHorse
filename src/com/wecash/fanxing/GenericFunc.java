package com.wecash.fanxing;

/**
 * Created by chengtong on 2018/1/18.
  泛型方法也是为了提高代码的重用性和程序安全性。编程原则：尽量设计泛型方法解决问题，如果设计泛型方法可以取代泛型整个类，应该采用泛型方法。

 泛型方法的格式：类型变量放在修饰符后面和返回类型前面， 如：public static <E> E getMax(T... in)
 */
public class GenericFunc {
    public static void main(String[] args) {
        print("hahaha");
        print(200);
    }

    public static <T> void print(T t){
        System.out.println(t.toString());
    }

}
