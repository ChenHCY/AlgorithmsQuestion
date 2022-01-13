/* 977. Squares of a Sorted Array
Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.

Example 1:
Input: nums = [-4,-1,0,3,10]
Output: [0,1,9,16,100]
Explanation: After squaring, the array becomes [16,1,0,9,100].
After sorting, it becomes [0,1,9,16,100].

Example 2:
Input: nums = [-7,-3,2,3,11]
Output: [4,9,9,49,121]
*/

class Solution {
    public int[] sortedSquares(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int end = nums.length - 1;
        int[] res = new int[nums.length];
        
        while(end >= 0){
            if(Math.abs(nums[left]) > Math.abs(nums[right])){
                res[end] = nums[left] * nums[left];
                left++;
                end--;
            } else{
                res[end] = nums[right] * nums[right];
                right--;
                end--;
            }
        }
        
        return res;
    }
}
