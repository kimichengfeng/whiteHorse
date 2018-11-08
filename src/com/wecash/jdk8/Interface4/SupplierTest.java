package com.wecash.jdk8.Interface4;

import com.google.common.base.Supplier;

/**
 * Created with IntelliJ IDEA
 * Description:Supplier 接口翻译过来就是提供者,和上面的消费者相反，该接口对应的方法类型为不接受参数，
 * 但是提供一个返回值，通俗的理解为这种接口是无私的奉献者，不仅不要参数，还返回一个值,使用get()方法获得这个返回值
 * User: tong.cheng
 * Date: 2018-11-08
 * Time: 19:30
 */
public class SupplierTest {

    public static void main(String[] args) {
        Supplier<String> getInstance = () -> "HelloWorld!";
        System.out.println(getInstance.get());
        // 控偶值台输出 HelloWorld
    }
}
