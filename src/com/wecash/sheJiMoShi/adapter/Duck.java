package com.wecash.sheJiMoShi.adapter;

/**
 * Created by chengtong on 2018/3/13.
 * 将火鸡伪装成一个Duck这是我们的目的，看到这句话可能会想到装饰者模式，
 * 注意比较，先看适配器模式代码，一个Duck接口和一个Turkey接口。
 */
public interface Duck {
    public void quak();
    public void fly();
}
