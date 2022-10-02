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

class Solution {
    public int lengthOfLIS(int[] nums) {
        //remove 0
        if(nums.length == 0 || nums == null){
            return 0;
        }
        
        /*
        Dp State: use dp[] save the length of increasing subsequence
        Dp Initialize: dp[0] = 1
        Dp Function: do[i] = dp[i] + 1  ==> Math.max(res, dp[i])
        Dp Answer: return res
        */
        
        int[] dp = new int[nums.length]; // dp[] save the length of increasing subsequence
        int res = 1;
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
