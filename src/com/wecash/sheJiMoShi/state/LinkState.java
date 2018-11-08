package com.wecash.sheJiMoShi.state;


/**
 * Created with IntelliJ IDEA
 * Description: ①先定义炸炮楼每个阶段要做的事情
 * User: tong.cheng
 * Date: 2018-11-02
 * Time: 17:52
 */
public abstract class LinkState {
    protected Context context;
    public void setContext(Context context){
        this.context = context;
    }
    //扛炸药包
    public abstract void carry();
    //放置炸药包
    public abstract void put();
    //跑路
    public abstract void goaway();
    //点燃炸药包
    public abstract void ignite();
}
