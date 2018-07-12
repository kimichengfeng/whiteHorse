package com.wecash.MantThread.joinTest;

/**
 * Created by chengtong on 2017/10/23.
 */
public class ThreadJoinTest {
    public static void main(String[] args) {
        Thread t = new Thread(new RunnableImpl());
        new ThreadTest(t).start();
        t.start();
        try {
            t.join(1000);
            System.out.println("joinFinish");
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
    }
}

class RunnableImpl implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println("Begin sleep");
            Thread.sleep(1000);
            System.out.println("End sleep");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadTest extends Thread {
    Thread thread;
    public ThreadTest(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        holdThreadLock();
    }

    public void holdThreadLock() {
        //用当前的线程当做lock
        synchronized (thread) {
            System.out.println("getObjectLock");
            try {
                Thread.sleep(9*1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.println("ReleaseObjectLock");
        }

    }
}
