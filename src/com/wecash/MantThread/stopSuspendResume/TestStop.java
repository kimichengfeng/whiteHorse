package com.wecash.MantThread.stopSuspendResume;

/**
 * Created by chengtong on 2018/4/13.
 * 当调用stop()方法时会发生两件事：

 1.即刻停止run()方法中剩余的全部工作，包括在catch或finally语句中，并抛出ThreadDeath异常(通常情况下此异常不需要显示的捕获)，因此可能会导致一些清理性的工作的得不到完成，如文件，数据库等的关闭。

 2.会立即释放该线程所持有的所有的锁，导致数据得不到同步的处理，出现数据不一致的问题。


 */
public class TestStop {
    public static void main(String [] args) throws Exception{
        TestObject testObject = new TestObject();
        Thread t1 = new Thread(){
            public void run(){
                try {
                    testObject.print("1", "2");
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };

        t1.start();
        Thread.sleep(1000);
        t1.stop();
        System.out.println("first : " + testObject.getFirst() + " " + "second : " + testObject.getSecond());
/**
 * 从上面的程序验证结果来看，stop()确实是不安全的。它的不安全主要是针对于第二点：释放该线程所持有的所有的锁。一般任何进行加锁的代码块，都是为了保护数据的一致性，如果在调用thread.stop()后导致了该线程所持有的所有锁的突然释放，那么被保护数据就有可能呈现不一致性，其他线程在使用这些被破坏的数据时，有可能导致一些很奇怪的应用程序错误。
 */

    }
}
