/* Leetcode (Contest 332) 2563. Count the Number of Fair Pairs

Given a 0-indexed integer array nums of size n and two integers lower and upper, return the number of fair pairs.

A pair (i, j) is fair if:

0 <= i < j < n, and
lower <= nums[i] + nums[j] <= upper
 

Example 1:

Input: nums = [0,1,7,4,4,5], lower = 3, upper = 6
Output: 6
Explanation: There are 6 fair pairs: (0,3), (0,4), (0,5), (1,3), (1,4), and (1,5).


Example 2:

Input: nums = [1,7,9,2,5], lower = 11, upper = 11
Output: 1
Explanation: There is a single fair pair: (2,3).
*/

/*
思路： 此题是要统计nums[]中所有（i, j）对的和（sum) 是大于lower 和 小于upper的 有多少个

we need find the fair pair(i, j) that value is lower <= nums[i] + nums[j] <= upper

所以可以理解为值小于upper的对的数量 - 值小于lower的对的数量

==》 So it equals (the sum of fair pairs that value <= upper) - (the sum of fair pairs that value < low)

通过双指针的手法进行统计，两个指针来确定界限。当找到满足条件的区域时：右指针的位置 - 左指针的位置 就是 count的数量 ==》 count += (right - left);

*/

//Time complexity: O(n∗logn) + O(2n) = O(n∗logn)  Space complexity: O(1)
class Solution {
    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        long res = countPairs(nums, upper) - countPairs(nums, lower - 1);
        return res;
    }

    //we need find the fair pair(i, j) that value is lower <= nums[i] + nums[j] <= upper
    //So it equals (the sum of fair pairs that value <= upper) - (the sum of fair pairs that value < low)
    public long countPairs(int[] nums, int target){
        long count = 0;
        int left = 0;
        int right = nums.length - 1;

        while(left < right){
            int sum = nums[left] + nums[right];
            if(sum <= target){
                count += (right - left);
                left++;
            } else{
                right--;
            }
        }

        return count;
    }
}
