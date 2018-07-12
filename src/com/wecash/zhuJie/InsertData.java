package com.wecash.zhuJie;

import com.google.common.collect.Lists;

import java.util.List;

/**
* 
* @author chengTong
* @date 2018-06-20 17:57
**/
public class InsertData {
    public static void main(String[] args) {
        String[] strs = new String[]{"a","b","c","d","e","f","g"};
        insert(strs,2);
    }
    public static void insert(String[] data, int size){
        List<String> tempList = Lists.newArrayList();
        int num;
        for(int i=0;i<data.length;i++){
            tempList.add(data[i]);
            num = i+1;
            if(num%size == 0){
                System.out.println("入库："+tempList.size());
                num = 0;
                tempList.clear();
            }
        }
        if(tempList.size()>0) {
            System.out.println("入库:" + tempList.size());
        }
    }
}
