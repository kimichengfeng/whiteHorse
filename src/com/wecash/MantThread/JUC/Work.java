package com.wecash.MantThread.JUC;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by chengtong on 2018/4/11.
 * 有三个工人在为老板干活，这个老板有一个习惯，就是当三个工人把一天的活都干完了的时候，
 * 他就来检查所有工人所干的活。记住这个条件：三个工人先全部干完活，老板才检查。
 * 所以在这里用Java代码设计两个类，Worker代表工人，Boss代表老板
 */
public class Work implements Runnable {
    private CountDownLatch downLatch;
    private String name;

    public Work(CountDownLatch downLatch, String name){
        this.downLatch = downLatch;
        this.name = name;
    }
    @Override
    public void run() {
        this.doWork();
        try{
            TimeUnit.SECONDS.sleep(new Random().nextInt(10));
        }catch(InterruptedException ie){
        }
        System.out.println(this.name + "活干完了！");
        this.downLatch.countDown();
    }
    private void doWork(){
        System.out.println(this.name + "正在干活!");
    }

}
