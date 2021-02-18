package com.wecash.jdk8;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: tong.cheng
 * Date: 2020-04-24
 * Time: 15:21
 */
public class ComparatorTest {
    public static void main(String[] args) {
        ArrayList<Integer> list = Lists.newArrayList(0,3,6,12,18,24);
        list.sort((Integer u1, Integer u2) -> u2.compareTo(u1));
//        Collections.sort(list, Comparator.comparing(User::getAge).reversed());
        list.add(0,list.get(list.size()-1));
        list.remove(list.size()-1);
        list.forEach(System.out::println);
        BigDecimal a= new BigDecimal("30");
        a.compareTo(null);

    }


}
