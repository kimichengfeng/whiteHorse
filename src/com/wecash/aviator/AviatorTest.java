package com.wecash.aviator;

import com.googlecode.aviator.AviatorEvaluator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: tong.cheng
 * Date: 2018-10-22
 * Time: 17:51
 */
public class AviatorTest {
    public static void main(String[] args) {
//        testMap();
        testSeq();
    }
    public static  void testMap(){
        String yourName = "Michael";
        Map<String, Object> env = new HashMap<String, Object>();
        env.put("yourName", yourName);
        String result = (String) AviatorEvaluator.execute(" 'hello ' + yourName ", env,true);
        System.out.println(result);  // hello Michael
    }

    /**
     * 求长度: count(list)
     * 求和: reduce(list,+,0), reduce函数接收三个参数,第一个是seq,第二个是聚合的函数,如+等,第三个是聚合的初始值
     * 过滤: filter(list,seq.gt(9)), 过滤出list中所有大于9的元素并返回集合; seq.gt函数用于生成一个谓词,表示大于某个值
     * 判断元素在不在集合里: include(list,10)
     * 排序: sort(list)
     * 遍历整个集合: map(list,println), map接受的第二个函数将作用于集合中的每个元素,这里简单地调用println打印每个元素
     */
    public static void testSeq(){
        Map<String, Object> env = new HashMap<String, Object>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(3);
        list.add(20);
        list.add(10);
        env.put("list", list);
        Object result = AviatorEvaluator.execute("count(list)", env);
        System.out.println(result);  // 3
        result = AviatorEvaluator.execute("reduce(list,+,0)", env);
        System.out.println(result);  // 33
        result = AviatorEvaluator.execute("filter(list,seq.gt(9))", env);
        System.out.println(result);  // [10, 20]
        result = AviatorEvaluator.execute("include(list,10)", env);
        System.out.println(result);  // true
        result = AviatorEvaluator.execute("sort(list)", env);
        System.out.println(result);  // [3, 10, 20]
        AviatorEvaluator.execute("map(list,println)", env);
    }
}
