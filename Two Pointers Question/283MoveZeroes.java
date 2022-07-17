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
        int left = 0;
        int right = 0;
        
        while(right < nums.length){
            if(nums[right] != 0){// when nums[right] is not 0
                nums[left] = nums[right]; // move nums[right] value to nums[left] place
                left++; // everytime nums[right] is not 0, left-pointer move 1
            }
            
            right++; // everytime right-pointer move 1
        }
        
        
        //this question, was used left-pointer to calculate how many 0 that nums list have it
        while(left < nums.length){// when the left still not arrive last number
            nums[left] = 0;// change all the element start at left-pointer equal to 0
            left++; 
        }
    }
}
 
