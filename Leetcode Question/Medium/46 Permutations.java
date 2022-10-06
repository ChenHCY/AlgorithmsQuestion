/* Leetcode  46 Permutations

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
//Solution 1: Used Backtracking Method with ArrayList contains() function
//Time: O(n! * n)   Space: O(n)
//If any number of recursive calls, T(n) satisfies the recurrence T(n) = T(n -1) + T(n-2) +  ... + T(1) + T(0)
//The time will be O(n!)  ==> And add the contains() function time Q(n) ===> the total time will be O(n! * n) 
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        //Backtracking Method
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        
        //remove 0 and null
        if(nums.length == 0 || nums == null){
            return res;
        }
        
        helper(res, list, nums); //call backtracking method
        
        return res;
    }
    
    //Backtracking method
    public static void helper(List<List<Integer>> res, List<Integer> list, int[] nums){
        //Add condition
        if(list.size() == nums.length){ //if the arraylist length = nums.length
            //it means all the number from nums[] have ready add into arraylist 
            res.add(new ArrayList<>(list)); //so add the list into res arraylist
            return; // used to exit from a method
        }
        
        //travser the element from nums
        for(int i = 0; i < nums.length; i++){
            //check whether can find number in the arraylist
            if(list.contains(nums[i])){ // ==================================> contains() function's time is O(n)
                continue; //if contains same number, continue move forward
            }
            list.add(nums[i]); //add the number with not contains into arraylist
            helper(res, list, nums); //call backtracking move forward to next number in the nums[] list
            list.remove(list.size() - 1); //backtracking method: come back in last level and number
        }
    }
}

//Solution 2: Used Backtracking Method without ArrayList contains() function
//Time: O(n!)   Space: O(n)   ===========> beacuse did not use arraylist contains() function, so the time is O(n!)
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
        
        //Swap every two numbers from nums[] list
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
