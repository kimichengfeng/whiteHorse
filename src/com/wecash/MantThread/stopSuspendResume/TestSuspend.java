package com.wecash.MantThread.stopSuspendResume;

/**
 * Created by chengtong on 2018/4/13.
 * suspend()和resume()必须要成对出现，否则非常容易发生死锁。
 因为suspend方法并不会释放锁，如果使用suspend的目标线程对一个重要的系统资源持有锁，
 那么没任何线程可以使用这个资源直到要suspend的目标线程被resumed，
 如果一个线程在resume目标线程之前尝试持有这个重要的系统资源锁再去resume目标线程，
 这两条线程就相互死锁了，也就冻结线程。
 */
public class TestSuspend {
    public static void main(String [] args) throws Exception{
        TestObject2 testObject = new TestObject2();
        Thread t1 = new Thread(){
            public void run(){
                testObject.print();
            }
        };
        t1.setName("A");
        t1.start();
        Thread.sleep(1000);

        Thread t2 = new Thread(){
            public void run(){
                System.out.println("B已启动,但进入不到print方法中");
                testObject.print();
            }
        };
        t2.setName("B");
        t2.start();



    }
}
