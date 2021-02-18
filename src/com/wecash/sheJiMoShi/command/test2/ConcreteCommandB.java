package com.wecash.sheJiMoShi.command.test2;

/**
 * Created with IntelliJ IDEA
 * Description: ConcreteCommand角色B
 * User: tong.cheng
 * Date: 2018-11-14
 * Time: 21:46
 */
public class ConcreteCommandB implements ICommand {
    private Receiver receiver = null;

    public ConcreteCommandB(Receiver receiver)
    {
        this.receiver = receiver;
    }
    @Override
    public void Execute() {
        this.receiver.DoB();
    }
}
