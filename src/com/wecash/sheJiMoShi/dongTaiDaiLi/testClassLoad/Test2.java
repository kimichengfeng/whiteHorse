package com.wecash.sheJiMoShi.dongTaiDaiLi.testClassLoad;

import java.io.File;

/**
* 
* @author chengTong
* @date 2018-06-25 16:51
**/
public class Test2 {
    public static void main(String[] args) throws Exception {
        String path = "/Users/chengtong/IdeaProjects/MyTest/out/production/MyTest/com/wecash/sheJiMoShi/dongTaiDaiLi/testClassLoad/";
        File src = new File(path+"Car.class");
        File des = new File(path+"desc/Car.class");
        EncryptUtil.encrypt(src, des);//加密
    }
}
