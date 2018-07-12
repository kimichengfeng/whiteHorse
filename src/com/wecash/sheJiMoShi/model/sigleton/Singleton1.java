package com.wecash.sheJiMoShi.model.sigleton;

public class Singleton1 {
	private static Singleton1 uniqueInstance;
	//这里是其它的有用的实例化变量
	/**
	 * 把构造器声明为私有的，只有类内才可以调用构造器
	 */
	private Singleton1(){
		
	}
	
	/**
	 * 延迟实例化
	 * 通过增加synchroized关键字，迫使每个线程进入这个方法之前，要先等待别的线程离开这个方法。但是同步会降低性能，同步一个
	 * 方法可能造成程序执行效率降低100倍。
	 * @return
	 */
	public static synchronized Singleton1 getInstance(){
		if(uniqueInstance == null)
			uniqueInstance = new Singleton1();
		return uniqueInstance;
		
	}
	
	//这里是其他的有用方法
}
