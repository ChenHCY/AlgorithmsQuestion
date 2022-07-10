/*Leetcode 219. Contains Duplicate II
Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.

Example 1:
Input: nums = [1,2,3,1], k = 3
Output: true

Example 2:
Input: nums = [1,0,1,1], k = 1
Output: true

Example 3:
Input: nums = [1,2,3,1,2,3], k = 2
Output: false
*/

//Solution 1: Used HashMap to find number and number position(i and j)
//Time complexity： O(n)
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(nums[i])){ // if have nums[i] == nums[j]
                int j = map.get(nums[i]); // get j position
                if(i - j <= k){ //abs(i - j) <= k. Because j is at previous value position, so i - j always should be positive
                    return true; //return true if there are two distinct indices
                }
            }
            map.put(nums[i], i); // Put the number of nums[] into HashMap one by one
        }
        
        return false;
    }
}
