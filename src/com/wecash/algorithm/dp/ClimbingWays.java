package com.wecash.algorithm.dp;

import com.google.common.collect.Maps;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA
 * Description: 【动态规划】
 * 有一座高度是10级台阶的楼梯，从下往上走，每跨一步只能向上1级或者2级台阶。
 * 要求用程序来求出一共有多少种走法。
 * 比如，每次走1级台阶，一共走10步，这是其中一种走法。我们可以简写成 1,1,1,1,1,1,1,1,1,1。
 * User: tong.cheng
 * Date: 2018-09-11
 * Time: 11:17
 */
public class ClimbingWays {

    public static void main(String[] args) {
      int n=10;
      ClimbingWays ways = new ClimbingWays();
      int ways1 = ways.getClimbWays1(n);
      System.out.println("way1="+ways1);
      HashMap<Integer,Integer> map = Maps.newHashMap();
      int ways2 = ways.getClimbingWays2(n,map);
      System.out.println("way2="+ways2);
      int ways3 = ways.getClimbWays3(n);
      System.out.println("ways3="+ways3);

    }

    /**
     * 递归实现
     * @param n
     * @return
     */
    public int getClimbWays1(int n){
        if(n<1) return 0;
        if(n==1){return 1;}
        if(n==2){return 2;}
        return getClimbWays1(n-1)+getClimbWays1(n-2);

    }

    /**
     * 备忘录算法
     * @param n
     * @param map
     * @return
     */
    public int getClimbingWays2(int n, HashMap<Integer,Integer> map){
        if(n<1){ return 0;}
        if(n == 1) {return 1;}
        if(n == 2) {return 2;}
        if(map.containsKey(n)){
            return map.get(n);
        } else {
            int value = getClimbingWays2(n-1,map) +getClimbingWays2(n-2,map);
            map.put(n,value);
            return value;
        }
    }

    /**
     * 真正的动态规划算法，时间复杂度O（N），空间复杂度O（1）
     * @param n
     * @return
     */
    public int getClimbWays3(int n){
        if(n<1) return 0;
        if(n==1) return 1;
        if(n==2) return 2;
       int a=1;
       int b=2;
       int temp = 0;
       for(int i=3;i<=n;i++){
           temp = a+b;
           a=b;
           b=temp;
       }
        return temp;
    }
}
