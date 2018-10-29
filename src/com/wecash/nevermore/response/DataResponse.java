package com.wecash.nevermore.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by guankai.wang on 2016/11/18.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataResponse<T> {
    int code;
    T body;
    String message;


}
