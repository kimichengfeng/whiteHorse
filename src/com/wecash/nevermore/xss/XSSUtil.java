//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.wecash.nevermore.xss;

public final class XSSUtil {
    private XSSUtil() {
        throw new Error("Utility classes should not instantiated!");
    }

    public static String htmlEncode(String xssString) {
        if(xssString != null && !xssString.equals("")) {
            xssString = xssString.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\'", "&#39;").replaceAll("\"", "&quot;");
        }

        return xssString;
    }
}
