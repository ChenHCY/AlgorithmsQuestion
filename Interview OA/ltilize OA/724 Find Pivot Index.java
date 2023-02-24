/* Leetcode 724. Find Pivot Index ===》 This question is the same as Leetcode 1991 Find the Middle Index in Array

Given an array of integers nums, calculate the pivot index of this array.

The pivot index is the index where the sum of all the numbers strictly to the left of the index is equal to the sum 
of all the numbers strictly to the index's right.

If the index is on the left edge of the array, then the left sum is 0 because there are no elements to the left. 
This also applies to the right edge of the array.

Return the leftmost pivot index. If no such index exists, return -1.


Example 1:

Input: nums = [1,7,3,6,5,6]
Output: 3

Explanation:
The pivot index is 3.
Left sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11
Right sum = nums[4] + nums[5] = 5 + 6 = 11

Example 2:

Input: nums = [1,2,3]
Output: -1

Explanation:
There is no index that satisfies the conditions in the problem statement.

Example 3:

Input: nums = [2,1,-1]
Output: 0

Explanation:
The pivot index is 0.
Left sum = 0 (no elements to the left of index 0)
Right sum = nums[1] + nums[2] = 1 + -1 = 0
 

Constraints:

1 <= nums.length <= 104
-1000 <= nums[i] <= 1000
*/

/* 思路：

1. 首先for-loop 找到nums[]的总和

2. 然后有两个部分leftSum 和 rightSum

再次遍历nums[]，规则 ==》 rightSum = Sum - leftSum - currentSum(nums[i])

如果当两个部分值相等时，nums[i]就是 pivot index上的值

3.重点：有可能存在第一个数为pivot index的情况 ===》 nums = [2,1,-1]

所有要先判断，然后再把当前的值加入leftSum

*/

class Solution {
    public int pivotIndex(int[] nums) {
        int sum = 0; //the total sum
        int leftSum = 0; // the sum of the left part
        int rightSum = 0; // the sum of the right part

        //get the sum of nums
        for(int num : nums){
            sum += num;
        }

        //find the pivot index of num
        for(int i = 0; i < nums.length; i++){
            rightSum = sum - leftSum - nums[i];

            if(leftSum == rightSum){
                return i;
            }

            leftSum += nums[i];
        }

        return -1;
    }
}
