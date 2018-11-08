package com.wecash.sheJiMoShi.state;

/**
 * Created with IntelliJ IDEA
 * Description: ②扛上炸药玩命跑路到炮楼底下
 * User: tong.cheng
 * Date: 2018-11-02
 * Time: 18:12
 */
public class CarryingState extends LinkState {

    @Override
    public void carry() {
        System.out.println("小Y:报告排长，我已跑到炮楼底下");
    }

    @Override
    public void put() {
        super.context.setLinkState(Context.puttingState);
        super.context.getLinkState().put();
    }

    @Override
    public void goaway() {
        System.out.println("小Y:还没放置炸药成功，撤离失败");
    }

    @Override
    public void ignite() {
        System.out.println("小Y:还没放置炸药成功，引爆失败");
    }
}
