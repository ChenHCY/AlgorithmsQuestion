/* Leetcode 63. Unique Paths II
You are given an m x n integer array grid. There is a robot initially located at the 
top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). 

The robot can only move either down or right at any point in time.

An obstacle and space are marked as 1 or 0 respectively in grid. A path that the robot takes cannot include any square that is an obstacle.
Return the number of possible unique paths that the robot can take to reach the bottom-right corner.
The testcases are generated so that the answer will be less than or equal to 2 * 109.

Example 1:
Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
Output: 2
Explanation: There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right

Example 2:
Input: obstacleGrid = [[0,1],[0,0]]
Output: 1
 
Constraints:
m == obstacleGrid.length
n == obstacleGrid[i].length
1 <= m, n <= 100
obstacleGrid[i][j] is 0 or 1.
*/
// Time Complexity: O(M*N)  Space: O(n) ==> declare and Used a new int[][] dp
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        //Used Dp Method:
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
         //base case: the top-left corner or the bottom-right corner is obstacle
        if(obstacleGrid[0][0] == 1 || obstacleGrid[m-1][n-1] == 1){
            return 0; //it means did not have any possible unique paths that the robot can arrived
        }
        
        // i is row, j is colum
        /* Dp function: 1. through first row or first colum
                       dp[i][j] = dp[0][j] ==> i = 0 first row only one path
                       dp[i][j] = dp[i][0] ==> j = 0 first colum only one path
                       2. the robot can only move either down or right at any point in time.
                       dp[i][j] = dp[i-1][j] ==> down direction
                       dp[i][j] = dp[i][j-1] ==> right direction
                       3. Meet the obstacle
                       obstacleGrid[i][j] == 1 ==> Meet the obstacle
        */
        
        // Dp state: represents the maximum number of paths 
        int[][] dp = new int[m][n];
        
        //first row
        for(int i = 0; i < m && obstacleGrid[i][0] == 0; i++){
            dp[i][0] = 1;
        }
        
        //first column
        for(int j = 0; j < n && obstacleGrid[0][j] == 0; j++){
            dp[0][j] = 1;
        }
        
        
        //traverse all the path solution:
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(obstacleGrid[i][j] == 1){ //meet the obstacle
                    dp[i][j] = 0;
                    continue;
                } else{ //The robot can only move either down or right at any point in time.
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        
        return dp[m-1][n-1]; // Dp answer: The robot tries to move to the bottom-right corner
    }
}
