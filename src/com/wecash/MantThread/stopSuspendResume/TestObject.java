package com.wecash.MantThread.stopSuspendResume;

/**
 * Created by chengtong on 2018/4/13.
 */
public class TestObject {
    private String first = "ja";
    private String second = "va";

    public synchronized void print(String first, String second) throws Exception{
        this.first = first;

        Thread.sleep(10000);

        this.second = second;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }
}
