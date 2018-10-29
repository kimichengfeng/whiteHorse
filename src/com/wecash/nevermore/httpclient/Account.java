package com.wecash.nevermore.httpclient;

/**
 * 账户
 */
public class Account implements Resource {

    Integer id;
    String name;
    String pwd;
    Integer useCount;

    public Account(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    public Account(Integer id, String name, String pwd, Integer useCount) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.useCount = useCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getUseCount() {
        return useCount;
    }

    public void setUseCount(Integer useCount) {
        this.useCount = useCount;
    }

    @Override
    public ResourceType type() {
        return ResourceType.ACCOUNT;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", useCount=" + useCount +
                '}';
    }
}
