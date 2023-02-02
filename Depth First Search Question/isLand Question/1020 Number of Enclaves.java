/* Leetcode 1020. Number of Enclaves

You are given an m x n binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.

A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the boundary of the grid.

Return the number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.

Example 1:
Input: grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
Output: 3
Explanation: There are three 1s that are enclosed by 0s, and one 1 that is not enclosed because its on the boundary.

Example 2:
Input: grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
Output: 0
Explanation: All 1s are either on the boundary or can reach the boundary.
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 500
grid[i][j] is either 0 or 1.
*/

/*
思路： 和Leetcode 130. Surrounded Regions 逻辑思路相似

使用DFS的填岛思路，从四周开始搜索（第一行，最后一行，第一列 和最后一列），找到能连接的所有岛屿

然后将这些岛屿填海（ grid[x][y] = 0; //change the isLand that visisted）

这样图中留下的岛屿就一定是Enclaves（飞地）

再用for-loop遍历每一块地方，找到所有grid[i][j] == 1 的地方

==》 得到最后的面积


*/

class Solution {
    public int numEnclaves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;

        //search start at the first and last row
        for(int j = 0; j < n; j++){
            if(grid[0][j] == 1){
                dfs(grid, m, n, 0, j);
            }

            if(grid[grid.length - 1][j] == 1){
                dfs(grid, m, n, grid.length - 1, j);
            }
        }

        //search start at the first and last column
         for(int i = 0; i < m; i++){
            if(grid[i][0] == 1){
                dfs(grid, m, n, i, 0);
            }

            if(grid[i][grid[0].length - 1] == 1){
                dfs(grid, m, n, i, grid[0].length - 1);
            }
        }

        //after all the search finished start at four lines
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1){
                    res += 1;
                }
            }
        }

        return res;
    }

    //DFS function
    public static void dfs(int[][] grid, int m, int n, int x, int y){
        //the exit condition
        if(x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 0){
            return;
        }

        grid[x][y] = 0; //change the isLand that visisted

        dfs(grid, m, n, x, y + 1); //right ways
        dfs(grid, m, n, x, y - 1); //left ways
        dfs(grid, m, n, x + 1, y); //Up
        dfs(grid, m, n, x - 1, y); //Down
    }
}
