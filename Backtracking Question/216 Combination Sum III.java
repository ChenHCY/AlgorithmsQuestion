/* Leetcode 216. Combination Sum III

Find all valid combinations of k numbers that sum up to n such that the following conditions are true:

Only numbers 1 through 9 are used.
Each number is used at most once.
Return a list of all possible valid combinations. The list must not contain the same combination twice, and the combinations may be returned in any order.

Example 1:

Input: k = 3, n = 7
Output: [[1,2,4]]
Explanation:
1 + 2 + 4 = 7
There are no other valid combinations.

Example 2:

Input: k = 3, n = 9
Output: [[1,2,6],[1,3,5],[2,3,4]]
Explanation:
1 + 2 + 6 = 9
1 + 3 + 5 = 9
2 + 3 + 4 = 9
There are no other valid combinations.

Example 3:

Input: k = 4, n = 1
Output: []
Explanation: There are no valid combinations.
Using 4 different numbers in the range [1,9], the smallest sum we can get is 1+2+3+4 = 10 and since 10 > 1, there are no valid combination.
 

Constraints:

2 <= k <= 9
1 <= n <= 60
*/

//Used backtracking idea, check to find all valid combinations of k numbers that sum up to n

class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        //call backtracking function
        backtracking(res, list, k, n, 1);
        return res;
    }

    //backtracking function ==> "index" was used to limit Each number used at most once.
    public void backtracking(List<List<Integer>> res, List<Integer> list, int k, int n, int index){
        //exit and add contidion
        if(n == 0 && list.size() == k){
            res.add(new ArrayList<>(list)); //add the them into res list 
            return; //exit the backtracking function
        }

        //main for-loop to travser all the element 1 - 9
        for(int i = index; i <= 9; i++){
            list.add(i); //add the element into list
            backtracking(res, list, k, n - i, i + 1); //call Backtracking function move to next elelment
            list.remove(list.size() - 1); // remove and back to previous step 
        }
    }
}
