/*Leetcode 485. Max Consecutive Ones

Given a binary array nums, return the maximum number of consecutive 1's in the array.


Example 1:
Input: nums = [1,1,0,1,1,1]
Output: 3
Explanation: The first two digits or the last three digits are consecutive 1s. The maximum number of consecutive 1s is 3.

Example 2:
Input: nums = [1,0,1,1,0,1]
Output: 2
 
Constraints:
1 <= nums.length <= 105
nums[i] is either 0 or 1.
*/

class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int res = 0; //use for compare and find the maximum number of consecutive 1's
        
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 1){
                max += 1; //when find the nums[i] is 1, max length add 1
                res = Math.max(res, max); //every time compare current consecutive 1's length with prev consecutive 1's 
            } else {
                max = 0; //if nums[i] is 0, reset length back to 0
            }   
        }
        
        return res; 
    }
}
