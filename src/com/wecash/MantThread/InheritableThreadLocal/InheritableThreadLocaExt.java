package com.wecash.MantThread.InheritableThreadLocal;

import java.util.Date;

/**
* 
* @author chengTong
* @date 2018-06-25 19:53
**/
public class InheritableThreadLocaExt extends InheritableThreadLocal {
    @Override
    protected Object initialValue() {
        return "傻逼吗";

    }
    @Override
    protected Object childValue(Object parentValue) {
        return parentValue+"对继承值进行修改";

    }
}
