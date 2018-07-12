package com.wecash.sheJiMoShi.impl.monitor;

import com.wecash.sheJiMoShi.inter.monitor.IEvent;
import com.wecash.sheJiMoShi.inter.monitor.IEventListener;

/**
 * Created by chengtong on 2018/4/10.
 */
public class EventSource2 implements IEvent {
    private IEventListener mEventListener;
    boolean button;
    boolean mouse;
    @Override
    public void setEventListener(IEventListener arg) {
        mEventListener = arg;
    }
    // 触发事件
    public void buttonEventHappened() {
        button = true;
        mEventListener.doEvent(this);
    }
    @Override
    public boolean ClickButton() {
        return button;
    }

    @Override
    public boolean MoveMouse() {
        return mouse;
    }
}
