/* Leetcode 300. Longest Increasing Subsequence

Given an integer array nums, return the length of the longest strictly increasing subsequence.

Example 1:

Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.

Example 2:

Input: nums = [0,1,0,3,2,3]
Output: 4

Example 3:

Input: nums = [7,7,7,7,7,7,7]
Output: 1
 

Constraints:

1 <= nums.length <= 2500
-104 <= nums[i] <= 104
*/

/*
思路： Dp 动态规划 记忆搜索

通过两个for-loop的指针来遍历nums中的所有数

因为递增数列可以不用一直连续，所有只用两个for-loop来找到递增的数，然后用dp统计长度

do[i] = dp[i] + 1  ==> Math.max(res, dp[i])

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
