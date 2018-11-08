/**
 * 
 */
package com.wecash.sheJiMoShi.proxy;

import java.rmi.Naming;


/**
 * @author boce
 * 代理模式为另一个对象提供一个替身或占位符以控制对这个对象的访问
 */
public class MyRemoteClient {
	public static void main(String[] args){
		new MyRemoteClient().go();
	}
	
	public void go(){
		try{
			MyRemote service = (MyRemote) Naming.lookup("rmi://127.0.0.1/RemoteHello");
			
			String s = service.sayHello();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
