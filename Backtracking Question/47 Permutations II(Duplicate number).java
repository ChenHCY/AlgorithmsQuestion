/* Leetcode 47. Permutations II
Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.

Example 1:
Input: nums = [1,1,2]
Output:
[[1,1,2],
 [1,2,1],
 [2,1,1]]
 
Example 2:
Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 
Constraints:
1 <= nums.length <= 8
-10 <= nums[i] <= 10
*/

//Solution 1: Used Backtracking Method
//Time: O(n!)   Space: O(n)
//If any number of recursive calls, T(n) satisfies the recurrence T(n) = T(n -1) + T(n-2) +  ... + T(1) + T(0)
//So the time will be O(n!) 

class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        
        //remove 0 and null
        if(nums.length == 0 || nums == null){
            return res;
        }
        Arrays.sort(nums); // used for remove duplicate arrays
        helper(res, list, nums, new boolean[nums.length]); //call backtracking function
        return res;
    }
    
    //Backtracking function  ==> boolean[] used means save whether used this number element
    public static void helper(List<List<Integer>> res, List<Integer> list, int[] nums, boolean[] used){
        //Add condition ==>when all the number have added into list
        if(list.size() == nums.length){
            res.add(new ArrayList<>(list)); //add the list into result arrayList
        }
        
        //The condition of Add the number into list ==> if there is have duplicate number
        //1. this number did not used it before ==> !used[i] 
        //2. After this number's first time used, can add a second time
        for(int i = 0; i < nums.length; i++){
            if(used[i] || (i > 0 && nums[i] == nums[i-1] && !used[i - 1])){
                continue;
            }
            used[i] = true; //means this current number was used
            list.add(nums[i]); //add current number into list
            helper(res, list, nums, used); //call backtracking that move forward
            // backtracking last level
            used[i] = false; //renew used[i] into "not used"
            list.remove(list.size() - 1); // remove current numbers to backtracking to last level
        }
    }
}

//Solution 2: Used Backtracking Method with a swap small function
//Time: O(n!)   Space: O(n)
