/* 148 · Sort Colors
Description
Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.
We will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Example 1

Input : [1, 0, 1, 2]
Output : [0, 1, 1, 2]
Explanation : sort it in-place
Challenge
Could you come up with an one-pass algorithm using only O(1) space?
*/

public class Solution {
    public void sortColors(int[] nums) {
        // write your code here
       int left = 0;
       int right = nums.length - 1;

       while(left <= right){
           while(left <= right && nums[left] != 2){
               left++;
           }

           while(left <= right && nums[right] == 2){
               right--;
           }

           if(left < right){
               int Temp = nums[left];
               nums[left] = nums[right];
               nums[right] = Temp;
               right--;
               left++;
           } 
       }  

       left = 0;
       right = nums.length - 1;

       while(left <= right){
           while(left <= right && nums[left] != 1){
               left++;
           }

           while(left <= right && nums[right] != 0){
               right--;
           }

           if(left < right){
               int Temp = nums[left];
               nums[left] = nums[right];
               nums[right] = Temp;
               right--;
               left++;
           } 
       }        
    }
}
