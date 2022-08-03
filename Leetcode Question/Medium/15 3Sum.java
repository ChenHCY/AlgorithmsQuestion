/*Leetcode 15. 3Sum

Given an integer array nums, return all the triplets 
[nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.

Example 1:
Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
Explanation: 
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
The distinct triplets are [-1,0,1] and [-1,-1,2].
Notice that the order of the output and the order of the triplets does not matter.

Example 2:
Input: nums = [0,1,1]
Output: []
Explanation: The only possible triplet does not sum up to 0.

Example 3:
Input: nums = [0,0,0]
Output: [[0,0,0]]
Explanation: The only possible triplet sums up to 0.
 

Constraints:
3 <= nums.length <= 3000
-105 <= nums[i] <= 105
*/

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        /*The Main idea: First determine that the value of i pointer (nums[i]) is temporarily fixed, 
        and then use the Two-pointer method to find j pointer and k pointer value.(Target value is 0 - nums[i]) */
        
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        Arrays.sort(nums); // The sort() sorts the elements of an array. 
        
        for(int i = 0; i < len; i++){
            if(i > 0 && nums[i] == nums[i-1]){
                continue; //Remove duplicate elements， the set must not contain duplicate triplets.
            }
            
            int target = 0 - nums[i]; //Set the target value
            int j = i + 1, k = len - 1; // Two-pointer Method j is left pointer, k is right pointer
            
            while(j < k){
                if(nums[j] + nums[k] == target){ // if find j+k = target ==> means here is nums[i] + nums[j] + nums[k] == 0. 
                    res.add(Arrays.asList(nums[i], nums[j], nums[k])); //as arraylist to add into res list
                    k--; //move k pointer into next element
                    j++;//move j pointer into next element
                    while(j < k && nums[j] == nums[j-1]) {
                        j++; //Remove duplicate elements， the set must not contain duplicate triplets.
                    } 
                    while(j < k && nums[k] == nums[k+1]) {
                        k--; //Remove duplicate elements， the set must not contain duplicate triplets.
                    }
                } else if(nums[j] + nums[k] > target){
                    k--; //Two-pointer Method: if large than target value, move k into less element
                } else{
                    j++;//Two-pointer Method: if small than target value, move j into greater element
                } 
            }
        }
        
        return res;
    }
}
