package com.wecash.nevermore.httpclient;

import org.apache.http.Header;

import java.util.List;


public interface Headers {
    /**
     * 一个预留的接口，用于在client中填充header
     *
     * @return
     */
    List<Header> getHeader();
}
