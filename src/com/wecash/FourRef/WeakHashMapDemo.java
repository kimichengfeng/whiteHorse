package com.wecash.FourRef;

import java.util.WeakHashMap;

/**
* WeakHahsMap 的实现原理简单来说就是HashMap里面的条目 Entry继承了 WeakReference，
 * 那么当 Entry 的 key 不再被使用（即，引用对象不可达）且被 GC 后，
 * 那么该 Entry 就会进入到 ReferenceQueue 中。
 * 当我们调用WeakHashMap 的get和put方法会有一个副作用，即清除无效key对应的Entry。
 * 这个过程就和上面的代码很类似了，首先会从引用队列中取出一个Entry对象，
 * 然后在HashMap中查找这个Entry对象的位置，最后把这个 Entry 从 HashMap中删除，
 * 这时key和value对象都被回收了。重复这个过程直到队列为空。


 * @author chengTong
* @date 2018-06-22 17:36
**/
public class WeakHashMapDemo {
    public static void main(String[] args){

        WeakHashMap<String, byte[]> whm = new WeakHashMap<String, byte[]>();
        String s1 = new String("s1");
        String s2 = new String("s2");
        String s3 = new String("s3");

        whm.put(s1, new byte[100]);
        whm.put(s2, new byte[100]);
        whm.put(s3, new byte[100]);

        s2 = null;
        s3 = null;

        /*此时可能还未执行gc,所以可能还可以通过仅有弱引用的key找到value*/
        System.out.println(whm.get("s1"));
        System.out.println(whm.get("s2"));
        System.out.println(whm.get("s3"));

        System.out.println("-------------------");

        /*执行gc,导致仅有弱引用的key对应的entry(包括value)全部被回收*/
        System.gc();
        System.out.println(whm.get("s1"));
        System.out.println(whm.get("s2"));
        System.out.println(whm.get("s3"));
    }
}
