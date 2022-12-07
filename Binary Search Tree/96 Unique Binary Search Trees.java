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
        //Dp State
        int[] dp = new int[n + 1];
        //Dp Initialize:
        dp[0] = 1;

        //Dp function: 
        for(int i = 1; i < dp.length; i++){
            for(int j = 0; j < i; j++){
                //we can assume i be root in every time
                //dp[j] is the value less than root(i) value part (the number of situations)
                //dp[i - j - 1] is the value larget than root{i} value part (the number of situations)
                dp[i] += dp[j] * dp[i - j - 1]; //then get the total number of situations
            }
        }
        
        //Dp return: return the dp result
        return dp[dp.length - 1];
    }
}
