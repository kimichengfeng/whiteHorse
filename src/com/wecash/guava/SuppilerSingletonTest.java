package com.wecash.guava;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA
 * Description: 利用Suppliers.memoize实现单例
 * User: tong.cheng
 * Date: 2018-10-30
 * Time: 12:01
 */
public class SuppilerSingletonTest {
    class HeavyObject{
        public HeavyObject() {
            System.out.println("being created");
        }
    }

    class ObjectSuppiler implements Supplier<HeavyObject> {

        @Override
        public HeavyObject get() {
            return new HeavyObject();
        }
    }

    /**
     * 每次都new一次
     */
    @Test
    public void testNotSingleton(){
        Supplier<HeavyObject> notCached = new ObjectSuppiler();
        for(int i=0;i<5;i++){
            notCached.get();
        }
    }

    /**
     * 单例
     */
    @Test
    public void testSingleton(){
        Supplier<HeavyObject> notCached = new ObjectSuppiler();
        Supplier<HeavyObject> cachedSupplier = Suppliers.memoize(notCached);
        for(int i=0;i<5;i++){
            cachedSupplier.get();
        }
    }
}
