package com.wecash.MantThread.blockQueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by chengtong on 2017/11/1.
 */
public class ConditionBlockQueue {
    final Lock lock = new ReentrantLock(); //定义一个锁
    final Condition notFull = lock.newCondition(); //定义阻塞队列满了的Condition
    final Condition notEmpty = lock.newCondition();//定义阻塞队列空了的Condition

    final Object[] items = new Object[10]; //为了下面模拟，设置阻塞队列的大小为10，不要设太大

    int putptr, takeptr, count; //数组下标，用来标定位置的

    //往队列中存数据
    public void put(Object x) throws InterruptedException {
        lock.lock(); //上锁
        try {
            while (count == items.length) {
                System.out.println(Thread.currentThread().getName() + " 被阻塞了，暂时无法存数据！");
                notFull.await();    //如果队列满了，那么阻塞存数据这个线程，等待被唤醒
            }
            //如果没满，按顺序往数组中存
            items[putptr] = x;
            if (++putptr == items.length) //这是到达数组末端的判断，如果到了，再回到始端
                putptr = 0;
            ++count;    //消息数量
            System.out.println(Thread.currentThread().getName() + " 存好了值： " + x);
            notEmpty.signal(); //好了，现在队列中有数据了，唤醒队列空的那个线程，可以取数据啦
        } finally {
            lock.unlock(); //放锁
        }
    }

    //从队列中取数据
    public Object take() throws InterruptedException {
        lock.lock(); //上锁
        try {
            while (count == 0) {
                System.out.println(Thread.currentThread().getName() + " 被阻塞了，暂时无法取数据！");
                notEmpty.await();  //如果队列是空，那么阻塞取数据这个线程，等待被唤醒
            }
            //如果没空，按顺序从数组中取
            Object x = items[takeptr];
            if (++takeptr == items.length) //判断是否到达末端，如果到了，再回到始端
                takeptr = 0;
            --count; //消息数量
            System.out.println(Thread.currentThread().getName() + " 取出了值： " + x);
            notFull.signal(); //好了，现在队列中有位置了，唤醒队列满的那个线程，可以存数据啦
            return x;
        } finally {
            lock.unlock(); //放锁
        }
    }
}
