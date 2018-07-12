package com.wecash.MantThread.waitNotify;

/**
 * Created by chengtong on 2018/4/13.
 */
public class NumberTest {
    public static void main(String[] args)
    {
        NumberHolder numberHolder = new NumberHolder();

        Thread t1 = new IncreaseThread(numberHolder);
        Thread t2 = new DecreaseThread(numberHolder);

        t1.start();
        t2.start();
    }
}
