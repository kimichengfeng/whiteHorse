package com.wecash.sheJiMoShi.model.observer;


import com.wecash.sheJiMoShi.inter.observer.Observer;
import com.wecash.sheJiMoShi.inter.observer.Subject;

import java.util.ArrayList;

/**
 * 气象数据 主题
 * 
 * @author boce
 *
 */
public class FamilyData implements Subject {
	private ArrayList<Observer> observers;// 记录观察者
	private float temperature;
	private float image;
	private float dampness;

	/**
	 * 构造函数
	 */
	public FamilyData() {
		observers = new ArrayList<Observer>();
	}
	/**
	 * 通知新数据
	 */
	@Override
	public void notifyObserver() {
		for (int i = 0; i < observers.size(); i++) {
			Observer observer = (Observer) observers.get(i);
			observer.update(temperature, image, dampness);
		}

	}

	/**
	 * 注册为观察者
	 */
	@Override
	public void registerObserver(Observer o) {
		observers.add(o);
	}

	/**
	 * 取消注册
	 */
	@Override
	public void removeObserver(Observer o) {
		int i = observers.indexOf(o);
		if (i >= 0) {
			observers.remove(i);
		}

	}


	/**
	 * 当从气象站得到更新的观察值时，通知观察者
	 */
	public void measurementschanged() {
		notifyObserver();
	}

	/**
	 * 数据发生变化
	 * 
	 * @param temperature
	 * @param image
	 * @param dampness
	 */
	public void setMeasurements(float temperature, float image, float dampness) {
		this.temperature = temperature;
		this.image = image;
		this.dampness = dampness;
		measurementschanged();
	}
	// 其他方法
}
