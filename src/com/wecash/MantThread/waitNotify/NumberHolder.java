package com.wecash.MantThread.waitNotify;

/**
 * Created by chengtong on 2018/4/13.
 * 　利用两个线程，对一个整形成员变量进行变化，
 * 一个对其增加，一个对其减少，利用线程间的通信，实现该整形变量0101这样交替的变更。
 */
public class NumberHolder {
    private int number;

    public synchronized void increase()
    {
        if (0 != number)
        {
            try
            {
                System.out.println("increase()释放锁");
                wait();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        // 能执行到这里说明已经被唤醒
        // 并且number为0
        number++;
        System.out.println(number);

        // 通知在等待的线程
        notify();
    }

    public synchronized void decrease()
    {
        if (0 == number)
        {
            try
            {
                System.out.println("decrease()释放锁");
                wait();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

        }

        // 能执行到这里说明已经被唤醒
        // 并且number不为0
        number--;
        System.out.println(number);
        notify();
    }
}
