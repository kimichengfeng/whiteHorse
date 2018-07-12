package com.wecash.sheJiMoShi.inter.observer;

public interface Observer {
    public void update(float temp, float humidity, float pressure);//当观测值发生变化时，主题会把这些状态值作为方法的参数，传递给观察者
}
