package com.wecash.algorithm.lookup.bisearch;
/**
* （二分查找）折半查找
* @author chengTong
* @date 2018-06-28 14:38
**/
public class BiSearch {
    //非递归
    public static int biSearch(int []array,int a){
        int lo=0;
        int hi=array.length-1;
        int mid;
        while(lo<=hi){
            mid=(lo+hi)/2;
            if(array[mid]==a){
                return mid;
            }else if(array[mid]<a){
                lo=mid+1;
            }else{
                hi=mid-1;
            }
        }
        return -1;
    }
    //递归
    public static int sort(int []array,int a,int lo,int hi){
        if(lo<=hi){
            int mid=(lo+hi)/2;
            if(a==array[mid]){
                return mid;
            }
            else if(a>array[mid]){
                return sort(array,a,mid+1,hi);
            }else{
                return sort(array,a,lo,mid-1);
            }
        }
        return -1;
    }
    //查找第一个元素出现的位置（元素允许重复）
    public static int biSearch2(int []array,int a){
        int n=array.length;
        int low=0;
        int hi=n-1;
        int mid=0;
        while(low<hi){
            mid=(low+hi)/2;
            if(array[mid]<a){
                low=mid;
            }else{
                hi=mid;
            }
        }
        if(array[low]!=a){
            return -1;
        }else{
            return low;
        }
    }
    //查询元素最后一次出现的位置
    public static int biSearch3(int []array,int a){
        int n=array.length;
        int low=0;
        int hi=n-1;
        int mid=0;
        while(low<hi){
            mid=(low+hi+1)/2;
            if(array[mid]<=a){
                low=mid;
            }else{
                hi=mid-1;
            }
        }

        if(array[low]!=a){
            return -1;
        }else{
            return hi;
        }
    }

    public static void main(String[] args) {
        int[] numbers = new int[]{4,6,1,8,10,3};
        int index = biSearch(numbers,8);
        System.out.println("位置："+index);
    }
}
