/**
 * 
 */
package com.wecash.sheJiMoShi.proxy;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


/**
 * @author boce
 *
 */
public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote {

	/**
	 * @throws RemoteException
	 */
	protected MyRemoteImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @Title: sayHello
	 * @Description: TODO
	 * @return
	 * @throws RemoteException
	 * @author chengtong 2016年8月6日
	 */

	@Override
	public String sayHello() throws RemoteException {
		// TODO Auto-generated method stub
		return "server say hello";
	}

	public static void main(String[] args){
		try{
			//产生远程对象
			MyRemote service = new MyRemoteImpl();
			//绑定到RMI registry
			Naming.rebind("RemoteHello", service);
		}catch(Exception e){
			
		}
	}

}
