/*Leetcode 334. Increasing Triplet Subsequence

Given an integer array nums, return true if there exists a triple of indices (i, j, k) such that i < j < k and nums[i] < nums[j] < nums[k]. If no such indices exists, return false.

Example 1:
Input: nums = [1,2,3,4,5]
Output: true
Explanation: Any triplet where i < j < k is valid.

Example 2:
Input: nums = [5,4,3,2,1]
Output: false
Explanation: No triplet exists.

Example 3:
Input: nums = [2,1,5,0,4,6]
Output: true
Explanation: The triplet (3, 4, 5) is valid because nums[3] == 0 < nums[4] == 4 < nums[5] == 6.
 

Constraints:
1 <= nums.length <= 5 * 10^5
-2^31 <= nums[i] <= 2^31 - 1
*/
//Time: O(n) Space: O(1)
class Solution {
    // 我们要找到一个长度为3的递增子序列，所以我们只需要维护两个变量，然后遍历整个数组
    // 判断是否存在三个数字递增的情况
    public boolean increasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;

        for(int third : nums){
            //如果第三个数大于第二个，表示当前数前面有两个小于它的，符合要求的三元子序列(thiplet subsequence)
            if(third > second){
                return true;
            } 

            //如果 第二个数值second > 当前数third > first小 ==> 更新second变量，给第三个数值更多的选择空间
            else if(third > first && third < second){
                second = third;
            } else if(third < first){ //如果当前数别first小，更新first, 给后面更多的选择空间
                first = third;
            }
        }    

        return false;    
    }
}
