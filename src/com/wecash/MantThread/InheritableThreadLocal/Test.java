package com.wecash.MantThread.InheritableThreadLocal;
/**
* 
* @author chengTong
* @date 2018-06-25 19:56
 * 参考： https://www.jb51.net/article/126122.htm
**/
public class Test {
    public static void main(String[] args) {
        try {
            for(int i=0;i<10;i++) {
                System.out.println("主线程中值："+Tool.t.get());
                Thread.sleep(100);
            }
            Thread.sleep(5000);
            MyThread thread=new MyThread();
            thread.start();

        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
