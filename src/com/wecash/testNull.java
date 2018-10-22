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
       String objects = null;
       if(objects instanceof String){
           System.out.println("ok");
       }
        System.out.println("no");
        return 2;
    }
    public static boolean isNumber(String string) {
        if (string == null)
            return false;
        Pattern pattern = Pattern.compile("^-?\\d+(\\.\\d+)?$");
        return pattern.matcher(string).matches();
    }
}
