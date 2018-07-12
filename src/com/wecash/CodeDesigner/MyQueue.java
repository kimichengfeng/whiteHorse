package com.wecash.CodeDesigner;

/**
 * Created by chengtong on 2017/12/25.
 */
public class MyQueue implements BlockingQueue {
    @Override
    public boolean add() {
        return false;
    }

    @Override
    public boolean offer() {
        return false;
    }

    @Override
    public boolean get() {
        return false;
    }
}
