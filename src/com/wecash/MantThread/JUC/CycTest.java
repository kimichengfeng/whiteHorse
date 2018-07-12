package com.wecash.MantThread.JUC;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by chengtong on 2018/4/11.
 * 接着上面的例子，还是这三个工人，不过这一次，这三个工人自由了，老板不用检查他们任务了，他们三个合作建桥，
 * 有三个桩，每人打一个，同时打完之后才能一起搭桥（搭桥需要三人一起合作）。
 * 也就是说三个人都打完桩之后才能继续工作。
 */
public class CycTest {
    public static void main(String[] args)
    {
        ExecutorService executorpool= Executors. newFixedThreadPool(3);
        CyclicBarrier cyclicBarrier= new CyclicBarrier(3);

        CycWork work1= new CycWork(cyclicBarrier, "张三" );
        CycWork work2= new CycWork(cyclicBarrier, "李四" );
        CycWork work3= new CycWork(cyclicBarrier, "王五" );

        executorpool.execute(work1);
        executorpool.execute(work2);
        executorpool.execute(work3);

        executorpool.shutdown();

    }

}
