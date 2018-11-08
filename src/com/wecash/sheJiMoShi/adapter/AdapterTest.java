package com.wecash.sheJiMoShi.adapter;

import com.wecash.sheJiMoShi.adapter.MallarDuck;
import com.wecash.sheJiMoShi.adapter.TurkeyAdapter;
import com.wecash.sheJiMoShi.adapter.WildTurkey;

/**
 * Created by chengtong on 2018/3/13.
 * 1，生活中的适配器
 如果你自己接过水管或者自己接过洗衣机的水管头，你肯定有过类似的体验，无论你怎么接，它都会漏水，然后去店里一问，
 就知道有水管转换接头这么个东西。他可以让两个粗细不同对接不上的水管无缝的对接在一起。

 2，面向对象的适配器
 两套系统的使用接口不一致，但是你想将两套系统给对接起来，你就必须设计一个适配器将两个接口对接起来，这样你就可以在一个系统中调用另外一个系统的实现方案。
 　　适配器模式定义：将一个类的接口，转换成客户期望的另一个接口。适配器让原本接口不兼容的类可以合作无间。

 作者：我和哈
 链接：https://www.jianshu.com/p/354aa326593a
 來源：简书
 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class AdapterTest {
    public static void main(String[] args) {
        MallarDuck duck = new MallarDuck();
        duck.quak();
        duck.fly();
        System.out.println("--------------");
        WildTurkey turkey = new WildTurkey();
        TurkeyAdapter adapter = new TurkeyAdapter(turkey);
        adapter.quak();
        adapter.fly();

    }
}
