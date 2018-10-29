package com.wecash.nevermore.response;


import com.wecash.nevermore.json.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by guankai.wang on 2016/11/18.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    T data;
    int errCode;
    String errMsg;

    public static <T> String  send(int errCode,T data,String errMsg){
        return JsonUtil.toJson(Response.builder().errCode(errCode).errMsg(errMsg).data(data).build());
    }
    public static <T> String  send(int errCode,String errMsg){
        return JsonUtil.toJson(Response.builder().errCode(errCode).errMsg(errMsg).build());
    }

    public static <T> String  sendWithNullValue(int errCode,T data,String errMsg){
        return JsonUtil.toJsonWithNull(Response.builder().errCode(errCode).errMsg(errMsg).data(data).build());
    }
    public static <T> String  sendWithNullValue(int errCode,String errMsg){
        return JsonUtil.toJsonWithNull(Response.builder().errCode(errCode).errMsg(errMsg).build());
    }
    public static void main(String[] args) {
        System.out.println(Response.sendWithNullValue(1, "11"));
    }

}
