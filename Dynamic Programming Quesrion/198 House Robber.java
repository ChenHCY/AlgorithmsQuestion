/* Leetcode 198. House Robber

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, 
the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and 
it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can 
rob tonight without alerting the police.


Example 1:

Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.


Example 2:

Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.
 

Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 400
*/

/* 思路： DP method
        1. Dp 数组 和 下标的含义：记录在这个index时能偷到的最大金额
        2. 递推公式：  dp[i] = Math.max(nums[i] + dp[i-2], dp[i-1]);
        3. DP数组的初始化：dp[0] = 1; dp[1] = nums[0]; 
        4. 确定遍历的顺序：从前往后遍历
        5. 举例推导DP数组
*/

class Solution {
    public int rob(int[] nums) {
        if(nums.length == 0){
            return 0;
        }

        int n = nums.length;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = nums[0];

        for(int i = 2; i <= n; i++){
            //因为DP数组会有前置 0 和 dp[1], 所以dp的容量应该比nums[] 大 1。
            dp[i] = Math.max(dp[i - 2] + nums[i - 1], dp[i - 1]); //所以DP数组的i 对应在 nums[]中的index 为 i - 1
        }

        return dp[n]; //输出DP的最后一个值，也就是题目要求的能偷到的最大金额
    }
}
