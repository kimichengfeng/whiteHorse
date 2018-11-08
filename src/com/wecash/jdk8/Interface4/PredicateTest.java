package com.wecash.jdk8.Interface4;

import java.util.function.Predicate;

/**
 * Created with IntelliJ IDEA
 * Description:predicate<T,Boolean> 谓语接口，顾名思义，中文中的‘是’与‘不是’是中文语法的谓语，同样的该接口对应的方法为接收一个参数，
 * 返回一个Boolean类型值，多用于判断与过滤，
 * 当然你可以把他理解成特殊的Funcation<T,R>，但是为了便于区分语义，还是单独的划了一个接口，使用test()方法执行这段行为
 * User: tong.cheng
 * Date: 2018-11-08
 * Time: 19:31
 */
public class PredicateTest {
    public static void main(String[] args) {
        Predicate<Integer> predOdd = integer -> integer % 2 == 1;
        System.out.println(predOdd.test(5));
        //控制台输出 5
    }

}
