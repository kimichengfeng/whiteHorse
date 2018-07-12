package com.wecash.FourRef;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
* 当gc（垃圾回收线程）准备回收一个对象时，如果发现它还仅有软引用(或弱引用，或虚引用)指向它，
 就会在回收该对象之前，把这个软引用（或弱引用，或虚引用）加入到与之关联的引用队列（ReferenceQueue）中。
 如果一个软引用（或弱引用，或虚引用）对象本身在引用队列中，就说明该引用对象所指向的对象被回收了。
 当软引用（或弱引用，或虚引用）对象所指向的对象被回收了，那么这个引用对象本身就没有价值了，
 如果程序中存在大量的这类对象（注意，我们创建的软引用、弱引用、虚引用对象本身是个强引用，不会自动被gc回收），就会浪费内存。
 因此我们这就可以手动回收位于引用队列中的引用对象本身。
 * @author chengTong
* @date 2018-06-22 17:07
**/
public class ReferenceQueneDemo {
    public static void main(String[] args){
        /*创建引用队列*/
        ReferenceQueue<SoftReference<int[]>> rq =
                new ReferenceQueue<SoftReference<int[]>>();

        /*创建一个软引用数组，每一个对象都是软引用类型*/
        SoftReference<int[]>[] srArr = new SoftReference[1000];

        for(int i = 0; i < srArr.length; i++){
            srArr[i] = new SoftReference(new int[300000], rq);
        }

        /*（可能）在gc前保留下了三个强引用*/
        int[] arr1 = srArr[30].get();
        int[] arr2 = srArr[60].get();
        int[] arr3 = srArr[90].get();

        /*占用内存，会导致一次gc，使得只有软引用指向的对象被回收*/
        int[] strongRef = new int[200000000];

        Object x;
        int n = 0;
        while((x = rq.poll()) != null){
            int idx = 0;
            while(idx < srArr.length){
                if(x == srArr[idx]){
                    System.out.println("free " + x);
                    srArr[idx] = null; /*手动释放内存*/
                    n++;
                    break;
                }
                idx++;
            }
        }

        /*当然最简单的方法是通过isEnqueued()判断一个软引用方法是否在
         * 队列中，上面的方法只是举例
         int n = 0;
         for(int i = 0; i < srArr.length; i++){
            if(srArr[i].isEnqueued()){
                srArr[i] = null;
                n++;
            }
         }
        */
        System.out.println("recycle  " + n + "  SoftReference Object");
    }
}
