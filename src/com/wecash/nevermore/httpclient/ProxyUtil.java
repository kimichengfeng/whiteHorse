package com.wecash.nevermore.httpclient;

import com.google.common.collect.Lists;
import com.wecash.nevermore.concurrent.ManagedThreadPool;
import com.wecash.nevermore.json.JsonUtil;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 代理IP服务的封装
 */
public class ProxyUtil {
    private static Logger log= LoggerFactory.getLogger(ManagedThreadPool.class);

    /**
     * 代理IP服务，懒加载一个代理IP池，并随机获取一个代理IP
     *
     * @return
     */
    public static ProxyIp getProxy()  {
        try {
            Client client = Client.localClient();
            ArrayList<Header> header = Lists.newArrayList();
            header.add(new BasicHeader("userName","sunhui"));
            header.add(new BasicHeader("password","sunhui"));
            String result = client.get("http://proxy-pool.wecash.net:9999/v4/proxy?userName=sunhui", header);
            MyProxyIp myProxyIp = JsonUtil.parseObject(result, MyProxyIp.class);
            return new ProxyIp(myProxyIp.getIp(),Integer.parseInt(myProxyIp.getPort()));
        }catch (Exception e){
            log.error("获取代理IP失败:",e);
            return null;
        }

    }

    public static void main(String[] args) throws IOException {
        getProxy();
    }

    /**
     * 一般的代理IP都有feedback机制，预留
     *
     * @return
     */
    public static ProxyIp feedback(ProxyIp proxyIp) {
        return null;
    }

}
