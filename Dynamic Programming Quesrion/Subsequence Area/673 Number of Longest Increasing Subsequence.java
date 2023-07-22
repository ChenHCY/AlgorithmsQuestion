/*Leetcode 673. Number of Longest Increasing Subsequence

Given an integer array nums, return the number of longest increasing subsequences.

Notice that the sequence has to be strictly increasing.

Example 1:

Input: nums = [1,3,5,4,7]
Output: 2
Explanation: The two longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].

Example 2:

Input: nums = [2,2,2,2,2]
Output: 5
Explanation: The length of the longest increasing subsequence is 1, and there are 5 increasing subsequences of length 1, so output 5.
 

Constraints:

1 <= nums.length <= 2000
-106 <= nums[i] <= 10^6

*/
//Time: O(n^2)   Space: O(n)
class Solution {
    //此题是找到数组的最长递增 Subsequence (子序列 不需要连续) 有多少种组成的形式
    //使用DP动态规划
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int maxLen = 1; // 最长递增Subsequence的长度

        //两个DP
        int[] dp = new int[n]; // 里面存的是 以 nums[i] 为结尾的最长递增子序列的长度
        int[] count = new int[n]; //里面存的是 以 nums[i] 结尾的最长递增子序列的个数。

        //答案为所有满足 dp[i] = maxLen 的 i 所对应的 count[i] 之和

        //DP初始化
        Arrays.fill(dp, 1); //由于每个数都能独自一个成为子序列，因此起始必然有 dp[i] = 1
        Arrays.fill(count, 1); //由于每个数都能独自一个成为子序列，因此起始必然有 g[i] = 1

        /*开始遍历：状态方程
        1. 如果dp[j] + 1 > dp[i]; 说明递增子序列的长度增加了，
            ==> 所以更新子序列长度dp[i] = dp[j] + 1; 最长递增子序列的数量的不变的， count[i] = count[j]
        2. 如果dp[j] + 1 == dp[i]; 说明递增子序列的长度并没有增加, 数量发生了改变
            ==> 所以不用更新子序列的长度，需要最长递增子序列的数量 count[i] += count[j];
        */

        for(int i = 1; i < n; i++){
            for(int j = 0; j < i; j++){
                if(nums[j] < nums[i]){ //单调递增的
                    if(dp[j] + 1 > dp[i]){ //说明递增子序列的长度增加了
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    } else if(dp[j] + 1 == dp[i]){ //说明递增子序列的长度并没有增加
                        count[i] += count[j];
                    }
                }
            }
            maxLen = Math.max(maxLen, dp[i]); //用来记录最长的递增子序列长度
        }


        int res = 0;
        for(int i = 0; i < dp.length; i++){
            if(dp[i] == maxLen){ //找到DP中储存的所有 最长递增子序列长度值
                res += count[i]; //把他们对应的个数加起来 就是最长递增子序列的数量。
            }
        }

        return res;
    }
}
