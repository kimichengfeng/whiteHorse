package com.wecash.guava;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: tong.cheng
 * Date: 2018-08-31
 * Time: 18:27
 */
public class ListsTest {

    public static void main(String[] args) {
        List<Integer> integerList = Lists.newArrayList(1,2,3,4,5);
        List<String> stringList = Lists.transform(integerList, new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                return integer.toString()+"t";
            }
        });
        stringList.forEach(item->{
            System.out.println(item);
        });

    }
}
