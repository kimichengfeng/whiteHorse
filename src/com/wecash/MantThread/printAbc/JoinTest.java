package com.wecash.MantThread.printAbc;

import lombok.SneakyThrows;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: tong.cheng
 * Date: 2020-08-07
 * Time: 15:51
 */
public class JoinTest {

    public static void main(String[] args) {
        for(int i=0;i<6;i++) {
            printABC();
        }

    }

    private static void printABC() {
        final Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.print("a");
            }
        });
        final Thread thread2 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                thread1.join();
                System.out.print("b");
            }
        });
        final Thread thread3 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                thread2.join();
                System.out.println("c");
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
