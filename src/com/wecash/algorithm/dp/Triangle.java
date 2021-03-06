package com.wecash.algorithm.dp;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description:
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 *
 * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 *
 *  
 *
 * 例如，给定三角形：
 *
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 *
 *  
 *
 * 说明：
 *
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/triangle
 * User: tong.cheng
 * Date: 2020-05-27
 * Time: 11:36
 */
public class Triangle {


   class DIgui {
       //递归，自顶向下 【超时】
       int row;
       public int minimumTotal(List<List<Integer>> triangle) {
           row = triangle.size();
           return helper(0, 0, triangle);
       }

       private int helper(int level, int c, List<List<Integer>> triangle) {
           // System.out.println("helper: level="+ level+ " c=" + c);
           if (level == row - 1) {
               return triangle.get(level).get(c);
           }
           int left = helper(level + 1, c, triangle);
           int right = helper(level + 1, c + 1, triangle);
           return Math.min(left, right) + triangle.get(level).get(c);
       }

   }
    //改进,避免重复计算
    class Momory {
        //自顶向下, 记忆化搜索 【AC】
        int row;
        Integer[][] memo;
        public int minimumTotal(List<List<Integer>> triangle) {
            row = triangle.size();
            memo = new Integer[row][row];
            return helper(0, 0, triangle);
        }

        private int helper(int level, int c, List<List<Integer>> triangle) {
            // System.out.println("helper: level="+ level+ " c=" + c);
            if (memo[level][c] != null)
                return memo[level][c];
            if (level == row - 1) {
                return memo[level][c] = triangle.get(level).get(c);
            }
            int left = helper(level + 1, c, triangle);
            int right = helper(level + 1, c + 1, triangle);
            return memo[level][c] = Math.min(left, right) + triangle.get(level).get(c);
        }
    }

    class DP {
        //自底向上, DP 【AC】
        public int minimumTotal(List<List<Integer>> triangle) {
            int row = triangle.size();
            int[] minlen = new int[row + 1];
            for (int level = row - 1; level >= 0; level--) {
                for (int i = 0; i <= level; i++) {   //第i行有i+1个数字
                    minlen[i] = Math.min(minlen[i], minlen[i + 1]) + triangle.get(level).get(i);
                }
            }
            return minlen[0];
        }
    }
}
