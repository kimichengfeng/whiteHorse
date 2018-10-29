package com.wecash.nevermore.httpclient;

import java.util.Map;

/**
 * 用来将一些复杂的对象抽离成bean解耦出来
 */
public interface IParam {

    /**
     * 将某个对象转成url参数
     *
     * @return ?a=**&b=*
     */
    String toGet();

    /**
     * 将某个对象转为map
     *
     * @return
     */
    Map<String, String> toPost();

    /**
     * 将对象转换为http请求的body部门，转为json
     *
     * @return
     */
    String toContent();
}

