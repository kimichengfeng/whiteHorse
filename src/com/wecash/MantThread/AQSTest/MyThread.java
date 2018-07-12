package com.wecash.MantThread.AQSTest;

import java.util.concurrent.locks.Lock;

/**
 * Created by chengtong on 2017/10/30.
 */
public class MyThread extends Thread {
    private Lock lock;
    public MyThread(String name, Lock lock) {
        super(name);
        this.lock = lock;
    }

    public void run () {
        lock.lock();
        try {
            System.out.println(Thread.currentThread() + " running");
        } finally {
            lock.unlock();
        }
    }
}
