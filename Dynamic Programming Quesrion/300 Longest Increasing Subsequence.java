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

class solution{
  public int lengthOfLIS(int[] nums){
    if(nums.length == 0 || nums == null){
      return 0;
    }
    
    int[] dp = new int[nums.length];
    int res = 1
    
    for(int i = 0; i < nums.length; i++){
      for(int j = 0; j < i; j++){
        if(nums[i] > nums[j]){
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
      res = Math.max(dp[i], res);
    }
    
    return res;
  }
}
