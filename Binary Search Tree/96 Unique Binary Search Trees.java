/* Leetcode 96. Unique Binary Search Trees
Given an integer n, return the number of structurally unique BST's (binary search trees) 
which has exactly n nodes of unique values from 1 to n.

Example 1:

Input: n = 3
Output: 5

Example 2:

Input: n = 1
Output: 1
 

Constraints:
1 <= n <= 19
*/

//Dp Method
class Solution {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;

        for(int i = 1; i < dp.length; i++){
            for(int j = 0; j < i; j++){
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        
        return dp[dp.length - 1];
    }
}
