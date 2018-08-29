package com.wecash;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
        try {
            System.out.println( isNumber("111.00sh"));
            System.out.println("test");
            return 1;
        } catch (Exception e) {
            System.out.println(ExceptionStackTraceUtil.getExceptionStackTrace(e));
        }finally {
            System.out.println("ok");
        }
        return 2;
    }
    public static boolean isNumber(String string) {
        if (string == null)
            return false;
        Pattern pattern = Pattern.compile("^-?\\d+(\\.\\d+)?$");
        return pattern.matcher(string).matches();
    }
}
