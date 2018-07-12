package com.wecash.shujujiegou;

import java.util.HashMap;

/**
 * Created by chengtong on 2017/11/5.
 */
public class HashMapTest {
    public static void main(String[] args){
    HashMap hashMap = new HashMap();
    hashMap.put("a","a1");
    String hash = (String) hashMap.get("a");
    System.out.println(hash);
    }

}
