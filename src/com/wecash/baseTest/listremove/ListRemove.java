package com.wecash.baseTest.listremove;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
* ArrayList陷阱
* @author chengTong
* @date 2018-07-05 13:40
**/
public class ListRemove {
    public static void main(String[] args)
    {
        ArrayList<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("b");
        list.add("c");
        list.add("c");
        list.add("c");
        remove(list);

        for (String s : list)
        {
            System.out.println("element : " + s);
        }
    }

    /**
     * 错误的原因：这种最普通的循环写法执行后会发现第二个“b”的字符串没有删掉。
     * @param list
     */
    public static void remove(ArrayList<String> list)
    {
        for (int i = 0; i < list.size(); i++)
        {
            String s = list.get(i);
            if (s.equals("b"))
            {
                list.remove(s);
            }
        }
    }

    /**
     * 错误的原因：这种for-each写法会报出著名的并发修改异常：java.util.ConcurrentModificationException。
     * @param list
     */
    public static void remove2(ArrayList<String> list)
    {
        for (String s : list)
        {
            if (s.equals("b"))
            {
                list.remove(s);
            }
        }
    }

    /**
     * 正确
     * @param list
     */
    public static void remove3(ArrayList<String> list)
    {
        Iterator<String> it = list.iterator();
        while (it.hasNext())
        {
            String s = it.next();
            if (s.equals("b"))
            {
                it.remove();
            }
        }
    }
}
