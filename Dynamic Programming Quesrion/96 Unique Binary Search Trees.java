/* Leetcode 96. Unique Binary Search Trees

Given an integer n, return the number of structurally unique BST's (binary search trees) which has exactly n nodes of unique values from 1 to n.

Example 1:

Input: n = 3
Output: 5

Example 2:

Input: n = 1
Output: 1
 

Constraints:

1 <= n <= 19
*/

/* 思路： DP method
        1. Dp 数组 和 下标的含义： i个节点数能组成多少种不同的二叉搜索树
        2. 递推公式： dp[i] += dp[以j为头节点，左子树节点能组成的二叉树形式] * dp[以j为头节点，右子树节点能组成的二叉树形式]
        ==> j相当于是头结点的元素，从1遍历到i为止。
        ==> 所以递推公式为：dp[i] += dp[j - 1] * dp[i - j];  
        ==> [j-1] 表示以j为头节点，左子树能组成多少种形式
        ==> [i-j] 表示以j为头节点，右子树能组成多少种形式
        3. DP数组的初始化：dp[0] = 1; 因为一个节点也没有，也是一种组成的方式
        4. 确定遍历的顺序：从前往后遍历节点数，
        ==》 从递归公式：dp[i] += dp[j - 1] * dp[i - j]可以看出，节点数为i的状态是依靠 i之前节点数的状态。
        ==》所以遍历i里面每一个数作为头节点的状态，通过j来遍历
 */

class Solution {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1; //初始化 dp 数组

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= i; j++){
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }

        return dp[n];
    }
}
