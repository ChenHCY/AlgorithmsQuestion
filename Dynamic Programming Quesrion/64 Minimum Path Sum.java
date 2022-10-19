/*Leetcode 64. Minimum Path Sum
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example 1:
Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
Output: 7
Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.

Example 2:
Input: grid = [[1,2,3],[4,5,6]]
Output: 12

Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 200
0 <= grid[i][j] <= 100

*/
class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        //1. first time travser all the element in the first row
        for(int i = 1; i < n; i++){
            grid[0][i] += grid[0][i-1];
        }
        
        //2. Second time travser all the element in the first column
        for(int j = 1; j < m; j++){
            grid[j][0] += grid[j-1][0];
        }
        
        //finally compare and get minvalue in the others space:
        for(int j = 1; j < m; j++){
            for(int i = 1; i < n; i++){
                grid[j][i] += Math.min(grid[j-1][i], grid[j][i-1]);
            }
        }
        
        //dp return
        return grid[m-1][n-1];
    }
}
