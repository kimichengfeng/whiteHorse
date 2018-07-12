package com.wecash.sheJiMoShi.model.strategy;

import com.wecash.sheJiMoShi.inter.strategy.FlyBehavior;
import com.wecash.sheJiMoShi.inter.strategy.QuackBehavior;

/**
 * 鸭子抽象类
 * @author boce
 *
 */

public abstract class Duck{
	FlyBehavior flyBehavior;
	QuackBehavior quackBehavior;//为行为接口类型声明两个引用变量
	//抽象方法
	public abstract void display();
	
	public void performFly(){
		flyBehavior.fly();
	}
	/**
	 * 委托给行为类
	 */
	public void performQuack(){
		quackBehavior.quack();
	}
	
	/**
	 * 动态设定行为
	 */
	public void setFlyBehavior(FlyBehavior fb){
		flyBehavior = fb;
	}
}