package com.wecash;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
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
            String a = new String("bbbb");
            String b = new String("bbbb");
//           if (a == b) {
//               System.out.println("a==b 1");
//           }
//           if (a != null && a.equals(b)){
//               System.out.println("a==b 2");
//           }
            System.out.println(Objects.equals(a,b));

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
