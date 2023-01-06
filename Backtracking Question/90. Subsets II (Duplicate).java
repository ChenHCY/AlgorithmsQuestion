/* Leetcode 90. Subsets II

Given an integer array nums that may contain duplicates, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.

Example 1:

Input: nums = [1,2,2]
Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]

Example 2:

Input: nums = [0]
Output: [[],[0]]
 

Constraints:

1 <= nums.length <= 10
-10 <= nums[i] <= 10
*/

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        boolean[] used = new boolean[nums.length]; //checked whether visted before

        Arrays.sort(nums); //sort the nums[] array in the incresing order
        backtracking(res, list, nums, used, 0); //call backtracking function
        return res;
    }

    public void backtracking(List<List<Integer>> res, List<Integer> list, int[] nums, boolean[] used, int index){
        //exit and add condition
        res.add(new ArrayList<>(list));

        //main for-loop to travser all the element from nums[] array
        for(int i = index; i < nums.length; i++){
            //remove the duplicate subsets
            if(i != index && nums[i] == nums[i - 1] && !used[i - 1]){
                continue; //if meet duplicate number will have same subste, conitue and jump it
            }
          
            list.add(nums[i]); //add the element into list
            used[i] = true;
            backtracking(res, list, nums, used, i + 1);
            list.remove(list.size() - 1); //remove last element we add(remove back) 
            used[i] = false; //changed the previous element be false
        }
    }
}
