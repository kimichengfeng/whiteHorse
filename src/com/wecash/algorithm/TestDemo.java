package com.wecash.algorithm;
/**
* 
* @author chengTong
* @date 2018-06-26 10:44
**/
public class TestDemo {
    public static void main(String[] args)
    {
        int [] arr1=new int[]{1,3,6,2,9};
        printArrays(arr1);//输出的结果：13629
        reverseArray(arr1);//输出的结果：92631
        printArrays(arr1);//输出的结果：13629
    }

    /**求逆向数组*/
    public static void reverseArray(int [] arr){
        int [] arr_new=new int[arr.length];
        for(int i=0;i<arr.length;i++){
            arr_new[arr.length-1-i]=arr[i];
        }
        arr=arr_new;//这里到底有没有改变原本arr1的引用？
        printArrays(arr);
    }

    /**打印数组*/
    public static void printArrays(int [] arr){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]);
        }
        System.out.println("");
    }
}
