package com.wecash.nevermore.constant;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import java.util.List;

/**
 * 主要应用guava，处理字符串
 */
public class MyConstant {
    public static final Splitter SPACE_SPLITTER = Splitter.on(" ").trimResults().omitEmptyStrings();
    public static final Splitter STAND_LINE_SPLITTER = Splitter.on("|").trimResults().omitEmptyStrings();
    public static final Splitter EQUAL_SPLITTER = Splitter.on("=").trimResults().omitEmptyStrings();
    public static final Splitter SLEEP_LINE_SPLITTER = Splitter.on("-").trimResults().omitEmptyStrings();
    public static final Splitter COMMA_SPLITTER = Splitter.on(",").trimResults().omitEmptyStrings();
    public static final Splitter LOW_LINE_SPLITTER = Splitter.on("_").trimResults().omitEmptyStrings();
    public static final Joiner SPACE_JOINER = Joiner.on(" ").skipNulls();
	public static final Joiner COMMA_JOINER = Joiner.on(",").skipNulls();
    public static final Joiner LOW_LINE_JOINER= Joiner.on("_").skipNulls();

    public static void main(String[] args) {
        String str = "a";
        List<String> strs = COMMA_SPLITTER.splitToList(str);
        strs.forEach(item-> System.out.println(item));

        String strs2 = COMMA_JOINER.join(strs);
        System.out.println("strs:"+strs);
    }


}
