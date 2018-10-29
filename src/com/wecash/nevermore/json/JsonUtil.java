package com.wecash.nevermore.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;
import java.util.Map;

/**
 * Created by guankai.wang on 2016/12/6.
 */
public class JsonUtil {

    private JSONObject jsonObject;

    /**
     * 转换为java 类
     *
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T parseObject(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }
    /**
     * 转换为List
     *
     * @param json
     * @param clazz
     * @return
     */
    public static <T> List<T> parseArray(String json, Class<T> clazz) {
        return JSON.parseArray(json, clazz);
    }


    /**
     * 转换为string 输出空内容, 空列表
     *
     * @param obj
     * @return
     */
    public static String toJsonWithNull(Object obj) {
        return JSON.toJSONString(obj
                , SerializerFeature.WriteMapNullValue
                , SerializerFeature.WriteDateUseDateFormat);
    }
    /**
     *
     *
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        return JSON.toJSONString(obj);
    }

    public static <T> T parseMap(Map map,Class<T> clzz){
        return JsonUtil.parseObject(JsonUtil.toJson(map),clzz);
    }

    public static String toJson(Object obj,SerializerFeature... serializerFeatures){
        return JSON.toJSONString(obj,serializerFeatures);
    }


    public static String toJson(Object data, SerializeFilter s, SerializerFeature... serializerFeatures){
        return JSON.toJSONString(data,s, serializerFeatures);
    }
    /**
     * 转换String 为JsonObject
     *
     */
    public static JSONObject getJsonObject(String json){
        return JSON.parseObject(json);
    }


    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public static class JsonUtilBuilder{
        private JSONObject jsonObject;
        public JsonUtilBuilder(String  jsonObject) {
            this.jsonObject = JsonUtil.getJsonObject(jsonObject);
        }
        /**
         * 建造者模式来获取对应的key，避免每次接收json建个类或写一堆
         * @param key json中的想获取 其value的key
         * @return
         */
        public JsonUtilBuilder getJsonObject(String key) {
            this.jsonObject=JsonUtil.getJsonObject(String.valueOf(this.jsonObject.get(key)));
            return this;
        }
        public String getValue(String key) {
            return this.jsonObject.get(key).toString();
        }
    }

    public static JsonUtilBuilder builder(String json){
        return new JsonUtilBuilder(json);
    }


}
