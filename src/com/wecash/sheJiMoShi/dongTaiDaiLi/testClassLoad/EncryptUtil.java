package com.wecash.sheJiMoShi.dongTaiDaiLi.testClassLoad;

import java.io.*;

/**
* 
* @author chengTong
* @date 2018-06-25 16:09
**/
public class EncryptUtil {
    /**
     * 将数据从源文件中读取出来，让其每一位数据都取异或1的值，再写入目标文件
     *
     * @param src
     *            源文件
     * @param des
     *            加密后的文件
     * @throws Exception
     */
    public static void encrypt(File src, File des) throws Exception {
        InputStream in = new FileInputStream(src);
        OutputStream out = new FileOutputStream(des);

        int ch;
        StringBuilder builder = new StringBuilder();
        while (-1 != (ch = in.read())) {
            char ch1 = (char) ch;
            builder.append(ch1);
            System.out.println(":"+ch+"="+ch1);
            ch = ch ^ 0xff;//加密，0变成1，1变成0
            out.write(ch);
        }
        System.out.println(builder.toString());
        in.close();
        out.close();
    }
}
