package com.wecash.fanxing;

import com.google.common.collect.Maps;

/**
 * Created by chengtong on 2018/3/8.
 */
public class TestFun {
    public static <N extends Number> double add(N a, N b){

        double sum = 0;

        sum = a.doubleValue() + b.doubleValue();

        return sum;

    }

    public static void main(String[] args) {

    }
}
