/*Leetcode 120 Triangle  & Lintcode 109 Triangle

Given a triangle array, return the minimum path sum from top to bottom.
For each step, you may move to an adjacent number of the row below. More formally, 
if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.

Example 1:
Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
Output: 11
Explanation: The triangle looks like:
   2
  3 4
 6 5 7
4 1 8 3
The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).


Example 2:
Input: triangle = [[-10]]
Output: -10
Constraints:
1 <= triangle.length <= 200
triangle[0].length == 1
triangle[i].length == triangle[i - 1].length + 1
-104 <= triangle[i][j] <= 104
 
Follow up: Could you do this using only O(n) extra space, where n is the total number of rows in the triangle?
*/
//Solution 1: Basic Dynamic Programming Solution:
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle == null || triangle.size() == 0){
            return 0;
        }
         int rows = triangle.size(); //the row size of triangle list 
        
        // i is the row of triangle(height)
        // j is the column of triangle(width)
        // dp[i][j] represents the minimum path sum of row i and column j.
        int[][] dp = new int[rows][rows]; 
        dp[0][0] = triangle.get(0).get(0); // State Analysis: dp[0][0]=triangle[0][0]
        
        /* DP Function: dp[i][j]=dp[i−1][0] + triangle[i][0], j=0 (left side)
                       dp[i][j]=dp[i−1][i−1] + triangle[i][i], j==i (right side)
                       dp[i][j]=min(dp[i-1][j], dp[i-1][j-1]) + triangle[i][j], 0 < j < i */
        //Used for-loop traverse all the elements from triangle and calculate to find all the minimum path value 
        for(int i = 1; i < rows; i++){
            dp[i][0] = dp[i-1][0] + triangle.get(i).get(0); //through prev line's value, find curr line left-side value
            dp[i][i] = dp[i-1][i-1] + triangle.get(i).get(i); // through prev line's value, find curr line right-side value
            for(int j = 1; j < i; j++){ //this for-loop is used to find the middle part of the triangle path value
                dp[i][j] = Math.min(dp[i-1][j-1] + triangle.get(i).get(j), dp[i-1][j] + triangle.get(i).get(j));
            }
        }
        
        //Compare all the paths values of the triangle at last line
      //Since all possible minimum path sums have been calculated and found by DP, 
      //so we can compare the values in the last row of the triangle to get the final answer (minimum path sum)
        int minValue = dp[rows - 1][0];
        for(int k = 1; k < rows; k++){
            minValue = Math.min(minValue, dp[rows - 1][k]);
        }
        
        return minValue;
    }
}
