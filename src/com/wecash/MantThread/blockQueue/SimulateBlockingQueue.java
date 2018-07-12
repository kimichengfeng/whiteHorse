package com.wecash.MantThread.blockQueue;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用wait/notify模拟一个阻塞队列
 * Created by chengtong on 2017/11/1.
 */
public class SimulateBlockingQueue {
    //1.需要集合封装数据
    private LinkedList list = new LinkedList<>();

    //2.需要一个计数器(该类具备原子性)
    private AtomicInteger count = new AtomicInteger(0);

    //3.需要制定队列数据的上限以及下限
    private final int minSize = 0;
    private final int maxSize;

    //4.在构造方法给定队列数据的上限
    public SimulateBlockingQueue(int size) {
        this.maxSize = size;
    }

    //5.初始化一个对象 用于加锁
    private Object lock = new Object();

    //6.put(Object): 把Object加到BlockingQueue里,如果BlockQueue没有空间,
    // 则调用此方法的线程被阻断，直到BlockingQueue里面有空间再继续
    public void put(Object obj) {
        synchronized (lock) {

            //6.1集合是否满了
            while (count.get() == this.maxSize) {
                try {
                    //6.2进入阻塞状态（释放锁）
                    lock.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            //6.3添加元素操作
            list.add(obj);
            //6.4计数器累加
            count.incrementAndGet();
            //6.5通知另一个线程可以取数据了(唤醒功能)
            lock.notify();
            System.out.println("新加入的元素为:" + obj);
        }

    }

    //7.take: 取走BlockingQueue里排在首位的对象,若BlockingQueue为空,阻断进入等待状态直到BlockingQueue有新的数据被加入
    public Object take() {
        Object ret = null;
        synchronized (lock) {
            //7.1集合没有数据了
            while (count.get() == this.minSize) {
                try {

                    //7.2进入阻塞状态
                    lock.wait();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //7.3移除元素操作(移除第一个)
            ret = list.removeFirst();
            //7.4计数器递减
            count.decrementAndGet();
            //7.5唤醒两一个线程
            lock.notify();

        }
        return ret;

    }

    //8.得到队列的长度
    public int getSize() {
        return this.count.get();
    }

    public static void main(String[] args) {

        final SimulateBlockingQueue mq = new SimulateBlockingQueue(5);
        mq.put(1);
        mq.put(2);
        mq.put(3);
        mq.put(4);
        mq.put(5);
        System.out.println("------当前队列的长度--------" + mq.getSize());

        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                mq.put(6);
                mq.put(7);
            }
        }, "t1");

        t1.start();


        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Object o1 = mq.take();
                System.out.println("-------移除的元素-------" + o1);
                Object o2 = mq.take();
                System.out.println("-------移除的元素为-------" + o2);
            }
        }, "t2");

        try {
            TimeUnit.SECONDS.sleep(2);//睡眠2s才启动线程2
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.start();

    }

}
