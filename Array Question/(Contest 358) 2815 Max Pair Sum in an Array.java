/*Leetcode (Contest 358)  2815. Max Pair Sum in an Array

You are given a 0-indexed integer array nums. You have to find the maximum sum of a pair of numbers 
from nums such that the maximum digit in both numbers are equal.

Return the maximum sum or -1 if no such pair exists.

Example 1:

Input: nums = [51,71,17,24,42]
Output: 88
Explanation: 
For i = 1 and j = 2, nums[i] and nums[j] have equal maximum digits with a pair sum of 71 + 17 = 88. 
For i = 3 and j = 4, nums[i] and nums[j] have equal maximum digits with a pair sum of 24 + 42 = 66.
It can be shown that there are no other pairs with equal maximum digits, so the answer is 88.

Example 2:

Input: nums = [1,2,3,4]
Output: -1
Explanation: No pair exists in nums with equal maximum digits.
 

Constraints:

2 <= nums.length <= 100
1 <= nums[i] <= 10^4
*/

class Solution {
    public int maxSum(int[] nums) {
        int n = nums.length;
        int[] maxNum = new int[n];
        int maxSum = -1;
        
        //把每一个数中最大的digit字符存进maxNum array对应的index中
        for(int i = 0; i < n; i++){
            int maxDight = 0;
            int temp = nums[i];
            //find the max digit字符 of every number 
            while(temp > 0){
                maxDight = Math.max(maxDight, temp % 10);
                temp /= 10;
            }
            maxNum[i] = maxDight;
        }
        
        //然后两个for-loop循环，比较找到 每一个最大digit 相同的对数
        //然后统计保存这个对数的总和sum值，取最大的总和输出
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                if(maxNum[i] == maxNum[j]){
                    maxSum = Math.max(maxSum, nums[i] + nums[j]);
                }
            }
        }
        
        return maxSum;
    }
}
