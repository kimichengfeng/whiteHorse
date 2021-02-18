package com.wecash.fanxing;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: tong.cheng
 * Date: 2020-07-01
 * Time: 18:15
 */
public class ChaChuTest {
    public static void main(String[] args) throws Exception {
//
//        ArrayList<Integer> list = new ArrayList<Integer>();
//        System.out.println(list.getClass());
//
//        list.add(1);  //这样调用 add 方法只能存储整形，因为泛型类型的实例为 Integer
//        list.getClass().getMethod("add", Object.class).invoke(list, "asd");
//
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }
//        testList();
    }

    /**
     * 真正设计类型检查的是它的引用，因为我们是使用它引用list1来调用它的方法，比如说调用add方法，所以list1引用能完成泛型类型的检查。而引用list2没有使用泛型，所以不行。
     */
//    public static void testList(){
//        ArrayList<String> list1 = new ArrayList();
//        list1.add("1"); //编译通过
//        list1.add(1); //编译错误
//        String str1 = list1.get(0); //返回类型就是String
//
//        ArrayList list2 = new ArrayList<String>();
//        list2.add("1"); //编译通过
//        list2.add(1); //编译通过
//        Object object = list2.get(0); //返回类型就是Object
//
//        new ArrayList<String>().add("11"); //编译通过
//        new ArrayList<String>().add(22); //编译错误
//
//        String str2 = new ArrayList<String>().get(0); //返回类型就是String
//    }
}
