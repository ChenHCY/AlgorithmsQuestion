/* Leetcode 53. Maximum Subarray
Given an integer array nums, find the subarray which has the largest sum and return its sum.

Example 1:
Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.

Example 2:
Input: nums = [1]
Output: 1

Example 3:
Input: nums = [5,4,-1,7,8]
Output: 23
 
Constraints:
1 <= nums.length <= 105
-104 <= nums[i] <= 104
 
Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
*/

/*
所以如果当我们在array[]中遇到负数时，一定是拉低sum的 ==> 这就是贪心贪的地方

==> 局部最优：当前“连续和”为负数的时候立刻放弃，从下一个元素重新计算“连续和”

==> 推导到的全局最优：选取最大“连续和”

*/

//Time: O(N)  Space: O(1)
class Solution {
    public int maxSubArray(int[] nums) {
        int sum = nums[0];
        int max = nums[0];
        
        for(int i = 1; i < nums.length; i++){
            if(sum < 0){
                sum = 0;
            }
            
            sum += nums[i];
            if(sum > max){
                max = sum;
            }
        }
            
        return max;
    }
}
