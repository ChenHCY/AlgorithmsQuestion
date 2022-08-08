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
        List<Integer> sub = new ArrayList<>();
        
        Arrays.sort(nums);
        
        dfs(res, sub, nums, 0);
        
        return res;
    }
    
    private void dfs(List<List<Integer>> res, List<Integer> list, int[] nums, int index){
        res.add(new ArrayList<>(list));
        
        for (int i = index; i < nums.length; i++) {
            list.add(nums[i]);
            //we want to add not contain duplicate subsets   
            dfs(res, list, nums, i + 1); //when the list.length equal nums.length (All subsets on one branch have been added). the code will move to next line 
            list.remove(list.size() - 1);   //remove last element we add(remove back)     
        }     
    }
}
