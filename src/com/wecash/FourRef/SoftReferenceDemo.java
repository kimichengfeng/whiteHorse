package com.wecash.FourRef;

import java.lang.ref.SoftReference;

/**
 软引用的创建要借助于java.lang.ref包下的SoftReferenc类。
 当JVM进行垃圾回收时，只有在内存不足的时候JVM才会回收仅有软引用指向的对象所占的空间。

 可用场景：
 创建缓存的时候，创建的对象放进缓存中，当内存不足时，JVM就会回收早先创建的对象。
 PS：图片编辑器，视频编辑器之类的软件可以使用这种思路。

 * 虚拟机参数配置
 * -Xms256m
 * -Xmx1024m
* @author chengTong
* @date 2018-06-22 15:17
**/
public class SoftReferenceDemo {
    public static void main(String[] args){

        /*软引用对象中指向了一个长度为300000000个元素的整形数组*/
        SoftReference<int[]> softReference =
                new SoftReference<int[]>(new int[300000000]);

        /*主动调用一次gc,由于此时JVM的内存够用，此时softReference引用的对象未被回收*/
        System.gc();
        System.out.println(softReference.get());

        /*消耗内存,会导致一次自动的gc,此时JVM的内存不够用
         *就回收softReference对象中指向的数组对象*/
        int[] strongReference = new int[100000000];

        System.out.println(softReference.get());
    }
}
