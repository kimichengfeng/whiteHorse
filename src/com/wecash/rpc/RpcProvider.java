package com.wecash.rpc;

/**
 * Created by chengtong on 2018/1/22.
 */
public class RpcProvider {
    public static void main(String[] args) throws Exception {
        HelloService service = new HelloServiceImpl();
        RpcFramework.export(service, 1234);
    }

}
