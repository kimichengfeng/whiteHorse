package com.wecash.jdk8.Interface4;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: tong.cheng
 * Date: 2019-09-20
 * Time: 11:00
 */
public class OptionTest {

    public static void main(String[] args) {
        System.out.println(Optional.ofNullable("demo").orElse("a")); //demo
        System.out.println(Optional.ofNullable(null).orElse("a")); //a
        //或者 使用supplier生产
        System.out.println(Optional.ofNullable(null).orElseGet(() -> "abc")); //abc
        BigDecimal num = new BigDecimal("10");
        String str = Optional.ofNullable(null).map( x -> String.valueOf(x)).orElse(null);
        System.out.println(str);

    }

}
