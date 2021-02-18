package com.wecash.guava;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: tong.cheng
 * Date: 2019-07-31
 * Time: 16:02
 */
public class InterablesTest {

    public static void main(String[] args) {
        List<String> list = Lists.newArrayList("a","b","c","d");
        String str = Iterables.getFirst(list, null);
        System.out.println(str);
    }
}
