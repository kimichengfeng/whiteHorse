package com.wecash.sheJiMoShi.decorate;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by chengtong on 2018/1/28.
 */
public class InputTest {
    public static void main(String[] args) throws IOException {
        int c;
        InputStream in = new LowerCaseInputStream(new BufferedInputStream(new FileInputStream(
         "/Users/chengtong/Desktop/test.txt")));
        while((c=in.read())>=0){
            System.out.print((char)c);
        }
        in.close();
    }
}
