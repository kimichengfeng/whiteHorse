package com.wecash.fanxing;

/**
 * Created by chengtong on 2018/1/18.
 * 只要类中操作的引用数据类型不确定，就可以定义泛型类。通过使用泛型类，可以省去强制类型转换和类型转化异常的麻烦。
 */
public class Util<E> {
    public E getE(E e){
        return e;
    }
}
