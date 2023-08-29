/*Leetcode 2369. Check if There is a Valid Partition For The Array

You are given a 0-indexed integer array nums. You have to partition the array into one or more contiguous subarrays.

We call a partition of the array valid if each of the obtained subarrays satisfies one of the following conditions:

  1. The subarray consists of exactly 2, equal elements. For example, the subarray [2,2] is good.
  2. The subarray consists of exactly 3, equal elements. For example, the subarray [4,4,4] is good.
  3. The subarray consists of exactly 3 consecutive increasing elements, that is, the difference between adjacent elements is 1. For example, the subarray [3,4,5] is good, but the subarray [1,3,5] is not.

Return true if the array has at least one valid partition. Otherwise, return false.

Example 1:

Input: nums = [4,4,4,5,6]
Output: true
Explanation: The array can be partitioned into the subarrays [4,4] and [4,5,6].
This partition is valid, so we return true.

Example 2:

Input: nums = [1,1,1,2]
Output: false
Explanation: There is no valid partition for this array.
 

Constraints:
2 <= nums.length <= 10^5
1 <= nums[i] <= 10^6
*/


//DP 记忆化搜索
    //DP[i + 1] 表示从 [0, i] 这个区域内 是否能进行有效的划分
    //DP[i] = true 表示 可以进行有效划分  DP[i] = false 表示不行

    //需要的条件：dp[0] = true
    //1. 连续两个数字相等，nums[i] = nums[i - 1]      |      i >= 1      | => dpi + 1] = dp[i - 1] = true
    //2. 连续三个数字相等，nums[i] = nums[i - 1] = nums[i - 2]      | i >= 2               | => dp[i + 1] = dp[i - 2] = true
    //3. 连续三个数字增长，nums[i] = nums[i - 1] + 1 = nums[i - 2] + 2        | i >= 2           | =>dp[i + 1] = dp[i - 2] = true

class Solution {
    public boolean validPartition(int[] nums) {
        int n = nums.length;
        boolean[] dp = new boolean[n + 1];

        //dp初始化
        dp[0] = true;

        for(int i = 1; i < n; i++){
            //1. 连续两个数字相等
            if(nums[i] == nums[i - 1] && dp[i - 1]){
                dp[i + 1] = true; //因为dp存在前置0，所以index在dp数组中 都要向后移动一位
            }
            if(i > 1){
                //2. 连续三个数字相等，
                if(nums[i] == nums[i - 1] && nums[i] == nums[i - 2] && dp[i - 2]){
                    dp[i + 1] = true;
                }
                //3. 连续三个数字增长
                if(nums[i] == nums[i - 1] + 1 && nums[i] == nums[i - 2] + 2 && dp[i - 2]){
                    dp[i + 1] = true;
                }
            }
        }

        return dp[n];
    }
}
