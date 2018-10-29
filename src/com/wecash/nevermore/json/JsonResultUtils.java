//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.wecash.nevermore.json;

import com.google.common.collect.Maps;
import com.wecash.nevermore.xss.XSSUtil;

import java.util.HashMap;
import java.util.Map;

public final class JsonResultUtils {
    private JsonResultUtils() {
        throw new Error("Utility classes should not instantiated!");
    }

    public static Map<String, Object> getCodeAndMesMap(Integer code, String message) {
        HashMap resultMap = Maps.newHashMap();
        resultMap.put("code", code);
        resultMap.put("message", message);
        return resultMap;
    }

    public static Map<String, Object> getCodeAndMesMapAsDefault(JsonResultUtils.Code status) {
        return getCodeAndMesMap(Integer.valueOf(status.code), status.message);
    }

    public static <T> Map<String, Object> getObjectResultMap(Integer code, Object object, String message) {
        HashMap resultMap = Maps.newHashMap();
        resultMap.put("code", code);
        resultMap.put("data", object);
        resultMap.put("message", message);
        return resultMap;
    }

    public static <T> Map<String, Object> getObjectResultMapAsDefault(Object object, JsonResultUtils.Code status) {
        return getObjectResultMap(Integer.valueOf(status.code), object, status.message);
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

    public static enum Code {
        SUCCESS(200, "操作成功！"),
        ERROR(500, "对不起，操作出错！"),
        NOTFOUND(404, "对不起，您请求的资源不存在！"),
        DUPLICATE(302, "重复操作！"),
        NOPERM(403, "对不起，您没有进行此项操作的权限"),
        ILLEGA(422, "请求服务参数异常"),
        INPUTPARAMEMPT(501, "请求参数为空!"),
        DEALACCEPTINSSUC(0, "处理出保回调成功"),
        DEALACCEPTINSFAIL(502, "处理出保回调失败");

        private final int code;
        private final String message;

        private Code(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getMessage() {
            return this.message;
        }

        public int getCode() {
            return this.code;
        }
    }
}
