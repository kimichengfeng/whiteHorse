package com.wecash.sheJiMoShi.model.strategy;


import com.wecash.sheJiMoShi.impl.strategy.FlyNoWay;
import com.wecash.sheJiMoShi.impl.strategy.FlyWithWings;
import com.wecash.sheJiMoShi.impl.strategy.Quack;

/**
 * 绿头鸭实体类
 * 策略模式：定义了算法族，分别封装起来，让他们之间可以互相转换，此模式让算法的变化独立于使用算法的客体
 * 设计原则：多用组合，少用继承
 * @author boce
 *
 */
public class MallardDuck extends Duck {
	public MallardDuck() {
        quackBehavior = new Quack();//使用Quack类处理呱呱叫行为
        flyBehavior = new FlyNoWay();
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		quackBehavior.quack();
		
	}
	
	public static void main(String[] args){
		Duck mallardDuck = new MallardDuck();
		mallardDuck.performQuack();
		mallardDuck.performFly();
		//改变行为
		mallardDuck.setFlyBehavior(new FlyWithWings());
		mallardDuck.performFly();
	}
}
