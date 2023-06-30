/*Leetcode (Contest 351) 2750 Ways to Split Array Into Good Subarrays

You are given a binary array nums.

A subarray of an array is good if it contains exactly one element with the value 1.

Return an integer denoting the number of ways to split the array nums into good subarrays. As the number may be too large, return it modulo 10^9 + 7.

A subarray is a contiguous non-empty sequence of elements within an array.
 
Example 1:

Input: nums = [0,1,0,0,1]
Output: 3
Explanation: There are 3 ways to split nums into good subarrays:
- [0,1] [0,0,1]
- [0,1,0] [0,1]
- [0,1,0,0] [1]

Example 2:

Input: nums = [0,1,0]
Output: 1
Explanation: There is 1 way to split nums into good subarrays:
- [0,1,0]
 

Constraints:

1 <= nums.length <= 10^5
0 <= nums[i] <= 1
*/

class Solution {
    public int numberOfGoodSubarraySplits(int[] nums) {
        /* 数组中的某个子数组 恰好 只存在 一 个值为 1 的元素，则认为该子数组是一个 好子数组 。 
            ==> 此题是要查找nums[]数组里面 分割成好子数组 的有多少种方式
            
            例子：[0 1 0 0 | 1 0 0 0 0 1] =》 以第二个1为分界线，左边有3种方式，右边是4种，所以有 3 * 4 = 12种分割 好子数组的方式
            所以这题就是一个 “乘法原理”，我们只需要计算没两个 ‘1’ 中有多少个0，然后把全部相乘 就是答案
        */

        final long MOD = (long) 1e9 + 7;
        long ans = 1;
        //pre记录的是每次遇到1，之前最近的1的index位置
        int pre = -1; //因为无法确定nums[0]是不是1，所以pre起始不能是0
        int n = nums.length;

        for(int i = 0; i < n; i++){
            if(nums[i] == 0){
                continue;
            }
            if(nums[i] == 1 && pre >= 0){
                ans = ans * (i - pre) % MOD;
            } 
            pre = i;
        }
        //最后判断如果pre没发生任何改变，nums = [0, 0]的情况，就无法进行分割成好子树组
        return pre < 0 ? 0 : (int) ans;
    }
}
