package com.wecash.MantThread.volatileT;

import java.io.File;

import static java.lang.Thread.sleep;
import static java.lang.Thread.yield;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: tong.cheng
 * Date: 2020-08-14
 * Time: 14:31
 */
public class SeeCan {

    /**
     * 为什么主线程没有对ThreadDemo线程的变量变更可见呢？
     * 从内存存模型可知，主线程一直用工作内存的变量，没有重新加载主内存被ThreadDemo线程改变的变量
     * 为什么主线程一直没有重新加载主内存的变量呢？
     * 只有工作内存失效的时候。工作内存才会重新加载主内存的变量
     * 那什么时候工作内存的变量会失效呢
     * 线程中释放锁时
     * 线程切换时
     * CPU有空闲时间时（比如线程休眠，IO操作）
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        ThreadDemo td = new ThreadDemo();
        new Thread(td).start();
        while (true) {
//            在方法中调用同步方法 比如System.out.println();
//            System.out.println(td.isFlag());
//            线程休眠sleep(1000);
//            sleep(1000);
            // yield();
//            IO操作
//            File file = new File("D://temp.txt");

            if (td.isFlag()) {
                System.out.println("---end break---");
                break;
            }
        }
    }
    static class ThreadDemo implements Runnable {

//        private volatile boolean flag = false;
        private  boolean flag = false;
        @Override
        public void run() {
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            flag = true;
            System.out.println("flag=" + flag);
        }

        public boolean isFlag() {
            return flag;
        }
    }
}
