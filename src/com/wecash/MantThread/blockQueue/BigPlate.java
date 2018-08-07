package com.wecash.MantThread.blockQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by chengtong on 2017/11/1.
 * 定义一个盘子类，可以放鸡蛋和取鸡蛋
 * ArrayBlockingQueue的内部是通过一个可重入锁ReentrantLock和两个Condition条件对象来实现阻塞
 * // 控制并发访问的锁
  final ReentrantLock lock;
 //notEmpty条件对象，用于通知take方法队列已有元素，可执行获取操作
  private final Condition notEmpty;
//notFull条件对象，用于通知put方法队列未满，可执行添加操作
  private final Condition notFull;
 */
public class BigPlate {

    /** 装鸡蛋的盘子，大小为5 */
    private BlockingQueue eggs = new ArrayBlockingQueue(5);

    /** 放鸡蛋 */
    public void putEgg(Object egg) {
        try {
            eggs.put(egg);// 向盘子末尾放一个鸡蛋，如果盘子满了，当前线程阻塞
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 下面输出有时不准确，因为与put操作不是一个原子操作
        System.out.println("放入鸡蛋");
    }

    /** 取鸡蛋 */
    public Object getEgg() {
        Object egg = null;
        try {
            egg = eggs.take();// 从盘子开始取一个鸡蛋，如果盘子空了，当前线程阻塞
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 下面输出有时不准确，因为与take操作不是一个原子操作
        System.out.println("拿到鸡蛋");
        return egg;
    }

    /** 放鸡蛋线程 */
    static class AddThread extends Thread {
        private BigPlate plate;
        private Object egg = new Object();

        public AddThread(BigPlate plate) {
            this.plate = plate;
        }

        public void run() {
            plate.putEgg(egg);
        }
    }

    /** 取鸡蛋线程 */
    static class GetThread extends Thread {
        private BigPlate plate;

        public GetThread(BigPlate plate) {
            this.plate = plate;
        }

        public void run() {
            plate.getEgg();
        }
    }

    public static void main(String[] args) {
        BigPlate plate = new BigPlate();
        // 先启动10个放鸡蛋线程
        for(int i = 0; i < 10; i++) {
            new Thread(new AddThread(plate)).start();
        }
        // 再启动10个取鸡蛋线程
        for(int i = 0; i < 10; i++) {
            new Thread(new GetThread(plate)).start();
        }
    }
}
