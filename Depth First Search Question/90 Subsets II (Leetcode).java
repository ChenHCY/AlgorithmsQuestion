/* Leetcode 90. Subsets II
Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
The solution set must not contain duplicate subsets. Return the solution in any order.

Example 1:
Iput: nums = [1,2,2]
Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]

Example 2:
Input: nums = [0]
Output: [[],[0]]
 
Constraints:
1 <= nums.length <= 10
-10 <= nums[i] <= 10
*/

//Time: O(n * 2^n)  Space: O(n)
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        //call dfs function
        dfs(res, list, nums, used, 0);
        
        return res;
    }
    
    private void dfs(List<List<Integer>> res, List<Integer> list, int[] nums, boolean[] used, int index){
        //add the element condition
        res.add(new ArrayList<>(list));
        
        //exit the condition
        if(index == nums.length){
            return;
        }
        
        //basic travser for-loop
        for(int i = index; i < nums.length; i++){
            if(i > 0 && nums[i] == nums[i-1] && !used[i-1]){
                continue; //if meet duplicate number will have same subste, conitue and jump it
            }
            list.add(nums[i]); //add the element into list
            used[i] = true; 
            dfs(res, list, nums, used, i + 1); //when the list.length equal nums.length (All subsets on one branch have been added). the code will move to next line 
            list.remove(list.size() - 1); //remove last element we add(remove back)     
            used[i] = false; //changed the previous element be false
        }
    }
}
