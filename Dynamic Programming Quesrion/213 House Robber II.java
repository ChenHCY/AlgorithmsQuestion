/* Leetcode 213. House Robber II

You are a professional robber planning to rob houses along a street. Each house has a certain 
amount of money stashed. All houses at this place are arranged in a circle. That means the first 
house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected, 
and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money 
you can rob tonight without alerting the police.


Example 1:

Input: nums = [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.

Example 2:

Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.

Example 3:

Input: nums = [1,2,3]
Output: 3
 

Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 1000
*/

/*
这个题目是小偷偷房子的升级，区别在于所有房屋位于一个圆圈上，

==》第一个房子和最后一个房子也相邻，所以要满足相邻房子不能偷的条件
==》表示这两个房子只能选择一个

所以我们创建和复杂两个数组，一个不包括最后一间房，一个不包括第一间房

然后我们在这两个数组进行DP查找，找到能偷到的最大金额，后在进行比较

得到的值，就是小偷在这种情况下能偷到的最大值

 /* DP 思路： DP method
        1. Dp 数组 和 下标的含义：记录在这个index时能偷到的最大金额
        2. 递推公式：  dp[i] = Math.max(nums[i] + dp[i-2], dp[i-1]);
        3. DP数组的初始化：dp[0] = 1; dp[1] = nums[0]; 
        4. 确定遍历的顺序：从前往后遍历
        5. 举例推导DP数组
  */

*/

class Solution {
    public int rob(int[] nums) {
        if(nums.length == 0){
            return 0;
        }

        if(nums.length == 1){
            return nums[0];
        }
        int n = nums.length;
      
      // ==》第一个房子和最后一个房子也相邻，所以要满足相邻房子不能偷的条件
      // ==》表示这两个房子只能选择一个
      //所以我们创建和复杂两个数组，一个不包括最后一间房，一个不包括第一间房
      //Arrays.copyOfRange 也是左闭右开的区间 ==》右边的区间值需要写大一位
        int[] array1 = Arrays.copyOfRange(nums, 0, n-1); //从第一个数到 倒数第二个数，排除最后一个数
        int[] array2 = Arrays.copyOfRange(nums, 1, n); //从第二个数 到 最后一个数, 排除第一个数
        System.out.println(array2[0]);
        int res = Math.max(countSum(array1), countSum(array2));

        return res;
    }
  
    //DP function to count the maxinum money value that robber can rob it.
    public int countSum(int[] nums){
        int[] dp = new int[nums.length + 1]; //DP是根据前面的值来生成的，所以会有前置0，长度也比array多1
        dp[1] = nums[0];

        for(int i = 2; i <= nums.length; i++){
            dp[i] = Math.max(dp[i - 2] + nums[i - 1], dp[i - 1]);
        }

        return dp[nums.length]; //输出DP的最后一个值，也就是题目要求的能偷到的最大金额
    }
}
