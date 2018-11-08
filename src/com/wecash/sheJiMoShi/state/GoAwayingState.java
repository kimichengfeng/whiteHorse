package com.wecash.sheJiMoShi.state;

/**
 * Created with IntelliJ IDEA
 * Description:④放置好炸药还不赶紧跑路，等被炸药炸啊？
 * User: tong.cheng
 * Date: 2018-11-02
 * Time: 18:22
 */
public class GoAwayingState extends LinkState{

    @Override
    public void carry() {
        System.out.println("小Y:已经逃离，无法再扛炸药包");
    }

    @Override
    public void put() {
        System.out.println("小Y:已经逃离，无法进行炸药包放置");
    }

    @Override
    public void goaway() {
        System.out.println("小Y:呼~~，终于捡回条小命");
    }

    @Override
    public void ignite() {
        super.context.setLinkState(Context.ignitingState);
        super.context.getLinkState().ignite();
    }
}
