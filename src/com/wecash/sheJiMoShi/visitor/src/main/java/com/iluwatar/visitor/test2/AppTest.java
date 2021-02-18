package com.wecash.sheJiMoShi.visitor.src.main.java.com.iluwatar.visitor.test2;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: tong.cheng
 * Date: 2019-01-31
 * Time: 20:03
 */
public class AppTest {
    public static void main(String[] args) {

        ComputerPart computer = new Computer();
        computer.accept(new ComputerPartDisplayVisitor());

        ComputerPart keyboard = new Keyboard();
        keyboard.accept(new ComputerPartDisplayVisitor());
    }
}
