package com.wecash;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.regex.Pattern;

/**
 * Created by chengtong on 2017/3/22.
 */
public class testNull {

    @org.junit.Test
    public void testNull() {
        test1();
    }
    public int test1(){
        List<String> list = Lists.newArrayList();
        List<String> list1 = Lists.newArrayList("1","2");
        List<String> list2 = Lists.newArrayList("2","3");
        list.addAll(list1);
        list.addAll(list2);
        list.forEach(item->{
            System.out.println(item);
        });
        return 2;
    }
    public static boolean isNumber(String string) {
        if (string == null)
            return false;
        Pattern pattern = Pattern.compile("^-?\\d+(\\.\\d+)?$");
        return pattern.matcher(string).matches();
    }
}
