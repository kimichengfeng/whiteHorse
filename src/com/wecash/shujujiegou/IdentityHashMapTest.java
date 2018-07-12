package com.wecash.shujujiegou;

import java.util.HashMap;
import java.util.IdentityHashMap;

/**
* IdentityHashMap是一个特殊的Map实现！此类实现Map接口时，它有意违反Map的通常规范：IdentityHashMap要求两个key严格相等才认为两个key相等。
 * IdentityHashMap不保证key-value对之间的顺序，更不能保证它们的顺序随时间的推移保持不变。
* @author chengTong
* @date 2018-06-25 10:46
**/
public class IdentityHashMapTest {
    public static void main(String[] args) {
        IdentityHashMap idenmap = new IdentityHashMap();
        idenmap.put(new String("语文"), 80);
        idenmap.put(new String("语文"), 89);

        idenmap.put("java", 80);
        idenmap.put("java", 80);
        System.out.println(idenmap);
        //{语文=80, java=80, 语文=89}

        HashMap hashMap = new HashMap();
        hashMap.put(new String("语文"), 80);
        hashMap.put(new String("语文"), 89);

        hashMap.put("java", 80);
        hashMap.put("java", 80);
        System.out.println(hashMap);
    }
}
