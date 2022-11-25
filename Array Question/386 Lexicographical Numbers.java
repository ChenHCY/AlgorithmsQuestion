/* Leetcode  386. Lexicographical Numbers

Given an integer n, return all the numbers in the range [1, n] sorted in lexicographical order.

You must write an algorithm that runs in O(n) time and uses O(1) extra space. 

Example 1:
Input: n = 13
Output: [1,10,11,12,13,2,3,4,5,6,7,8,9]

Example 2:
Input: n = 2
Output: [1,2]

Constraints:
1 <= n <= 5 * 104
*/

/*

Thought: Used DFS method

like a binary tree, add the number with frist dight sort one level by one level
*/

class Solution {
    List<Integer> res = new ArrayList<>();
    public List<Integer> lexicalOrder(int n) {        
        //start at 1-9
        for(int i = 1; i <= 9; i++){
            dfs(i, n); // n is means the number value limit
        }
        
        return res;
    }
    
    public void dfs(int num, int max){
        //the exit condition of dfs ==> every number should less than n
        if(num > max){
            return;
        }
        
        res.add(num);
        
        num *= 10; // start at the next level number
        
        //the exit condition of dfs ==> every number should less than n
        if(num > max){
            return;
        }
        
        for(int i = 0; i <= 9; i++){
            dfs(num + i, max);
        }
    }
}
