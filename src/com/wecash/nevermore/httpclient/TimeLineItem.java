package com.wecash.nevermore.httpclient;

import org.joda.time.DateTime;


public class TimeLineItem {
    private Status status;
    private DateTime dateTime;

    public TimeLineItem(Status status, DateTime dateTime) {
        this.status = status;
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return dateTime.toString("yyyy-MM-dd HH:mm:ss") + " : " + status.toString() + "\n";
    }

}
