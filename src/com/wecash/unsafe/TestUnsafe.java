package com.wecash.unsafe;

import sun.misc.Unsafe;

import static sun.misc.Unsafe.getUnsafe;

/**
 * Created by chengtong on 2017/8/1.
 */
public class TestUnsafe {
    static class Target {
        public int target = 1;
    }

    static class MyTestThread extends Thread {
        private Target target = null;
        public MyTestThread() {
        }
        public MyTestThread(Target target) {
            this.target = target;
        }
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Unsafe unsafe = getUnsafe();
                long valueOffset = unsafe.objectFieldOffset(Target.class.getDeclaredField("target"));
                unsafe.compareAndSwapInt(target, valueOffset, 1, 0);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            System.out.println("target2 out");
        }
    }
    volatile static Target[] target = new Target[1];
    public static void main(String[] args) {
        target[0] = new Target();
        Thread t1 = new Thread() {
            @Override
            public void run() {
                while(target[0].target == 1) {
                }
                System.out.println("target1 out");
            }
        };
        t1.start();
        Thread t2 = new MyTestThread(target[0]);
        t2.start();
    }
}
