/* Leetcode 33. Search in Rotated Sorted Array
There is an integer array nums sorted in ascending order (with distinct values).
Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) 
such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). 

For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].

Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
You must write an algorithm with O(log n) runtime complexity.

Example 1:
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4

Example 2:
Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1

Example 3:
Input: nums = [1], target = 0
Output: -1
 
Constraints:
1 <= nums.length <= 5000
-104 <= nums[i] <= 104
All values of nums are unique.
nums is an ascending array that is possibly rotated.
-104 <= target <= 104
*/

/*   解题思路：

双指针的方法，重点是找到哪一块区域是纯递增的，然后移动两个指针来缩小范围。

     1. 要找到那一块区域是递增的。[left, mid) or [mid, right]
     2. 然后if-statement 检查 target是否存在于这个区间
      a. 如果是的， ==> 移动另一个指针
      b. 如果不是， ==> 移动本身的指针

*/

//Time: O(n)   Space: O(1)
class Solution {
    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0){
            return -1;
        }
        //Binary Search Method
        int left = 0; // left-pointer
        int right = nums.length - 1; //right-pointer
        
        while(left <= right){
            int mid = left + (right - left) / 2;
            
            //if find the target value
            if(nums[mid] == target){
                return mid;
            } 
            
            //Check whether the range of [left, mid] is in ascending order
            if(nums[left] <= nums[mid]){
                //Check whether target in the left range
                //Yes => move right-pointer || No => move left-pointer
                if(nums[left] <= target && target < nums[mid]){
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } 
            
            //Check whether the range of [mid, right] is in ascending order
            else{
                //Check whether target in the right range
                //Yes => move left-pointer || No ==> move right-pointer 
                if(nums[mid] < target && target <= nums[right]){
                    left = mid + 1;
                } else{
                    right = mid - 1;
                }
            }
        }
        
        return -1;
    }
}
