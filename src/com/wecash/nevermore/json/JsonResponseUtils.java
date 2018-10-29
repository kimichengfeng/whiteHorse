//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.wecash.nevermore.json;

import com.google.common.collect.Maps;
import com.wecash.nevermore.xss.XSSUtil;

import java.util.HashMap;
import java.util.Map;

public final class JsonResponseUtils {
    private JsonResponseUtils() {
        throw new Error("Utility classes should not instantiated!");
    }

    public static Map<String, Object> getCodeAndMesMap(Integer code, String message) {
        HashMap resultMap = Maps.newHashMap();
        resultMap.put("code", code);
        resultMap.put("message", message);
        return resultMap;
    }

    public static <T> Map<String, Object> getObjectResultMap(Integer code, Object object, String message) {
        HashMap resultMap = Maps.newHashMap();
        resultMap.put("code", code);
        resultMap.put("data", object);
        resultMap.put("message", message);
        return resultMap;
    }

    private static String buildJsonPResult(String cb, String result) {
        return XSSUtil.htmlEncode(cb) + "(" + result + ")";
    }

    public static <T> Map<String, Object> getObjectStrResultMap(Integer code, Object object, String str) {
        HashMap resultMap = Maps.newHashMap();
        resultMap.put("code", code);
        resultMap.put("data", object);
        resultMap.put("str", str);
        return resultMap;
    }

    public static <T> Map<String, Object> getDoubleObjectResultMapAsDefault(Object object, Object obj, Integer code, String message) {
        return getDoubleObjectResultMap(code, object, obj, message);
    }

    public static <T> Map<String, Object> getDoubleObjectResultMap(Integer code, Object object, Object obj, String message) {
        HashMap resultMap = Maps.newHashMap();
        resultMap.put("code", code);
        resultMap.put("data", object);
        resultMap.put("page", obj);
        resultMap.put("message", message);
        return resultMap;
    }
}
