package com.wecash.MantThread.joinTest;

/**
 * Created by chengtong on 2017/10/23.
 */
public class ThreadJoin2 {
    public static int a = 0;

    public static void main(String[] args){
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run(){
                for(int i=0;i<5;i++)
                    a++;
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a);

    }
}
