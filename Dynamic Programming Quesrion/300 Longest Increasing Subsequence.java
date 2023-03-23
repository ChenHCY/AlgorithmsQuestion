/*Leetcode 300: Longest Increasing Subsequence

Given an integer array nums, return the length of the longest strictly increasing subsequence.

A subsequence is a sequence that can be derived from an array by deleting some 
or no elements without changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7]

Example:
Input: nums = [2, 1, 0, 3, 2, 3]
Output: 3
Explanation: The longest increasing subsequence is [1, 2, 3], therefore the length is 3
Note that there can be multiple longest increasing subsequences of same length
*/

//base case: just one number(index 0)
//common case： bottom up from base case to cooman case

/* 思路： DP method
        1. Dp 数组 和 下标的含义：dp[i]表示这nums[] array中 [0, i]的区间的最长递增子序列长度
        
        2. 状态转移方程(递推公式)：位置i的最长升序子序列等于j从0到i-1各个位置的最长升序子序列 + 1 的最大值。
        ==> dp[i] = Math.max(dp[i], dp[j] + 1); 
        
        3. DP数组的初始化：每一个i，对应的dp[i]（即最长递增子序列）起始大小至少都是1. 
        ==>  Arrays.fill(dp, 1);
        
        4. 确定遍历的顺序：因为dp[i]的值都是基于dp[0, j] ==> j其实就是遍历0到i-1， 
                          ==> 所以一定是从前往后遍历
                          
        5. 举例推导DP数组.
*/



class Solution {
    public int lengthOfLIS(int[] nums) {
        //remove 0
        if(nums.length == 0 || nums == null){
            return 0;
        }

        // Dp 数组 和 下标的含义
        int[] dp = new int[nums.length]; // dp[] save the length of increasing subsequence
        int res = 1;
        // DP数组的初始化
        Arrays.fill(dp, 1); // Arrays.fill() assigns the specified data type value to each element of the specified range of the specified array
        
        //this question is find Longest Increasing Subsequence
        //traverse and check every current number have how many previous numbers less than it
        for(int i = 0; i < nums.length; i++){ //i-pointer is means every current element
            for(int j = 0; j < i; j++){ //so j-pinter is used to check the prev number of current element
                if(nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1); //if curr number large than j-pointer number
                    //add one in the dp[] ==> dp[] save the length of increasing subsequence
                }
            }
            //so compare every i-pointer number's length of Increasing Subsequence
            res = Math.max(res, dp[i]); //Then find the Longest Increasing Subsequence
        }
        
        return res;
    }
}
