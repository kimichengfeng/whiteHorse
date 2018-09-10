package com.wecash.MantThread.callTest;

import java.util.concurrent.Callable;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: tong.cheng
 * Date: 2018-08-31
 * Time: 14:51
 */
public class CallSimpleTest {

    public static void main(String[] args) {

        callTest();
        new Runnable() {
            @Override
            public void run() {
                System.out.println("run:"+Thread.currentThread());
            }
        }.run();

    }
    private static void callTest() {
        System.out.println("main:"+Thread.currentThread());
        try {
            String str = new Callable<String>() {
                @Override
                public String call() throws Exception {
                    System.out.println("call:"+Thread.currentThread());
                    return "call --";
                }
            }.call();
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
