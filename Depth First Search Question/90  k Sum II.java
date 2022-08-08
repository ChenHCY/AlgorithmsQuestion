/* Lintcode 90 · k Sum II

Description

Given n distinct positive integers, the integer k (1<=k<=n1<=k<=n) and a target number.
Find k distinct numbers within these n numbers such that the sum of these k numbers equals the target number, 
and you need to find all the solutions that satisfy the requirement (the order of the solutions is not required).

Example 1:
Input:
array = [1,2,3,4]
k = 2
target = 5

Output: [[1,4],[2,3]]
Explanation:  1+4=5,2+3=5

Example 2:
Input:
array = [1,3,4,6]
k = 3
target = 8

Output: [[1,3,4]]
Explanation: 1+3+4=8
*/

public class Solution {
    /**
     * @param a: an integer array
     * @param k: a postive integer <= length(A)
     * @param target: an integer
     * @return: A list of lists of integer
     *          we will sort your return value in output
     */
    public List<List<Integer>> kSumII(int[] a, int k, int target) {
        // write your code here
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> subset = new ArrayList<>();
        Arrays.sort(a);
        dfs(a, k, target, res, subset, 0);

        return res;
    }
  
    //dfs function 
    private void dfs(int[] a, int k, int target, List<List<Integer>> res, List<Integer> subset, int index){
         //recursive exit
        if(k == 0 && target == 0){
            res.add(new ArrayList<>(subset));
            return;
        }
        //if k is unreachable
        if(k == 0 && target !=0){
            return;
        } 

        for(int i = index; i < a.length; i++){
            subset.add(a[i]);
            //explore next element from a[] list 
            dfs(a, k-1, target - a[i], res, subset, i + 1);
            //backtrack
            subset.remove(subset.size() - 1);
        }
    }
}
