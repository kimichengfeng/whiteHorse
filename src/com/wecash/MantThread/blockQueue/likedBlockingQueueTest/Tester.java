package com.wecash.MantThread.blockQueue.likedBlockingQueueTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 与ArrayBlockingQueue不同的是，LinkedBlockingQueue内部分别使用了takeLock 和 putLock 对并发进行控制，也就是说，添加和删除操作并不是互斥操作，可以同时进行，这样也就可以大大提高吞吐量。这里再次强调如果没有给LinkedBlockingQueue指定容量大小，其默认值将是Integer.MAX_VALUE，如果存在添加速度大于删除速度时候，有可能会内存溢出，这点在使用前希望慎重考虑。至于LinkedBlockingQueue的实现原理图与ArrayBlockingQueue是类似的，除了对添加和移除方法使用单独的锁控制外，两者都使用了不同的Condition条件对象作为等待队列，用于挂起take线程和put线程。
 */
public class Tester {
    public Tester(){
        // 队列
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>(10);


        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 6; i++) {
            service.submit(new Consumer(queue, "X二代" + i));
            service.submit(new Consumer(queue, "导演" + i));
        }
        for (int i = 0; i < 6; i++) {
            service.submit(new Producer(queue, "黄金酒," + i));
            service.submit(new Producer(queue, "美女演员" + i));
        }
        service.shutdown();
    }

}
