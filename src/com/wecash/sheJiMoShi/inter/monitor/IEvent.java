package com.wecash.sheJiMoShi.inter.monitor;

/**
 * Created by chengtong on 2018/4/10.
 * 事件
 */
public interface IEvent {
    void setEventListener(IEventListener arg);

    boolean ClickButton();

    boolean MoveMouse();
}
