/* Leetcode 46. Permutations
Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.

Example 1:
Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

Example 2:
Input: nums = [0,1]
Output: [[0,1],[1,0]]

Example 3:
Input: nums = [1]
Output: [[1]]
 
Constraints:
1 <= nums.length <= 6
-10 <= nums[i] <= 10
All the integers of nums are unique.
*/

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        //Backtracking Method
        List<List<Integer>> res = new ArrayList<>();
        //remove 0 and null
        if(nums.length == 0 || nums == null){
            return res;
        }
        
        helper(res, nums, 0); //call backtracking method
        
        return res;
    }
    
    //backtracking method
    public static void helper(List<List<Integer>> res, int[] nums, int start){
        //Add condition 
        if(start == nums.length){ //if the start arrived nums.length
            List<Integer> list = new ArrayList<>();
            for(int num : nums){ //add into list 
                list.add(num);
            }
            res.add(new ArrayList<>(list)); //it means all the number from nums[] have ready add into arraylist
            return; //so add the list into res arraylist
        }
        
        //swap every two numbers from nums[] list
        for(int i = start; i < nums.length; i++){
            swap(nums, start, i); //swap start position and i-pointer 
            helper(res, nums, start + 1); //call backtracking
            swap(nums, start, i); //move back last level
        }
    }
    
    //Small function: swap the number in the nums[] list
    public static void swap(int[] nums, int l, int r){
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }
}
