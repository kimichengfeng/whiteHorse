package com.wecash.guava;

import com.google.common.base.Function;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ListenableFuture 继承了Future (jdk)，额外新增了一个方法（任务结束后的回调方法），void addListener(Runnable listener, Executor executor);  其中executor是回调方法的执行器(通常是线程池)。需要注意的是不加Executor的情况，只适用于轻型的回调方法，如果回调方法很耗时占资源，会造成线程阻塞， 因为DirectExecutor有可能在主线程中执行回调
 * ListenableFutureTask 继承了FutureTask (jdk)并实现了ListenableFuture
 * ListeningExecutorService 继承 ExecutorService (jdk)接口，重写了submit方法，修改返回值类型为ListenableFuture
 * 它提供的功能也特别多：
 *
 * 监听任务执行结果并执行回调方法，Futures.addCallback
 * 提供方便的任务接口转换，Futures.transform()、Futures.transformAsync()
 * 多线程并发执行取结果集合，Futures.allAsList()
 * 注：Futures.transform()和Futures.addCallback()都是对addListener做了封装，进行回调的设置，但是transform更适合用在链式处理的中间过程，addCallback更适合用在处理最终的结果上。
 * ---------------------
 * 版权声明：本文为CSDN博主「布道」的原创文章，遵循CC 4.0 by-sa版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/alex_xfboy/article/details/86581560
 */
public class ListenableFutureTest {

    public static void main(String[] args) {
       //线程池
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 5, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100), new CustomizableThreadFactory("demo"), new ThreadPoolExecutor.DiscardPolicy());
        ListeningExecutorService listeningExecutor = MoreExecutors.listeningDecorator(poolExecutor);

       //获得一个随着jvm关闭而关闭的线程池，通过Runtime.getRuntime().addShutdownHook(hook)实现,修改ThreadFactory为创建守护线程，默认jvm关闭时最多等待120秒关闭线程池，重载方法可以设置时间
        ExecutorService newPoolExecutor = MoreExecutors.getExitingExecutorService(poolExecutor);

        //只增加关闭线程池的钩子，不改变ThreadFactory
        MoreExecutors.addDelayedShutdownHook(poolExecutor, 120, TimeUnit.SECONDS);

        //提交任务
        ListenableFuture<String> listenableFuture = listeningExecutor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "";
            }
        });
       //注册回调
        Futures.addCallback(listenableFuture, new FutureCallback<String>() {
            @Override
            public void onSuccess(String result) {
            }
            @Override
            public void onFailure(Throwable t) {
            }
        });
        /**
         * 链式语法
         **/
        ListenableFutureTask<String> task1 = ListenableFutureTask.create(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "";
            }
        });
        new Thread(task1).start();
        task1.addListener(new Runnable() {
            @Override
            public void run() {
                ListenableFutureTask<String> task2 = ListenableFutureTask.create(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        return "";
                    }
                });
                task2.addListener(new Runnable() {
                    @Override
                    public void run() {
                    }
                }, MoreExecutors.directExecutor());
                new Thread(task2).start();
            }
        }, MoreExecutors.directExecutor());

        ListenableFuture<String> task2 = Futures.transform(task1, new Function<String, String>() {
            @Override
            public String apply(String input) {
                return "";
            }
        });
        ListenableFuture<String> task3 = Futures.transform(task2, new Function<String, String>() {
            @Override
            public String apply(String input) {
                return "";
            }
        });
       //处理最终的异步任务
        Futures.addCallback(task3, new FutureCallback<String>() {
            @Override
            public void onSuccess(String result) {
            }
            @Override
            public void onFailure(Throwable t) {
            }
        });
    }

}
