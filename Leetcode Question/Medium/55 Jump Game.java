/* Leetcode 55. Jump Game

You are given an integer array nums. You are initially positioned at the array's first index, 
and each element in the array represents your maximum jump length at that position.

Return true if you can reach the last index, or false otherwise.

Example 1:
Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

Example 2:
Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
 
Constraints:
1 <= nums.length <= 104
0 <= nums[i] <= 105
*/

class Solution {
    public boolean canJump(int[] nums) {
        //remove 0 and null
        if(nums == null || nums.length == 0){
            return false;
        }
        
        //if nums only have one number 0, it should be return true
        //beacuse you already arrived last last index of nums[] list
        if(nums[0] == 0 && nums.length == 1){
            return true;
        }
        
        int index = 0; //use a pointer to save every possible position can jump
        for(int i = 0; i < nums.length; i++){
            //if can not move forward, break return false ==> means can not arrived last index
            if(index < i){
                break;
            }
            
            //find every possible jump position
            if(index < i + nums[i]){
                //each element in the array represents your maximum jump length at that position.
                index = i + nums[i];
            }
            
            //if arrived the last index, return true
            if(index >= nums.length - 1){
                return true;
            } 
        }
        
        return false; //can not arrived last index, return false
    }
}
