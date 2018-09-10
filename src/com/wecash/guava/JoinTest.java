package com.wecash.guava;

import com.google.common.base.Joiner;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: tong.cheng
 * Date: 2018-08-31
 * Time: 14:26
 */
public class JoinTest {
    //拼接字符串
    private static final Joiner JOINER = Joiner.on("-").useForNull("null");
    public static void main(String[] args) {
        String joinStr = JOINER.join("A","B","C",null);
        StringBuilder builder = new StringBuilder("good");
        String appendStr = JOINER.appendTo(builder, "1","2","3").toString();
        System.out.println(joinStr);
        System.out.println(appendStr);
    }

}
