package com.wecash.sheJiMoShi.impl.adapter;

import com.wecash.sheJiMoShi.inter.adapter.Duck;
import com.wecash.sheJiMoShi.inter.adapter.Turkey;

/**
 * Created by chengtong on 2018/3/13.
 * 到这里，分别有一个Duck具体类和一个WildTurkey具体类，
 * 那么我就需要一个adapter来将wildTurkey转换成Duck接口。
 */
public class TurkeyAdapter implements Duck {
    private Turkey turkey;
    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    @Override
    public void quak() {
        turkey.gobble();
    }
    //火鸡没有鸭子飞得远，那么我们就可以飞五次来假装成和鸭子一样的距离。
    @Override
    public void fly() {
        for(int i = 0; i < 5 ; i++){
            turkey.fly();
        }
    }
}
