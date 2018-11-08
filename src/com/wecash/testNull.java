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
        A test = new A();
       Object ob = test;
       A ob1 = (A) ob;
        System.out.println(ob1);
        return 2;
    }
    class A{
        String a="1";
        String b="2";

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }

        @Override
        public String toString() {
            return "A{" +
                    "a='" + a + '\'' +
                    ", b='" + b + '\'' +
                    '}';
        }
    }
    public static boolean isNumber(String string) {
        if (string == null)
            return false;
        Pattern pattern = Pattern.compile("^-?\\d+(\\.\\d+)?$");
        return pattern.matcher(string).matches();
    }
}
