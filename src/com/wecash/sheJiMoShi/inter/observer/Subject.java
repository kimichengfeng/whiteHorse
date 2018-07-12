package com.wecash.sheJiMoShi.inter.observer;



public interface Subject {
	public void registerObserver(Observer o);//以观察者作为对象
	public void removeObserver(Observer o);
	
	public void notifyObserver();//当主题状态改变时，这个方法会被调用，以通知所有的观察者
	

}
