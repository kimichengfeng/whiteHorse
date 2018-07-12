package com.wecash.MantThread.lockTest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chengtong on 2017/10/26.
 * 这种排斥了 写/写，读/写 读/读。
 */
public class SynchronizedMap<K,V> {
    private final Map<K,V> map=new HashMap<K, V>();


    public synchronized void put(K k,V v){
        map.put(k, v);
    }

    public synchronized V get(K k){
        return map.get(k);
    }

}
