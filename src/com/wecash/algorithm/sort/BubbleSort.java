package com.wecash.algorithm.sort;
/**
* 冒泡排序
 *穷举搜索法
* @author chengTong
* @date 2018-06-26 10:25
**/
public class BubbleSort {
    public static void bubbleSort(int[] numbers)
    {
        int temp = 0;
        int size = numbers.length;
        for(int i = 0 ; i < size-1; i ++)
        {
            for(int j = 0 ;j < size-1-i ; j++)
            {
                //交换两数位置
                if(numbers[j] > numbers[j+1])
                {
                    temp = numbers[j];
                    numbers[j] = numbers[j+1];
                    numbers[j+1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] numbers = new int[]{4,6,1,8,10,3};
        bubbleSort(numbers);
        for(int i=0;i<numbers.length;i++){
            System.out.println(numbers[i]);
        }
    }
}
