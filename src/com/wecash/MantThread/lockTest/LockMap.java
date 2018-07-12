package com.wecash.MantThread.lockTest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by chengtong on 2017/10/26.
 这种排斥了 写/写 读/写 。
 但读/读没有排斥。
 也是就说读与读是多个线程可以同时读的。----可以做为读多写少的应用。
 */
public class LockMap<K, V> {

    private final Map<K, V> map = new HashMap<K, V>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock r = lock.readLock();
    private final Lock w = lock.writeLock();


    public void put(K key, V value) {
        w.lock();
        try {
            map.put(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            w.unlock();
        }

    }

    public V get(K key) {
        r.lock();
        try {
            return map.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            r.unlock();
        }
        return null;
    }


}

