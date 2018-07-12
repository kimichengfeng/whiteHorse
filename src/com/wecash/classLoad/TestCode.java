package com.wecash.classLoad;

import java.net.URL;

/**
 * Created by chengtong on 2017/12/14.
 */
public class TestCode {
    public static void main(String[] args){
        TestCode tc = new TestCode();
        tc.testcode();

    }
    public String testcode(){
        //方法1
        URL url1 = ClassLoader.getSystemResource("business.properties");
        System.out.println("url1:\t" + (url1 == null ? "null" : url1.getPath()));

        URL url1withSlash = ClassLoader.getSystemResource("/business.properties");
        System.out.println("url1/:\t" + (url1withSlash == null ? "null" : url1withSlash.getPath()));


        //方法2
        ClassLoader classLoader2 = this.getClass().getClassLoader();
        URL url2 = classLoader2.getResource("business.properties");
        System.out.println("url2:\t" + (url2 == null ? "null" : url2.getPath())+";classLoader is:"+classLoader2.toString());

        URL url2withSlash = classLoader2.getResource("/business.properties");
        System.out.println("url2/:\t" + (url2withSlash == null ? "null" : url2withSlash.getPath()));

        //方法3
        URL url3 = this.getClass().getResource("business.properties");
        System.out.println("url3:\t" + (url3 == null ? "null" : url3.getPath()));

        URL url3withSlash = this.getClass().getResource("/business.properties");
        System.out.println("url3/:\t" + (url3withSlash == null ? "null" : url3withSlash.getPath()));

        //方法4
        ClassLoader classLoader4 = Thread.currentThread().getContextClassLoader();
        URL url4 = classLoader4.getResource("business.properties");
        System.out.println("url4:\t" + (url4 == null ? "null" : url4.getPath())+";classLoader is:"+classLoader4.toString());

        URL url4withSlash = classLoader4.getResource("/business.properties");
        System.out.println("url4/:\t" + (url4withSlash == null ? "null" : url4withSlash.getPath()));
        return "OK";
    }

}
