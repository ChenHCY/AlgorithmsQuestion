/* Leetcode 40. Combination Sum II
Given a collection of candidate numbers (candidates) and a target number (target), 
find all unique combinations in candidates where the candidate numbers sum to target.

Each number in candidates may only be used once in the combination.
Note: The solution set must not contain duplicate combinations.

Example 1:
Input: candidates = [10,1,2,7,6,1,5], target = 8
Output: 
[
[1,1,6],
[1,2,5],
[1,7],
[2,6]
]

Example 2:
Input: candidates = [2,5,2,1,2], target = 5
Output: 
[
[1,2,2],
[5]
]
 

Constraints:
1 <= candidates.length <= 100
1 <= candidates[i] <= 50
1 <= target <= 30
*/

// My Solution: 8ms  Beats 39.37%
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>(); //save all the requirment numbers Combination
        List<Integer> list = new ArrayList<>(); //save the number Combination
        if(candidates == null || candidates.length == 0){
            return res; //remove branches
        }
        Arrays.sort(candidates); //use Arrays.sort to eliminate duplicates numbers
        helper(res, list, candidates, target, 0); //call backtracking method
        return res; //output result
    }
    
    //backtracking function
    private void helper(List<List<Integer>> res, List<Integer> list, int[] candidates, int target, int index){
        if(target < 0){ // the condition of exit and end backtracking
            return;
        }
        
        if(target == 0){ //when the target is 0, it means find requirment condidtaes numbers
            res.add(new ArrayList<>(list)); // add the value into res list
            return;
        }
        
        for(int i = index; i < candidates.length; i++){
            //do the eliminate duplicates numbers
            if(i != index && candidates[i] == candidates[i - 1]){
                continue; 
            } // when there are duplicate numbers in the candidate's list, only save one kind of Combination to output
            list.add(candidates[i]);
            helper(res, list, candidates, target - candidates[i], i + 1); 
            // i + 1 means every number only used once in the combination
            list.remove(list.size() - 1);
        }
    }
}
