package com.wecash.sheJiMoShi.state;

/**
 * Created with IntelliJ IDEA
 * Description: ③把炸药放置在炮楼底下
 * User: tong.cheng
 * Date: 2018-11-02
 * Time: 18:21
 */
public class PuttingState extends LinkState{

    @Override
    public void carry() {
        System.out.println("小Y:还在敌方区，无法再扛炸药包");
    }

    @Override
    public void put() {
        System.out.println("小Y:好险，成功把放置炸药包");
    }

    @Override
    public void goaway() {
        super.context.setLinkState(Context.goawayingState);
        super.context.getLinkState().goaway();
    }

    @Override
    public void ignite() {
        System.out.println("小Y:还没逃离敌方区，无法引爆炸药");
    }
}
