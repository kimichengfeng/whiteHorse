package com.wecash.nevermore.httpclient;


public interface Result {

    /**
     * 爬虫结果的接口，用来放入最终解析后的结果
     *
     * @return
     */
    String toJson();
}
