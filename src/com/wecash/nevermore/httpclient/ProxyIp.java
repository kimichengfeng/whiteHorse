package com.wecash.nevermore.httpclient;



public class ProxyIp  {

    String host;
    Integer port;

    public ProxyIp(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "ProxyIp{" +
                "host='" + host + '\'' +
                ", port=" + port +
                '}';
    }

}
