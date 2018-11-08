package com.wecash.sheJiMoShi.state;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: tong.cheng
 * Date: 2018-11-02
 * Time: 18:23
 */
public class Client {
    public static void main(String[] args) {
        Context context = new Context();
        context.setLinkState(Context.carryingState);
        context.carry();
        context.ignite();
        context.put();
        context.goaway();
        context.ignite();
    }
}
