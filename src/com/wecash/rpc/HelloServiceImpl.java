package com.wecash.rpc;

/**
 * Created by chengtong on 2018/1/22.
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        return "Hello " + name;
    }
}
