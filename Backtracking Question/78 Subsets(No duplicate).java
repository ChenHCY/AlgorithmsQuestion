/* Leetcode 78. Subsets

Given an integer array nums of unique elements, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.

Example 1:

Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]

Example 2:

Input: nums = [0]
Output: [[],[0]]
 

Constraints:

1 <= nums.length <= 10
-10 <= nums[i] <= 10
All the numbers of nums are unique.
*/

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        //call backtracking function
        backtracking(res, list, nums, 0);
        return res;
    }

    //backtracking function
    public void backtracking(List<List<Integer>> res, List<Integer> list, int[] nums, int index){
        //add condition
        res.add(new ArrayList<>(list));

        //main for-loop to travser all the element from nums[] array
        for(int i = index; i < nums.length; i++){
            list.add(nums[i]);
            backtracking(res, list, nums, i + 1);
            list.remove(list.size() - 1);
        }
    }
}
