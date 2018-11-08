package com.wecash.sheJiMoShi.decorate;
/**
 * 抽象类，可被装饰的类
 * @author boce
 *
 */
public abstract class Beverage {
	String decription = "unknown beverage";

	public String getDescription() {
		return decription;
	}

	public abstract double cost();
	
}
