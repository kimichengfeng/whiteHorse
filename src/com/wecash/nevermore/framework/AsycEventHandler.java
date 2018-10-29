package com.wecash.nevermore.framework;

/**
 * Created by sunhui on 17/3/22.
 */
public interface AsycEventHandler<AsycEvent,Result> {

    Result handle(AsycEvent asycEvent);
}
