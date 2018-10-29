package com.wecash.nevermore.framework;

import com.google.common.base.Suppliers;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import com.wecash.nevermore.concurrent.ManagedThreadPool;
import com.wecash.nevermore.json.JsonUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by hui.sun on 16/4/14.
 * <p/>
 * 异步的事件驱动模型
 */
@Slf4j
public class EventPool {

    public static EventBus get(){
        return EventBusHolder.eventBus;
    }

    private static class EventBusHolder{
        private static EventBus eventBus=Suppliers.
                memoize(() -> new AsyncEventBus(ManagedThreadPool.newNamedExecutor("eventBus_pool", 1000)))
                .get();
        static {
            eventBus.register(new EventPool());
            log.info("eventBus启动成功");
        }
    }

    public static void post(AsycEvent asycEvent) throws InterruptedException {
        get().post(asycEvent);
        Thread.sleep(1000l);
    }



    @Subscribe
    public void event(AsycEvent asycEvent) {
        log.info("异步任务池进来一个对象:{}", JsonUtil.toJson(asycEvent.getEvent()));
        try {
            asycEvent.getHandler().handle(asycEvent.getEvent());
        }catch (Exception e){
            log.error("异步处理事件出现异常",e);
        }
    }



    @Subscribe
    public void deadEvent(DeadEvent deadEvent) {
        System.out.println(111);
        log.error("没有找到对应的 EVENT");
    }


    public static void main(String[] args) throws InterruptedException {
        /*EventPool.post(AsycEvent.<String,Integer>builder().event("123").handler(new AsycEventHandler<String,Integer>() {
            @Override
            public Integer handle(String o) {
                System.out.println(o);
                return 12;
            }
        }).build());
        System.out.println(333);*/
        /*eventBus.post("111");*/
    }
}
