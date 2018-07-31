package com.wecash.bigData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ReadFile {
    public static void main(String[] args) {
        try{
            String filePath = "D:\\test\\test.txt";
            BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)));
            String temp= "";
            while ((temp= reader.readLine())!=null){
                /**
                 * String的split方法支持正则表达式；
                 正则表达式\s表示匹配任何空白字符，+表示匹配一次或多次。
                 */
                String userId = temp.split("\\s+")[1];
                System.out.println(temp);
                System.out.println(userId);
            }
         }catch(Exception e){

        }
    }
}
