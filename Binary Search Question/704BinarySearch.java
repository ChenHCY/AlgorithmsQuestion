/* 
Given an array of integers nums which is sorted in ascending order, and an integer target, write a function to search target in nums. If target exists, then return its index. Otherwise, return -1.

You must write an algorithm with O(log n) runtime complexity.

Example 1:

Input: nums = [-1,0,3,5,9,12], target = 9
Output: 4
Explanation: 9 exists in nums and its index is 4
Example 2:

Input: nums = [-1,0,3,5,9,12], target = 2
Output: -1
Explanation: 2 does not exist in nums so return -1
*/

class Solution {
    public int search(int[] nums, int target) 
    {
        if (nums == null || nums.length == 0) 
        {
            return -1; //if the nums is 0 or nums have no length, the output result is -1
        }
        
        int start = 0; //Define the start number
        int end = nums.length - 1;//Define the end number
        
        while(start <= end) //while loop, it will end  if start > end
        {    
            int mid = start + (end - start) / 2; // Find the mid number place
            
            if(target == nums[mid] ) 
            {
                return mid; //if the mid number value in the nums list is equal target number, the output reselt is mid
            } 
            
            else if(target < nums[mid]) 
            {
                end = mid - 1; //Binary Search: if target value less than the mid value, the end number will move to mid-1
            } 
            
            else 
            {
                start = mid + 1;//Binary Search: if target value large than the mid value, the start number will move to mid+1
            }  
        }
        return -1;// nothing can run, just output the result is -1
    }
}
