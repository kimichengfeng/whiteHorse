package com.wecash.sheJiMoShi.impl.adapter;

import com.wecash.sheJiMoShi.inter.adapter.Duck;

/**
 * Created by chengtong on 2018/3/13.
 */
public class MallarDuck implements Duck {
    @Override
    public void quak() {
        System.out.println("Quack~");
    }

    @Override
    public void fly() {
        System.out.println("I'm flying~");
    }
}
