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
      
        int x[] = new int[2];//Define the x be 2 numbers arraylist
        
        //hashmap to get result
        Map<Integer, Integer> result = new HashMap<>();
        
        //used for-loop to go through nums list
        for(int i = 0; i < nums.length; i++){
            if(result.containsKey(nums[i])) //if the hashmap contains the key value
            {
                x[0] = i; //the first number in the x[] is i
                x[1] = result.get(nums[i]); //the second number in the x[] is nums[i]
                break;
            }
            result.put(target - nums[i], i); //put the number "target - nums[i]" in the place "i"
        }
        return x; //output the result x
    }
}
