/* Leetcode 377. Combination Sum IV

Given an array of distinct integers nums and a target integer target, return the number of possible combinations that add up to target.

The test cases are generated so that the answer can fit in a 32-bit integer.

Example 1:

Input: nums = [1,2,3], target = 4
Output: 7
Explanation:
The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)
Note that different sequences are counted as different combinations.

Example 2:

Input: nums = [9], target = 3
Output: 0
 

Constraints:

1 <= nums.length <= 200
1 <= nums[i] <= 1000
All the elements of nums are unique.
1 <= target <= 1000
*/

//Used Memorize Search (dp method) 
//Time: O(n^2)  Space: O(n)
// 一维动态规划时间复杂度一般有O(n)和O(n^2)两种，时间复杂度取决于状态转移方程。
// 如果第i个状态的确定需要利用前i-1个状态，即dp[i]由dp[i-1],dp[i-2],...,dp[0]的取值共同决定，那么此时的时间复杂度为O(n^2)。

class Solution {
    public int combinationSum4(int[] nums, int target) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        //dp state: means how many combination ways that add up to target
        int[] dp = new int[target + 1];

        dp[0] = 1; //dp initialize

        /*      Target Value:  0 1 2 3 4   nums[]: [1, 2, 3]  target = 4
            Combination ways:  1 1 2 4 7 ==> dp[i] = dp[i - nums[0]] + dp[i - nums[1]] .... + dp[i - nums[nums.length - 1]];
        */

        for(int i = 1; i < dp.length; i++){
            for(int num : nums){ //the combination numbers must be from nums[] array
                if(i - num >= 0){ //the index can not be negative
                    //it means this number can be added into the Combination that sum to the "target"
                    dp[i] += dp[i - num]; //dp function ==> count the how many Combination ways
                }
            }
        }

        return dp[target]; //dp return
    }
}
