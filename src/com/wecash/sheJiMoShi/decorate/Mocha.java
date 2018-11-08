package com.wecash.sheJiMoShi.decorate;
/**
 * 摩卡
 * 装饰者
 * @author boce
 *
 */
public class Mocha extends CondimentDecorator {
    Beverage beverage;
    public  Mocha(Beverage beverage) {
		// TODO Auto-generated constructor stub
    	this.beverage = beverage;
    	
	}
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return beverage.getDescription() + ",Mocha";
	}
	@Override
	public double cost() {
		return .20+beverage.cost();
	}
	
}
