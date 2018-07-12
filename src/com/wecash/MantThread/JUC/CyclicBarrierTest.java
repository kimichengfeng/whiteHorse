package com.wecash.MantThread.JUC;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by chengtong on 2018/4/15.
 */
public class CyclicBarrierTest {
    private static CyclicBarrier cyclicBarrier;
    static class CyclicBarrierThread extends Thread{
        public void run() {
            for(int i=0;i<10;i++){
                System.out.println(Thread.currentThread().getName() + "到了");
            }
            //等待
            try {
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args){
        cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("人到齐了，开会吧....");
            }
        });

        for(int i = 0 ; i < 5 ; i++){
            new CyclicBarrierThread().start();
        }
    }
}
