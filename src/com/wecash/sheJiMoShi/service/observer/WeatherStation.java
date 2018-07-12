package com.wecash.sheJiMoShi.service.observer;


import com.wecash.sheJiMoShi.model.observer.FamilyData;
import com.wecash.sheJiMoShi.model.observer.MobilePhone;

/**
 * 观察者模式——在对象之间定义一对多的依赖，这样一来，当一个对象改变时，依赖它的对象都会收到通知，并自动更新。
 * 设计原则：为了交互对象之间的松耦合而设计
 * @author boce
 *
 */
public class WeatherStation {
    public static void main(String[] args){
    	FamilyData weatherData = new FamilyData();//主题-气象数据
    	
    	MobilePhone currentConditionDisplay = new MobilePhone(weatherData);//订阅者-当前情况布告栏
    	
    	weatherData.setMeasurements(80, 65, 3.4f);
    	weatherData.setMeasurements(82, 70, 30.4f);
    	weatherData.setMeasurements(78, 90, 29.2f);
    }
}
