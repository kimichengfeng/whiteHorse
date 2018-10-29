package com.wecash.nevermore.framework;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.util.List;

/**
 * 一个直接继承Spring接口获取spring容器的类
 * Created by sunhui on 16/11/28.
 */
@Slf4j
public class SpringContext {

    private static ApplicationContext context;

    /**
     * 将SpringContext做成懒单例
     * 并且暴露出来唯一的spring上下文
     *
     * @return
     */
    private static ApplicationContext instance() {
        return Suppliers.memoize((Supplier<ApplicationContext>) ContextLoader::getCurrentWebApplicationContext).get();
    }

    private SpringContext() {
    }

    public static <T> T getBean(Class<T> clazz) {
        log.info("获取bean:{}", clazz.getName());
        return instance().getBean(clazz);
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        log.info("获取bean:{}", name);
        return instance().getBean(name, clazz);
    }

    public static <T> List<T> getBeanList(Class<T> clazz) {
        String[] names = instance().getBeanNamesForType(clazz);
        if (names == null || names.length == 0) {
            return null;
        }
        List<T> beans = Lists.newArrayList();
        for (String name : names) {
            beans.add(getBean(name, clazz));
        }
        return beans;
    }


}
