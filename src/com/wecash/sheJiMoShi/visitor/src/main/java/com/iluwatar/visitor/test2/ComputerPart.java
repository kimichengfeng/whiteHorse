package com.wecash.sheJiMoShi.visitor.src.main.java.com.iluwatar.visitor.test2;

/**
 * Created with IntelliJ IDEA
 * Description:定义一个表示元素的接口。
 * User: tong.cheng
 * Date: 2019-01-31
 * Time: 19:59
 */
public interface ComputerPart {
    public void accept(ComputerPartVisitor computerPartVisitor);
}
