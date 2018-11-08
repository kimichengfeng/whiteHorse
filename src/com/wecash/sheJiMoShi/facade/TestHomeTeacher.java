package com.wecash.sheJiMoShi.facade;

/**
 * Created by chengtong on 2018/3/13.
 * 外观者模式
 * 提供一个简易的接口，来访问子系统中的一群接口。外观定义了一个高层接口，让子系统容易使用。
 */
public class TestHomeTeacher {
    public static void main(String[] args) {
        Amplifier amp = new Amplifier();
        Light light = new Light();
        TV tv = new TV();
        HomeTheater homeTheater = new HomeTheater(amp, light, tv);
        homeTheater.WatchMove();
    }
}
