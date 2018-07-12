package com.wecash.MantThread.AQSTest;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by chengtong on 2018/4/11.
 案例：设计一个AQS同步器，该工具在同一时刻，只能有两个线程能够访问，其他的线程阻塞。

 设计分析：针对上述案例，我们可以这样定义AQS，设定一个初始状态为2，
 每一个线程获取一次就-1，正确的状态为：0，1，2，0表示新的线程获取同步状态时阻塞。
 由于资源数量大与1，需要实现tryAcquireShared和tryReleaseShared方法。

 作者：miaoLoveCode
 链接：https://www.jianshu.com/p/df0d7d6571de
 來源：简书
 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class LockInstance implements Lock{
    private final Sync sync = new Sync(2);

    private static final class Sync extends AbstractQueuedSynchronizer {
        Sync(int state) {
            if (state <= 0) {
                throw new IllegalArgumentException("count must large than 0");
            }
            setState(state);
        }

        @Override
        public int tryAcquireShared(int arg) {
            for (;;) {
                System.out.println("try acquire....");
                int current = getState();
                int now = current - arg;
                if (now < 0 || compareAndSetState(current, now)) {
                    return now;
                }
            }
        }

        @Override
        public boolean tryReleaseShared(int arg) {
            for(;;) {
                System.out.println("try release....");
                int current = getState();
                int now = current + arg;
                if (compareAndSetState(current, now)) {
                    return true;
                }
            }
        }
    }

    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquireShared(1) >= 0;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireSharedNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.tryReleaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
