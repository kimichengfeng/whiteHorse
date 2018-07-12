package com.wecash.MantThread.executors;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.List;
import java.util.concurrent.*;

/**
 * Created by chengtong on 2017/12/25.
 */
public class ThreadPoolUtil {
    public static ExecutorService getThreadPool(String threadName, int corePoolSize, int maximumPoolSize){
        ThreadFactory nameThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat(threadName+"-pool-%d").build();
        ExecutorService pool = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>(1024),
                nameThreadFactory,new ThreadPoolExecutor.AbortPolicy());
        return pool;
    }
    public static void main(String[] args) {
        ExecutorService executorService = ThreadPoolUtil.getThreadPool("test", 5, 100);
        List<Future<String>> futures = Lists.newArrayList();
        for(int i=0;i<20;i++){
            int finalI = i;
            Future<String> future = executorService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {

                    return String.valueOf(Thread.currentThread().getName()+"："+finalI);
                }
            });
            futures.add(future);
        }

        for(Future<String> future:futures){
            try {
                System.out.println(future.get()
                );
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

//        for(int i=0;i<20;i++) {
//            int finalI = i;
//            executorService.execute(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(Thread.currentThread().getName()+"："+String.valueOf(finalI));
//                }
//            });
//        }
//    }
}
