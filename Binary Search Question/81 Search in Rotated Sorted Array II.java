/* Leetcode 81. Search in Rotated Sorted Array II
There is an integer array nums sorted in non-decreasing order (not necessarily with distinct values).

Before being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length) 
such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] 
(0-indexed). For example, [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].

Given the array nums after the rotation and an integer target, return true if target is in nums, 
or false if it is not in nums.
You must decrease the overall operation steps as much as possible.

Example 1:
Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true

Example 2:
Input: nums = [2,5,6,0,0,1,2], target = 3
Output: false

Constraints:
1 <= nums.length <= 5000
-104 <= nums[i] <= 104
nums is guaranteed to be rotated at some pivot.
-104 <= target <= 104
*/

/* 解题思路：

双指针的方法，重点是两个指针如何缩小范围

   1. 要找到那一块区域是递增的。[left, mid) or [mid, right]
   2. 然后if-statement 检查 target是否存在于这个区间
      a. 如果是的， ==> 移动另一个指针
      b. 如果不是， ==> 移动本身的指针
   3. 因为nums[]不是纯递增数列，并且数组可能存在重复的数字
      For example: nums = [1,0,1,1,1] target = 0 
      所以nums[left] = nums[mid] 需要单独列为一直情况 ==》 直接舍弃
*/

//Time: O(n)   Space: O(1)
class Solution {
    public boolean search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        
        while(left <= right){
            int mid = left + (right - left) / 2;
            
            //find the target value
            if(nums[mid] == target){
                return true;
            }
            
            //find the increasing range 
            if(nums[left] < nums[mid]){ //the range of [left, mid] is increasing array
                if(nums[left] <= target && target < nums[mid]){
                    right = mid - 1;
                } else{
                    left = mid + 1;
                }
            } else if(nums[left] > nums[mid]){ //the range of [mid, right] is increasing array
                if(nums[mid] < target && target <= nums[right]){
                    left = mid + 1;
                } else{
                    right = mid - 1;
                }
            } else{ // nums[left] = nums[mid] == beacuse there maybe have duplicate number and nums[] is not increasing array
                left += 1; 
            } //For example: nums = [1,0,1,1,1] target = 0 ==> can not use if(nums[left] <= nums[mid])
        }
        
        return false;
    }
}
