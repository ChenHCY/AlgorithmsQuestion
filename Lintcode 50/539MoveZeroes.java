/* 539 · Move Zeroes
Description
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

You must do this in-place without making a copy of the array.
Minimize the total number of operations.
Example
Example 1:

Input: nums = [0, 1, 0, 3, 12],
Output: [1, 3, 12, 0, 0].
Example 2:

Input: nums = [0, 0, 0, 3, 1],
Output: [3, 1, 0, 0, 0].
*/

public class Solution {
    public void moveZeroes(int[] nums) {
        // write your code here
        int left = 0;
        int right = 0;

        while(right < nums.length){
            if(nums[right] != 0){
                nums[left] = nums[right];
                left++;
            } 
            right++;
        }

        while(left < nums.length){
            nums[left] = 0;
            left++;
        }
    }
}
