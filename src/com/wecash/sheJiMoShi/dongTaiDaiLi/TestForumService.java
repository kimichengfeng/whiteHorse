package com.wecash.sheJiMoShi.dongTaiDaiLi;

import java.lang.reflect.Proxy;

/**
 * Created by chengtong on 2017/7/4.
 * JDK动态代理
 * 参考：https://blog.csdn.net/lh513828570/article/details/74078773
 */
public class TestForumService {
    public static void main(String[] args) {

        //①希望被代理的目标业务类
        ForumService target = new ForumServiceImpl();

        //②将目标业务类和横切代码编织到一起
        PerformanceHandler handler = new PerformanceHandler(target);

        //③根据编织了目标业务类逻辑和性能监视横切逻辑的InvocationHandler实例创建代理实例
        ForumService proxy = (ForumService) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                handler);

        //④调用代理实例
        proxy.removeForum(10);
        proxy.removeTopic(1012);
    }
}
