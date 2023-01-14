/* Lintcode 110 · Minimum Path Sum
Description
Given a m * nm∗n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

The robot can only move either down or right at any point in time.

Example 1:
Input:
grid = [[1,3,1],[1,5,1],[4,2,1]]
Output:     7
Explanation:
Path is: 1 -> 3 -> 1 -> 1 -> 1

Example 2:
Input:
grid = [[1,3,2]]
Output:   6
Explanation:
Path is: 1 -> 3 -> 2
*/

public class Solution {
    /**
     * @param grid: a list of lists of integers
     * @return: An integer, minimizes the sum of all numbers along its path
     */
    public int minPathSum(int[][] grid) {
        // write your code here
        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n]; //Dp State 

        //The robot can only move either down or right at any point in time.
        //Dp Function: dp[0][0] = grid[0][0] ==> start postion(Top-Left) i = 0 j = 0
        //             dp[i][0] = dp[i-1][0] + grid[i][0] ==>  down direction i = 1 j = 0
        //             dp[0][j] = dp[0][j-1] + grid[0][j] ==> right direction i = 0 j = 1
        //Compare and find the Minimum Path: dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j]
        //             end postion: dp[m-1][n-1]

        dp[0][0] = grid[0][0]; //1. the start postion ==> Dp Initialize

        //2. down direction
        for(int i = 1; i < m; i++){
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }

        //3. right direction
        for(int j = 1; j < n; j++){
            dp[0][j] = dp[0][j-1] + grid[0][j];
        }

        //Compare and get the Minimum Path Sum
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }

        return dp[m-1][n-1]; //4.  end postion: dp[m-1][n-1]
    }
}
