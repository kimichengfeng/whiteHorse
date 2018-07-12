package com.wecash.MantThread.stopSuspendResume;

/**
 * Created by chengtong on 2018/4/13.
 */
public class TestObject2 {
    public synchronized void print(){
        if(Thread.currentThread().getName().equals("A")){
            System.out.println("A 线程 独占该资源了");
            Thread.currentThread().suspend();
        }
    }
}
