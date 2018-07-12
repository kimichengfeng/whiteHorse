/**
 * 
 */
package com.wecash.sheJiMoShi.inter.proxy;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author boce
 * 远程接口
 */
public interface MyRemote extends Remote {
	public String sayHello() throws RemoteException;
}
