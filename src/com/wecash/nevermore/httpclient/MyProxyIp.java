package com.wecash.nevermore.httpclient;


public class MyProxyIp {

    /**
     * name : 118.119.102.35:20209
     * proxyType : http
     * ip : 118.114.149.133
     * port : 8888
     * restartTime : 1481187642777
     * ipPosition : {"ip":"118.114.149.133","country":"中国","province":"四川","city":"成都"}
     */

    private String name;
    private String proxyType;
    private String ip;
    private String port;
    private long restartTime;
    /**
     * ip : 118.114.149.133
     * country : 中国
     * province : 四川
     * city : 成都
     */

    private IpPositionEntity ipPosition;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProxyType() {
        return proxyType;
    }

    public void setProxyType(String proxyType) {
        this.proxyType = proxyType;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public long getRestartTime() {
        return restartTime;
    }

    public void setRestartTime(long restartTime) {
        this.restartTime = restartTime;
    }

    public IpPositionEntity getIpPosition() {
        return ipPosition;
    }

    public void setIpPosition(IpPositionEntity ipPosition) {
        this.ipPosition = ipPosition;
    }

    public static class IpPositionEntity {
        private String ip;
        private String country;
        private String province;
        private String city;

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }
    }
}
