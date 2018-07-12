package com.wecash.zhuJie.filed;

import java.lang.reflect.Field;

/**
* 
* @author chengTong
* @date 2018-04-25 14:52
**/
public class TestAnnotation {
    @MyFieldAnnotation(desc = "The Class Field", uri = "com.sgl.annotation#id")
    private String id;

    public static void main(String[] args) throws Exception {
        Class<TestAnnotation> clazz = TestAnnotation.class;
        // 获得字段注解
        Field field = clazz.getDeclaredField("id");
        MyFieldAnnotation myFieldAnnotation = field.getAnnotation(MyFieldAnnotation.class);
        System.out.println(myFieldAnnotation.desc() + "+" + myFieldAnnotation.uri());
    }
}
