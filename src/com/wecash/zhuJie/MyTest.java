package com.wecash.zhuJie;

import java.lang.reflect.Method;

/**
 * Created by chengtong on 2017/6/30.
 */
public class MyTest {

    public  static void main(String[] args){
        //获取MyService对应的class对象
        Class clazz = MyService.class;

        //获取类对应方法数组
        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods){
           MyAnnotation myAnnotation =  method.getAnnotation(MyAnnotation.class);
           if(myAnnotation!=null){
               if(myAnnotation.value()){
                   System.out.println(method.getName()+"，注解为"+myAnnotation.value());
               }else{
                   System.out.println(method.getName()+", 注解为"+myAnnotation.value());
               }
           }
        }
    }
}
