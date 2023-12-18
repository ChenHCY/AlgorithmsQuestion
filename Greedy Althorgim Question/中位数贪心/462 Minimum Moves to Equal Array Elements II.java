/*Leetcode 462. Minimum Moves to Equal Array Elements II

Given an integer array nums of size n, return the minimum number of moves required to make all array elements equal.

In one move, you can increment or decrement an element of the array by 1.

Test cases are designed so that the answer will fit in a 32-bit integer.

Example 1:
Input: nums = [1,2,3]
Output: 2
Explanation:
Only two moves are needed (remember each move increments or decrements one element):
[1,2,3]  =>  [2,2,3]  =>  [2,2,2]

Example 2:
Input: nums = [1,10,2,9]
Output: 16
 

Constraints:
n == nums.length
1 <= nums.length <= 10^5
-109 <= nums[i] <= 10^9
*/

/* 题目是要把nums所有数字都变成相同数字，得到需要的最小花费
==> 首先进行排序，然后找到已经存在的中位数
==> 遍历整个数组，计算花费 ==>这就是最小花费
*/
//Time: O(nlogn)   Space: O(1)
class Solution {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums); // O(nlogn) 
        int res = 0;
        int n = nums.length;

        //找到中位数
        int mid = nums[n / 2];

        //遍历整个数组 然后计算花费: O(n) 
        for(int num : nums){
            res += Math.abs(num - mid);
        }

        return res;
    }
}
