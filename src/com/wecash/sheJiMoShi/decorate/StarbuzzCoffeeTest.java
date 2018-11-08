package com.wecash.sheJiMoShi.decorate;

import java.io.FileNotFoundException;
/**
 * 星巴兹咖啡
 * 装饰者模式——动态地将模式附加到对象上，若要扩展功能，装饰者提供了比继承更有弹性的替代方案
 * @author boce
 *
 */
public class StarbuzzCoffeeTest {

	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Beverage beverage = new Espresso();
		System.out.println(beverage.getDescription() +"$"+beverage.cost());
		
		Beverage beverage2 = new Espresso();
		beverage2 = new Mocha(beverage2);
		System.out.println(beverage2.getDescription()+"$"+beverage2.cost());
		
		Beverage beverage3 = new Espresso();
		beverage3 = new Mocha(beverage3);
		beverage3 = new Mocha(beverage3);
		System.out.println(beverage3.getDescription()+"$"+beverage3.cost());
		
		Beverage beverage4 = new Mocha(new Mocha(new Espresso()));
		System.out.println(beverage4.getDescription()+"$"+beverage4.cost());
		
	//	LineNumberInputStream lineNumberInputStream = new LineNumberInputStream(new BufferedInputStream(new FileInputStream("")));
	}

}
