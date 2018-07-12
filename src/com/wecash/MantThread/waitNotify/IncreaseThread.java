package com.wecash.MantThread.waitNotify;

/**
 * Created by chengtong on 2018/4/13.
 */
public class IncreaseThread extends Thread {
    private NumberHolder numberHolder;

    public IncreaseThread(NumberHolder numberHolder)
    {
        this.numberHolder = numberHolder;
    }
    @Override
    public void run()
    {
        for (int i = 0; i < 20; ++i)
        {
            // 进行一定的延时
//            try
//            {
//                Thread.sleep((long) Math.random() * 1000);
//            }
//            catch (InterruptedException e)
//            {
//                e.printStackTrace();
//            }

            // 进行增加操作
            numberHolder.increase();
        }
    }

}
