package com.wecash.sheJiMoShi.command.test2;

/**
 * Created with IntelliJ IDEA
 * Description:ConcreteCommand角色A:Command接口的实现者，用来执行具体的命令，某些情况下可以直接用来充当Receiver。
 * User: tong.cheng
 * Date: 2018-11-14
 * Time: 21:45
 */
public class ConcreteCommandA implements ICommand {
    private Receiver receiver = null;

    public ConcreteCommandA(Receiver receiver)
    {
        this.receiver = receiver;
    }

    @Override
    public void Execute() {
        this.receiver.DoA();
    }
}
