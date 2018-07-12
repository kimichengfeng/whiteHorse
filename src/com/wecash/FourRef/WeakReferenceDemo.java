package com.wecash.FourRef;

import java.lang.ref.WeakReference;

/**
 弱引用的创建要借助于java.lang.ref包下的WeakReferenc类。当JVM进行垃圾回收时，无论内存是否充足，都会回收仅被弱引用关联的对象。
 由于垃圾回收器是一个优先级很低的线程，因此不一定会很快发现那些被弱引用指向的对象。

 可用场景：
 Java源码中的java.util.WeakHashMap中的key就是使用弱引用，我的理解就是，一旦我不需要某个引用，
 JVM会自动帮我处理它，这样我就不需要做其它操作。

 参考：http://www.importnew.com/21206.html

* @author chengTong
* @date 2018-06-22 15:26
**/
public class WeakReferenceDemo {
    public static void main(String[] args){

        /*若引用对象中指向了一个长度为1000个元素的整形数组*/
        WeakReference<String[]> weakReference =
                new WeakReference<String[]>(new String[1000]);

        /*未执行gc,目前仅被弱引用指向的对象还未被回收，所以结果不是null*/
        System.out.println(weakReference.get());
//        String[] test = weakReference.get();
        /*执行一次gc,即使目前JVM的内存够用,但还是回收仅被弱引用指向的对象*/
        System.gc();

        System.out.println(weakReference.get());
//        System.out.println(weakReference);
    }
}
