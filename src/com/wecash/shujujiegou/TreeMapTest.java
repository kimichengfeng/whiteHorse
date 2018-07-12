package com.wecash.shujujiegou;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * Created by chengtong on 2018/3/6.
 */
public class TreeMapTest {
    public static void main(String[] args) {
        // 初始化随机种子
        Random r = new Random();
        // 新建TreeMap
        TreeMap tmap = new TreeMap();
        // 添加操作
        tmap.put("1",r.nextInt(10));
        tmap.put("3",r.nextInt(10));
        tmap.put("2",r.nextInt(10));
        System.out.printf("\n ---- testTreeMapOridinaryAPIs ----\n");
        // 打印出TreeMap
        System.out.printf("%s\n",tmap);

        // 通过Iterator遍历key-value
        Iterator iter = tmap.entrySet().iterator();
        while(iter.hasNext())

        {
            Map.Entry entry = (Map.Entry) iter.next();
            System.out.printf("next : %s - %s\n", entry.getKey(), entry.getValue());
        }

    }

}
