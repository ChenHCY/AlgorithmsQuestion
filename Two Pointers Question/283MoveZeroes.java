/* 283. Move Zeroes
Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
Note that you must do this in-place without making a copy of the array.

Example 1:
Input: nums = [0,1,0,3,12]
Output: [1,3,12,0,0]

Example 2:
Input: nums = [0]
Output: [0]
*/

class Solution {
    public void moveZeroes(int[] nums) {
        // Define the left and right pointer is the same way
        int left = 0;
        int right = 0;
        
        while(right < nums.length){
            if(nums[right] != 0){ // when nums[right] is not 0
                nums[left] = nums[right]; // move nums[right] value to nums[left] place
                left++; // everytime nums[right] is not 0, left move 1
            }
            right++; // everytime right move 1
        }
        
        while(left < nums.length){ // when the left still not arrive last number
            nums[left] = 0; // change them equal to 0
            left++;// every time left move 1
        }
    }
}
 
