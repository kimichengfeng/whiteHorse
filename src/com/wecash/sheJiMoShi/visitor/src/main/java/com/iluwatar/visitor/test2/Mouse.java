package com.wecash.sheJiMoShi.visitor.src.main.java.com.iluwatar.visitor.test2;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: tong.cheng
 * Date: 2019-01-31
 * Time: 20:02
 */
public class Mouse  implements ComputerPart{
    @Override
    public void accept(ComputerPartVisitor computerPartVisitor) {
        computerPartVisitor.visit(this);
    }
}
