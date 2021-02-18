package com.wecash.sheJiMoShi.command.test2;

/**
 * Created with IntelliJ IDEA
 * Description:客户端调用
 * User: tong.cheng
 * Date: 2018-11-14
 * Time: 21:49
 */
public class Client {
    public Client()
    {
        Receiver receiver = new Receiver();
        Invoker invoker = new Invoker();
        invoker.SetCommand(new ConcreteCommandA(receiver));
        invoker.RunCommand();
//        invoker.SetCommand(new ConcreteCommandB(receiver));
//        invoker.RunCommand();
    }
}
