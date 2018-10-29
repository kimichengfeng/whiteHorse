package com.wecash.nevermore.framework;

import lombok.Builder;
import lombok.Data;

/**
 * Created by sunhui on 17/3/22.
 */
@Builder
@Data
public class AsycEvent<T,R> {
    T event;
    AsycEventHandler<T,R> handler;
}
