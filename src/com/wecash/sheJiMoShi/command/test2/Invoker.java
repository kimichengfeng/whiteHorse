package com.wecash.sheJiMoShi.command.test2;

/**
 * Created with IntelliJ IDEA
 * Description:Invoker角色:命令的请求者，是命令模式中最重要的角色。这个角色用来对各个命令进行控制
 * User: tong.cheng
 * Date: 2018-11-14
 * Time: 21:48
 */
public class Invoker {
    private ICommand command = null;
    //设置命令
    public void SetCommand(ICommand command){
        this.command = command;
    }
    //执行命令
    public void RunCommand(){
        command.Execute();
    }
}
