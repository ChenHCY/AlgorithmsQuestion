/* 1. Two Sum
Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
You may assume that each input would have exactly one solution, and you may not use the same element twice.
You can return the answer in any order.

Example 1:
Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Output: Because nums[0] + nums[1] == 9, we return [0, 1].

Example 2:
Input: nums = [3,2,4], target = 6
Output: [1,2]

Example 3:
Input: nums = [3,3], target = 6
Output: [0,1]
*/
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] helper = Arrays.copyOf(nums, nums.length); // creative a new list to find which number
        Arrays.sort(helper); // let the list be a array list n
        
        int left = 0;
        int right = helper.length - 1;
        int x[] = new int[2]; // the result
        x[0] = -1;
        
        
        while(left < right){
            if(helper[left] + helper[right] < target){
                left++;
            } else if (helper[left] + helper[right] > target){
                right--;
            } else {
                break;
            }
        }
        
        for(int i = 0; i < nums.length; i++){
            if(x[0] == -1 && nums[i] == helper[left]){
                x[0] = i;
            } else if(nums[i] == helper[right]){
                x[1] = i;
            }
        }
        return x;
    }
}
