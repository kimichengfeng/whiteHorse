package com.wecash.guava;

import com.google.common.base.MoreObjects;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: tong.cheng
 * Date: 2018-08-31
 * Time: 16:01
 */
public class MoreObjectsTest {

    public static void main(String[] args) {
        /**
         static <T> T	firstNonNull(T first, T second)
         依次判断First,second,返回第一个不为空的对象。如果都为空抛出空NullPointerException
         */
        System.out.println(MoreObjects.firstNonNull(null,"b"));
    }

}
