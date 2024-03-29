/* Leetcode 153. Find Minimum in Rotated Sorted Array
Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,2,4,5,6,7] might become:

[4,5,6,7,0,1,2] if it was rotated 4 times.
[0,1,2,4,5,6,7] if it was rotated 7 times.
Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].

Given the sorted rotated array nums of unique elements, return the minimum element of this array.

You must write an algorithm that runs in O(log n) time.

Example 1:
Input: nums = [3,4,5,1,2]
Output: 1
Explanation: The original array was [1,2,3,4,5] rotated 3 times.

Example 2:
Input: nums = [4,5,6,7,0,1,2]
Output: 0
Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.

Example 3:
Input: nums = [11,13,15,17]
Output: 11
Explanation: The original array was [11,13,15,17] and it was rotated 4 times. 
 
Constraints:
n == nums.length
1 <= n <= 5000
-5000 <= nums[i] <= 5000
All the integers of nums are unique.
nums is sorted and rotated between 1 and n times.
*/

//Time: O(logn)   Space: O(1)
//Used Binary Search Method
class Solution {
    public int findMin(int[] nums) {
        //remove the base case
        if(nums.length == 1){
            return nums[0];
        }
        
        //it means find the first number elemnt in the before rotated  
        int left = 0;
        int right = nums.length - 1;
        
        //compare the nums[mid] with nums[right] value ==> beacuse the before is in ascending order to rotated
        //So the minimum should at the small value range part
        //==>[left, mid] or (mid, right] ==> used to move two pointer(left and right)
        while(left < right){
            int mid = left + (right - left) / 2;
            //need find the min number and this is rotated array
            //if nums[mid] > nums[right]; the min number should in the (mid right]
            if(nums[mid] > nums[right]){
                left = mid + 1;
            } else{ //if nums[mid] <= nums[right], the min number should in the [left mid]
                right = mid; // beacuse we can not sure whether the nums[mid] is minimum or not
            }
        }
        
        return nums[left];
    }
}
