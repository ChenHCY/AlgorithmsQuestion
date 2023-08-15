/*Lintcode 114  Unique Paths

A robot is located at the top-left corner of a m×n grid.

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid.

How many possible unique paths are there?

Example 1:

Input:

n = 1
m = 3
Output:

1
Explanation:

Only one path to target position.
*/

public class Solution {
    /**
     * @param m: positive integer (1 <= m <= 100)
     * @param n: positive integer (1 <= n <= 100)
     * @return: An integer
     */
    public int uniquePaths(int m, int n) {
        // write your code here
        
        //当到达每个格子时，有多少种最多可以到达这个格子的路径
        int[][] dp = new int[m][n];

        dp[0][0] = 1; //Dp initialize: 设定无法再拆解的极限小的状态下的值

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i == 0 || j == 0){
                    dp[i][j] = 1;
                } else{ //机器人只能往右和往下走，所以当前格子可能存在的路径只能来着上方和右方
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        return dp[m-1][n-1];//Dp Answer: 最后要求的答案是什么
    }
}
