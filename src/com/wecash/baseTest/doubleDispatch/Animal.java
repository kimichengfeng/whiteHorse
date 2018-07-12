package com.wecash.baseTest.doubleDispatch;
/**
* 
* @author chengTong
* @date 2018-07-10 11:08
**/
public class Animal {
    static void bark(){
        System.out.println("animal's bark");
    }
    private void barkPri(){

    }
    static void barkStaic(){
        System.out.println("animal's barkStaic");
    }
}
