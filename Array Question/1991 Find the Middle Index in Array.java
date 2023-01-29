/* Leetcode 1991. Find the Middle Index in Array

Given a 0-indexed integer array nums, find the leftmost middleIndex (i.e., the smallest amongst all the possible ones).

A middleIndex is an index where nums[0] + nums[1] + ... + nums[middleIndex-1] == nums[middleIndex+1] + nums[middleIndex+2] + ... + nums[nums.length-1].

If middleIndex == 0, the left side sum is considered to be 0. Similarly, if middleIndex == nums.length - 1, the right side sum is considered to be 0.

Return the leftmost middleIndex that satisfies the condition, or -1 if there is no such index.


Example 1:

Input: nums = [2,3,-1,8,4]
Output: 3
Explanation: The sum of the numbers before index 3 is: 2 + 3 + -1 = 4
The sum of the numbers after index 3 is: 4 = 4


Example 2:

Input: nums = [1,-1,4]
Output: 2
Explanation: The sum of the numbers before index 2 is: 1 + -1 = 0
The sum of the numbers after index 2 is: 0


Example 3:

Input: nums = [2,5]
Output: -1
Explanation: There is no valid middleIndex.
 

Constraints:

1 <= nums.length <= 100
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
    public int findMiddleIndex(int[] nums) {
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
