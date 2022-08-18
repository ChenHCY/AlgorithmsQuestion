/*LintCode 109 · Triangle && Leetcode 120 · Triangle
Description
Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

Example 1:
Input:
triangle = [
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
Output:    11
Explanation:
The minimum path sum from top to bottom is 11 (2 + 3 + 5 + 1 = 11).

Example 2:
Input:
triangle = [
     [2],
    [3,2],
   [6,5,7],
  [4,4,8,1]
]
Output:    12
Explanation:
The minimum path sum from top to bottom is 12 (2 + 2 + 7 + 1 = 12).
*/

public class Solution {
    /**
     * @param triangle: a list of lists of integers
     * @return: An integer, minimum path sum
     */
    public int minimumTotal(int[][] triangle) {
        // write your code here
        int len = triangle.length; // the rows of triangle
        // i is the row of triangle(height)
        // j is the column of triangle(width)
        // dp[i][j] represents the minimum path sum of row i and column j.
        int[][] dp = new int[len][len];// dp method
        dp[0][0] = triangle[0][0]; // State Analysis: dp[0][0]=triangle[0][0]

        /* DP Function: dp[i][j]=dp[i−1][0] + triangle[i][0], j=0 (left side)
                       dp[i][j]=dp[i−1][i−1] + triangle[i][i], j==i (right side)
                       dp[i][j]=min(dp[i-1][j], dp[i-1][j-1]) + triangle[i][j] (mid part area), 0 < j < i */
        for(int i = 1; i < len; i++){
            //the left side of every rows:
            dp[i][0] = dp[i-1][0] + triangle[i][0];

             //the right side of every rows:
            dp[i][i] = dp[i-1][i-1] + triangle[i][i];

            //the mid part of triangle:
            for(int j = 1; j < i; j++){
                dp[i][j] = Math.min(dp[i-1][j-1] + triangle[i][j], dp[i-1][j] + triangle[i][j]);
            }
        }

         //Compare all the paths values of the triangle at last line
      //Since all possible minimum path sums have been calculated and found by DP, 
      //so we can compare the values in the last row of the triangle to get the final answer (minimum path sum)
        int minValue = dp[len - 1][0];
        for(int k = 0; k < len; k++){
            minValue = Math.min(minValue, dp[len-1][k]);
        }

        return minValue;
    }
}
