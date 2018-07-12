package com.wecash.MantThread.InheritableThreadLocal;
/**
* 
* @author chengTong
* @date 2018-06-25 19:55
**/
public class MyThread  extends Thread {
    @Override
    public void run() {
        try {
            for(int i=0;i<10;i++) {
                System.out.println("在线程A中："+Tool.t.get());
                sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
