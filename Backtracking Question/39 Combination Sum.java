/* Leetcode 39. Combination Sum 
Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates 
where the chosen numbers sum to target. You may return the combinations in any order.

The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the 
frequency of at least one of the chosen numbers is different.

The test cases are generated such that the number of unique combinations that sum up to target is less than 150 combinations for the given input.
 
Example 1:
Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]
Explanation:
2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
7 is a candidate, and 7 = 7.
These are the only two combinations.

Example 2:
Input: candidates = [2,3,5], target = 8
Output: [[2,2,2,2],[2,3,3],[3,5]]

Example 3:
Input: candidates = [2], target = 1
Output: []
 

Constraints:
1 <= candidates.length <= 30
2 <= candidates[i] <= 40
All elements of candidates are distinct.
1 <= target <= 40 
*/

//My Solution: 5ms  Beats 46.73%
//Time: O(n * m) ==> m is the candidates size      Space: O(n)
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        if(candidates == null ||candidates.length == 0){
            return res;
        }
        
        helper(res, list, candidates, target, 0); //call the backtracking method
        return res;
    }
    
    //Backtracking method: 
    private void helper(List<List<Integer>> res, List<Integer> list, int[] candidates, int target, int index){
        if(target < 0){ //Boundary conditions
            return; //exit the backtracking function
        }
        
        if(target == 0){ //when the target = 0, means find the requirment group numbers that sum to the target
            res.add(new ArrayList<>(list)); //add the them into res list 
            return;
        }
        
        for(int i = index; i < candidates.length; i++){
            list.add(candidates[i]); //add the current value into list
            helper(res, list, candidates, target - candidates[i], i); //call Backtracking function move to next elelment
            list.remove(list.size() - 1);  // remove and back to previous step
        }
    }
}

//The best time solution: 0ms  Beats 100%      **********************************
//Time: O(n)     Space: O(n)
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();

        //basic case
        if(candidates.length == 0){
            return res;
        }

        //call backtracking function
        backtracking(res, list, candidates, target, 0);
        return res;
    }

    //backtracking function ===> "Start" was used to delete duplicate numbers Combination
    public void backtracking(List<List<Integer>> res, List<Integer> list, int[] candidates, int target, int start){
        //exit and add condition
        if(target == 0){
            res.add(new ArrayList<>(list)); //add the them into res list 
            return; //exit the backtracking function
        }

        //main for loop to travser all the element from candidates array
        for(int i = start; i < candidates.length; i++){
            target -= candidates[i];
            if(target >= 0){
                list.add(candidates[i]);  //add the current value into list
                backtracking(res, list, candidates, target, i); //call Backtracking function move to next elelment
                list.remove(list.size() - 1);  // remove and back to previous step
            }
            target += candidates[i]; //add the value back into target
        }
    }
}
