package com.wecash.MantThread.JUC;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by chengtong on 2018/4/11.
 *  有三个工人在为老板干活，这个老板有一个习惯，就是当三个工人把一天的活都干完了的时候，
 * 他就来检查所有工人所干的活。记住这个条件：三个工人先全部干完活，老板才检查。
 * 所以在这里用Java代码设计两个类，Worker代表工人，Boss代表老板
 */
public class TestLatch {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        CountDownLatch latch = new CountDownLatch(3);

        Work w1 = new Work(latch,"张三");
        Work w2 = new Work(latch,"李四");
        Work w3 = new Work(latch,"王二");

        Boss boss = new Boss(latch);

        executor.execute(w3);
        executor.execute(w2);
        executor.execute(w1);
        executor.execute(boss);

        executor.shutdown();
    }

}
