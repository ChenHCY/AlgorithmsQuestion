/* Leetcode 62. Unique Paths

There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). 
The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.

Given the two integers m and n, return the number of possible unique paths that 
the robot can take to reach the bottom-right corner.

The test cases are generated so that the answer will be less than or equal to 2 * 109.

Example 1:
Input: m = 3, n = 7
Output: 28

Example 2:
Input: m = 3, n = 2
Output: 3
Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Down -> Down
2. Down -> Down -> Right
3. Down -> Right -> Down
 
Constraints:
1 <= m, n <= 100
*/
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n]; // Dp state: represents the maximum number of paths 
      
        // i is row, j is colum
        /*Dp function: 1. through first row or first colum
                       dp[i][j] = dp[0][j] ==> i = 0 first row only one path
                       dp[i][j] = dp[i][0] ==> j = 0 first colum only one path
                       2. the robot can only move either down or right at any point in time.
                       dp[i][j] = dp[i-1][j] ==> down direction
                       dp[i][j] = dp[i][j-1] ==> right direction
        */
      
        // The robot is initially located at the top-left corner (i.e., grid[0][0]). 
        dp[0][0] = 1; // Dp Initialize start position 
        
        //Traverse all the path solution
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                // For the first row dp[0][j], or the first column dp[i][0], there is only one path.
                if(i == 0 || j == 0){
                    dp[i][j] = 1; //so it means that the path is always one
                } else{ //The robot can only move either down or right at any point in time.
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        
        return dp[m-1][n-1]; // Dp answer: The robot tries to move to the bottom-right corner
    }
}
