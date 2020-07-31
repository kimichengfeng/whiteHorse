package com.wecash.algorithm.dp;

/**
 输入一个整型数组，数组里有正数也有负数。数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。

 要求时间复杂度为O(n)。

  

 示例1:

 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
 输出: 6
 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
  

 提示：

 1 <= arr.length <= 10^5
 -100 <= arr[i] <= 100

 */
public class MaxSubArray {
    /**
     * 动态规划解析：
     * 1、状态定义： 设动态规划列表 dp ，dp[i]代表以元素 nums[i] 为结尾的连续子数组最大和。
     * 为何定义最大和 dp[i] 中必须包含元素 nums[i] ：保证 dp[i] 递推到 dp[i+1] 的正确性；如果不包含 nums[i] ，递推时则不满足题目的 连续子数组 要求。

     * 2、转移方程： 若dp[i−1]≤0 ，说明 dp[i−1] 对 dp[i] 产生负贡献，即 dp[i-1] + nums[i] 还不如 nums[i] 本身大。
     * 当 dp[i−1]>0 时：执行 dp[i] = dp[i-1] + nums[i] ；
     * 当 dp[i−1]≤0 时：执行 dp[i] = nums[i] ；
     *
     * 3、初始状态： dp[0] = nums[0]，即以 nums[0] 结尾的连续子数组最大和为 nums[0]。
     *
     * 4、返回值： 返回 dp 列表中的最大值，代表全局最大值。

     */
    /**
     * 由于 dp[i]只与 dp[i-1] 和 nums[i]有关系，
     * 因此可以将原数组 numsnums 用作 dpdp 列表，即直接在 numsnums 上修改即可。
     *
     */
    public int maxSubArray(int[] nums) {
        int res = nums[0];
        for(int i = 1; i < nums.length; i++) {
            nums[i] += Math.max(nums[i - 1], 0);
            res = Math.max(res, nums[i]);
        }
        return res;
    }

    /**
     * 题目要求可能不能修改原有数组，考虑到在dp列表中，
     * dp[i]只和dp[i-1]有关,所以用两个参数存储循环过程中的dp[i]和dp[i-1]的值即可，空间复杂度也为O(1)
     * @param nums
     * @return
     */
    public int maxSubArray2(int[] nums) {
        int max = nums[0];
        int former = 0;//用于记录dp[i-1]的值，对于dp[0]而言，其前面的dp[-1]=0
        int cur = nums[0];//用于记录dp[i]的值
        for(int num:nums){
            cur = num;
            if(former>0) cur +=former;
            if(cur>max) max = cur;
            former=cur;
        }
        return max;
    }

}
