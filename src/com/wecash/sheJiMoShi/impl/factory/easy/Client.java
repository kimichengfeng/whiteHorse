package com.wecash.sheJiMoShi.impl.factory.easy;

public class Client {
    public static void main(String[] args) {
        //调用Factory的静态方法生成所要的类
        Factory.create("productA");
        Factory.create("ProductB");
    }
}
