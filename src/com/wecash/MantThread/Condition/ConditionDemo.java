package com.wecash.MantThread.Condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by chengtong on 2018/4/13.
 * Condition定义了等待/通知两种类型的方法，当前线程如果调用这些方法之前，
 * 必须先获取到condition对象关联的锁，Condition对象是由Lock对象创建出来的，
 * 也就是说Condition是绑定在一个Lock对象上的，依赖于Lock对象，
 * 使用时需要通过Lock对象new出来。相比于之前的wait/notify而言，
 * condition充当wait/notify的角色，而lock对象充当synchronized锁角色。
 */
public class ConditionDemo {
    // 创建重入锁
    private Lock lock = new ReentrantLock();
    // Lock对象创建condition对象
    private Condition condition = lock.newCondition();
    public void method1(){
        try {
            lock.lock();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "获取锁，并睡眠3秒..");
            Thread.sleep(3000);
            System.out.println("当前线程：" + Thread.currentThread().getName() + "释放锁..进入等待状态");
            // 等待
            condition.await();
            System.out.println("当前线程：" + Thread.currentThread().getName() +"被唤醒，继续执行...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void method2(){
        try {
            lock.lock();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "获取锁，进入...睡眠3秒");
            Thread.sleep(3000);
            System.out.println("当前线程：" + Thread.currentThread().getName() + "发出唤醒..，并释放锁");
            // 唤醒，同样要注意多线程下死锁的发生，优先使用signalAll()唤醒所有等待此条件的线程
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public static void main(String[] args) throws Exception{
        final ConditionDemo uc = new ConditionDemo();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                uc.method1();
            }
        }, "线程1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                uc.method2();
            }
        }, "线程2");
        System.out.println("线程1启动。。。");
        t1.start();
        Thread.sleep(1000);
        System.out.println("线程2启动。。。");
        t2.start();
    }
}
