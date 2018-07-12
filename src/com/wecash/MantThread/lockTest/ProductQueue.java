package com.wecash.MantThread.lockTest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by chengtong on 2017/10/26.
 * 注解里面解释到了问题的根本。
 notifyall 时将所有的线程，生产者，消费者都唤醒了。而此时你只想唤醒生产者，或者只想唤醒消费者，让你胡子眉毛一把抓
 */
public class ProductQueue<V> {
    private final static int defaultSize=10;
    private final V[] queue;
    private int total;
    private int tail;
    private int head;
    private Lock lock=new ReentrantLock();
    private Condition notEmpty=lock.newCondition();
    private Condition notFull= lock.newCondition();


    public ProductQueue(){

        this(defaultSize);
    }

    public ProductQueue(int initialCapacity) {
        super();
        this.queue = (V[])new Object[initialCapacity];
    }


    public void push(V v) throws InterruptedException{
        lock.lock();
        try {
            while (isFull()) {
                notFull.await();
            }

            queue[tail] = v;
            ++tail;
            if (tail == queue.length)
                tail = 0;
            total++;
            notEmpty.signal();//唤醒的是同一种类型的线程,不会浪费。

        } finally{
            lock.unlock();
        }

    }



    public V pop() throws InterruptedException{
        lock.lock();
        try {
            while(isEmpty()){
                notEmpty.await();
            }
            V v=queue[head];
            head++;
            if(head==queue.length)head=0;
            total--;
            notFull.signal();//唤醒的是同一种类型的线程,不会浪费。
            return v;
        } finally{
            lock.unlock();
        }
    }


    public boolean isEmpty(){
        return total==0;
    }
    public boolean isFull(){
        return total==queue.length;
    }


}
