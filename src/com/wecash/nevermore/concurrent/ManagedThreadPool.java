package com.wecash.nevermore.concurrent;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.wecash.nevermore.common.NamedThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池工具类
 */
public class ManagedThreadPool {

    private static Logger logger = LoggerFactory.getLogger(ManagedThreadPool.class);

    private static Supplier<ThreadPoolExecutor> getNamedPool(String name, int size) {
        return Suppliers.memoize(() -> new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), Runtime.getRuntime().availableProcessors(), 0, TimeUnit.MINUTES, new ArrayBlockingQueue<>(size), new NamedThreadFactory(name)));
    }

    public static ThreadPoolExecutor newNamedExecutor(String name, int queueSize) {
        logger.info("线程池：{}，初始化成功", name);
        return getNamedPool(name, queueSize).get();
    }

    public static ListeningExecutorService newNamedGuavaExecutor(String name, int queueSize) {
        logger.info("线程池：{}，初始化成功", name);
        return MoreExecutors.listeningDecorator(getNamedPool(name, queueSize).get());
    }

}
