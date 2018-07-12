package com.wecash.zhuJie;

/**
 * Created by chengtong on 2017/6/30.
 */
public class MyService {

    @MyAnnotation(value=true)
    public void selectDb(){
        System.out.println("查询数据库");
    }
    @MyAnnotation(value=false)
    public void insertDb(){
        System.out.println("插入数据库");
    }
}
