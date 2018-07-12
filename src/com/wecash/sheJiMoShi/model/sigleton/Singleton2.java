package com.wecash.sheJiMoShi.model.sigleton;
/**
 * 急切创建实例
 * @author boce
 *
 */
public class Singleton2 {
	//JVM在加载这个类时马上创建此唯一的单间实例。JVM保证在任何线程访问uniqueInstance静态变量之前，一定先创建这个实例
	private static Singleton2 uniqueInstance = new Singleton2();
	private  Singleton2() {
		// TODO Auto-generated constructor stub
	}
	
	public static Singleton2 getInstance(){
		return uniqueInstance;
	}
}
