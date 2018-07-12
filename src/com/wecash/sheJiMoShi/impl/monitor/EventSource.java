package com.wecash.sheJiMoShi.impl.monitor;

import com.wecash.sheJiMoShi.inter.monitor.IEvent;
import com.wecash.sheJiMoShi.inter.monitor.IEventListener;

/**
 * Created by chengtong on 2018/4/10.
 */
public class EventSource implements IEvent {
    private IEventListener mEventListener;
    boolean button;
    boolean mouse;
    //注册监听器
    @Override
    public void setEventListener(IEventListener arg) {
        mEventListener = arg;
    }
    //触发事件
    public void mouseEventHappened(){
        mouse = true;
        mEventListener.doEvent(this);
    }
    @Override
    public boolean ClickButton() {
        return button;
    }
    //触发事件
    @Override
    public boolean MoveMouse() {
        return mouse;
    }
}
