package com.wecash.sheJiMoShi.dongTaiDaiLi.testClassLoad;
/**
* 
* @author chengTong
* @date 2018-06-25 16:52
**/
public class Test {
    public static void main(String[] args) throws Exception {
        String path = "/Users/chengtong/IdeaProjects/MyTest/out/production/MyTest/com/wecash/sheJiMoShi/dongTaiDaiLi/testClassLoad/desc/";
        CustomClassLoader loader = new CustomClassLoader("Test");
        loader.setBasPath(path);//指定自定义类加载器加载路径
        Class<?> clazz = loader.findClass("Car"); //指定加载Car类
        System.out.println(clazz.getClassLoader());//输出加载类Car的加载器
        Object object = clazz.newInstance();//创建Car类对象，会调用构造方法
    }
}
