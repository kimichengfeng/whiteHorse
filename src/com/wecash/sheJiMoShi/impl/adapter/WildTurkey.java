package com.wecash.sheJiMoShi.impl.adapter;

import com.wecash.sheJiMoShi.inter.adapter.Turkey;

/**
 * Created by chengtong on 2018/3/13.
 */
public class WildTurkey implements Turkey {
    @Override
    public void gobble() {
        System.out.println("Gobble gobble");
    }

    @Override
    public void fly() {
        System.out.println("I'm flying a short distance");
    }
}
