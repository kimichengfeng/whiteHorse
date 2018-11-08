package com.wecash.sheJiMoShi.sigleton;

import java.util.HashMap;
import java.util.Map;

public class Singleton3 extends A implements B{
    //关键字volatile的作用是使变量在多个线程间可见。JVM被设置为-server模式时为了线程运行的效率，线程一直在私有堆栈中取值，当值
	//在公有堆栈中改变时，私有堆栈中的值和公共堆栈中的值会存在不同步的现象，使用volatile关键字，强制从公共堆栈中取值。
	//线程安全包括原子性和可见性两个方面，java的同步机制都是围绕这两个方面来确保线程安全的。
	private volatile static Singleton3 uniqueInstance;
    private static Map<String,String> map = new HashMap<>();
    private static final int temp = 1;
	// 这里是其它的有用的实例化变量
	/**
	 * 把构造器声明为私有的，只有类内才可以调用构造器
	 */
	private Singleton3() {
	  map.put("a", "b");
      System.out.println("构造函数！");
	}

	/**
	 * 双重检查加锁
	 * 
	 * @return
	 */
	public static Singleton3 getInstance() {
		if (uniqueInstance == null) {
			synchronized (Singleton3.class) {
				if (uniqueInstance == null) {
					System.out.println("创建对象start！");
					uniqueInstance = new Singleton3();
					System.out.println("创建对象end！");
				}
			}
		}
		return uniqueInstance;

	}
	public Map getMap(){
		return map;
	}
	// 这里是其他的有用方法
	public static void main(String[] args){
		Singleton3 danli = getInstance();
		/*String a = (String) danli.getMap().get("a");
		System.out.println("a="+a);
		int a1 = danli.getA();*/
		int a2 = danli.temp;
		System.out.println(a2);
		B b = getInstance();
		int b1 = b.temp;
		System.out.println(b1);
	}
}
