/* Leetcode 137. Single Number II 

Given an integer array nums where every element appears three times except for one, which appears exactly once. Find the single element and return it.
You must implement a solution with a linear runtime complexity and use only constant extra space.

Example 1:
Input: nums = [2,2,3,2]
Output: 3

Example 2:
Input: nums = [0,1,0,1,0,1,99]
Output: 99
 

Constraints:
1 <= nums.length <= 3 * 104
-231 <= nums[i] <= 231 - 1
Each element in nums appears exactly three times except for one element which appears once.
*/

class Solution {
    public int singleNumber(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int result = 0; // return the result
        int times = 0; // the times of number
        
        for(int num : nums){ // check every number times from nums list
            map.put(num, map.getOrDefault(num, 0)+ 1); 
        }
        
        for(int key : map.keySet()){ // find the single element
            if(map.get(key) == 1){
                result = key;
            }
        }
        
        return result; //return the single element
    }
}
