/*Leetcode 2401. Longest Nice Subarray

You are given an array nums consisting of positive integers.

We call a subarray of nums nice if the bitwise AND of every pair of elements that are in different positions in the subarray is equal to 0.

Return the length of the longest nice subarray.

A subarray is a contiguous part of an array.

Note that subarrays of length 1 are always considered nice.

Example 1:
Input: nums = [1,3,8,48,10]
Output: 3
Explanation: The longest nice subarray is [3,8,48]. This subarray satisfies the conditions:
- 3 AND 8 = 0.
- 3 AND 48 = 0.
- 8 AND 48 = 0.
It can be proven that no longer nice subarray can be obtained, so we return 3.

Example 2:
Input: nums = [3,1,5,11,13]
Output: 1
Explanation: The length of the longest nice subarray is 1. Any subarray of length 1 can be chosen.
 

Constraints:
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
*/
//Time: O(n)   Space: O(1)
class Solution {
    // 子数组中任意两个数 位 与（AND）运算 的结果等于 0
    // 所以 ==> 异或（XOR）运算 可以看作是“不进位的加法”
    // 所以在这种情况下，异或和和加法和相等。
    public int longestNiceSubarray(int[] nums) {
        int res = 0;
        int left = 0;
        int n = nums.length;

        //XORsum表示异或和，sum表示加法和
        int XORsum = 0;
        int sum = 0;

        for(int right = 0; right < n; right++){
            XORsum ^= nums[right]; //异或和
            sum += nums[right]; //加法和

            //如果滑动窗口内的数字 异或和 = 加法和 ==> 缩小窗口
            while(left < right && XORsum != sum){
                XORsum ^= nums[left];
                sum -= nums[left];
                left++;
            }

            res = Math.max(res, right - left + 1);
        }

        return res;
    }
}
