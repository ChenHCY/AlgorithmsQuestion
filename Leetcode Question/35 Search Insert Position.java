/*35. Search Insert Position
Given a sorted array of distinct integers and a target value, return the index if the target is found. 
If not, return the index where it would be if it were inserted in order.
You must write an algorithm with O(log n) runtime complexity.

Example 1:
Input: nums = [1,3,5,6], target = 5
Output: 2

Example 2:
Input: nums = [1,3,5,6], target = 2
Output: 1

Example 3:
Input: nums = [1,3,5,6], target = 7
Output: 4
*/

//Used Binary Search Methond
// 1. right = nums.length - 1  ==> range of left close and right close
class Solution {
    public int searchInsert(int[] nums, int target) {
        int left = 0; // left point 
        int right = nums.length - 1; // right point
        
        while(left <= right){
            int mid = left + (right - left) / 2; 
            
            if(nums[mid] == target){ // if find target in the list, just return the mid index
                return mid;
            } else if(nums[mid] > target){ // if pivot value is large than target, move right point to pivot(mid) point and exclude the pvoit point.
                right = mid - 1;
            } else { // if pivot value is less than target, move left point to pivot(mid) point and exclude the pvoit point.
                left = mid + 1; 
            }
        }
        return left; // If still can not find target in the value, just return output the index where it would be if it were inserted in order. (the first less(left) index element)
    }
}

// 2. right = nums.length  ==> range of left close and right open
class Solution {
    public int searchInsert(int[] nums, int target) {
        int left = 0; // left point 
        int right = nums.length; // right point
        
        while(left < right){
            int mid = left + (right - left) / 2; 
            
            if(nums[mid] == target){ // if find target in the list, just return the mid index
                return mid;
            } else if(nums[mid] > target){ // if pivot value is large than target, move right point to pivot(mid) point and exclude the pvoit point.
                right = mid;
            } else { // if pivot value is less than target, move left point to pivot(mid) point and exclude the pvoit point.
                left = mid + 1; // because we need to find the index where can add the target value in order
            }
        }
        return left; // If still can not find target in the value, just return output the index where it would be if it were inserted in order. (the first less(left) index element)
    }
}

 
