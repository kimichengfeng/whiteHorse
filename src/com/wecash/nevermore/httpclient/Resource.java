package com.wecash.nevermore.httpclient;


public interface Resource {

    ResourceType type();

    default String content() {
        return this.toString();
    }

}
