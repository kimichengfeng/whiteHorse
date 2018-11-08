package com.wecash.sheJiMoShi.decorate;

/**
 * 饮料类-浓缩咖啡
 * @author boce
 *
 */
public class Espresso extends Beverage {
   public  Espresso() {
	// TODO Auto-generated constructor stub
	   decription = "Espresso";
}
	@Override
	public double cost() {
		// TODO Auto-generated method stub
		return 1.99;
	}

}
