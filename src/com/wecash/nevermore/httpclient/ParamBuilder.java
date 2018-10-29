package com.wecash.nevermore.httpclient;


import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.http.cookie.Cookie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 用来链式构造简单的爬虫参数
 */
public class ParamBuilder {
    private ParamBuilder() {
    }

    public static MapBuilder builder() {
        return new MapBuilder();
    }

    public static Map<String, String> getToPost(String getPara) {
        List pairList = Splitter.on("&").omitEmptyStrings().trimResults().splitToList(getPara);
        HashMap<String, String> result = Maps.newHashMap();
        Iterator var3 = pairList.iterator();

        while (var3.hasNext()) {
            String pair = (String) var3.next();
            List<String> list = Splitter.on("=").splitToList(pair);
            result.put(list.get(0), Strings.nullToEmpty((String) list.get(1)));
        }

        return result;
    }

    public Cookie[] getCookieByStr(String cookieStr) {
        ArrayList result = Lists.newArrayList();
        return null;
    }

    public static class MapBuilder {
        private HashMap<String, String> map = Maps.newHashMap();

        public MapBuilder() {
        }

        public MapBuilder set(String key, String value) {
            this.map.put(key, value);
            return this;
        }

        public String get() {
            return "?" + Joiner.on("&").join(this.map.entrySet());
        }

        public String content() {
            StringBuilder builder = new StringBuilder("");
            builder.append("{\n");
            Iterator var2 = this.map.entrySet().iterator();

            while (var2.hasNext()) {
                Entry entry = (Entry) var2.next();
                builder.append("\"" + (String) entry.getKey() + "\"").append(":").append("\"" + (String) entry.getValue() + "\"").append(",\n");
            }

            builder.deleteCharAt(builder.length() - 2);
            builder.append("}");
            return builder.toString();
        }

        public Map<String, String> post() {
            return this.map;
        }
    }
}
