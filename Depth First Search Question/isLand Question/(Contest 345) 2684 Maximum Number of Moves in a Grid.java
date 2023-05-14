/*Leetcode (Contest 345) 2684 Maximum Number of Moves in a Grid

You are given a 0-indexed m x n matrix grid consisting of positive integers.

You can start at any cell in the first column of the matrix, and traverse the grid in the following way:

From a cell (row, col), you can move to any of the cells: (row - 1, col + 1), (row, col + 1) and (row + 1, col + 1) 

such that the value of the cell you move to, should be strictly bigger than the value of the current cell.
Return the maximum number of moves that you can perform.

Example 1:
Input: grid = [[2,4,3,5],[5,4,9,3],[3,4,2,11],[10,9,13,15]]
Output: 3
Explanation: We can start at the cell (0, 0) and make the following moves:
- (0, 0) -> (0, 1).
- (0, 1) -> (1, 2).
- (1, 2) -> (2, 3).
It can be shown that it is the maximum number of moves that can be made.

Example 2:
Input: grid = [[3,2,4],[2,1,9],[1,1,7]]
Output: 0
Explanation: Starting from any cell in the first column we cannot perform any moves.
 

Constraints:
m == grid.length
n == grid[i].length
2 <= m, n <= 1000
4 <= m * n <= 10^5
1 <= grid[i][j] <= 10^6
*/
/* 思路： DP + DFS

使用DP来记录已经访问过的格子和其找到的路径中的最大总和值

然后DFS遍历每一种从第一列开头可能的路径，然后计算其可以产生的路径最大总和值

*/
class Solution {
    public int maxMoves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int maxSum = Integer.MIN_VALUE;;
        
        //使用DP来记录能找到的路径中的最大总和值
        int[][] dp = new int[m][n];
        for(int[] path : dp){
            Arrays.fill(path, -1); 
        }

        for(int i = 0; i < m; i++){
            int sum = dfs(grid, i, 0, m, n, -1, dp);
            maxSum = Math.max(maxSum, sum);
        }

        return maxSum - 1; //因为在DP里先前填了-1
    }

    //the dfs function
    public static int dfs(int[][] grid, int i, int j, int m, int n, int parent, int[][] dp){
        System.out.println("parent: " + parent);
        //the exit condition
        if(i < 0 || i >= m || j < 0 || j >= n || parent >= grid[i][j]){
            return 0;
        }

        System.out.println("grid[i][j]: " + grid[i][j]);

        //如果已经遇到访问过的格子和完成统计的最大路径值，则直接返回储存
        if(dp[i][j] != -1){
            return dp[i][j];
        }
        
        int l = dfs(grid, i - 1, j + 1, m, n, grid[i][j], dp);
        int r = dfs(grid, i, j + 1, m, n, grid[i][j], dp);
        int u = dfs(grid, i + 1, j + 1, m, n, grid[i][j], dp);
        
        dp[i][j] = Math.max(Math.max(l, u), r) + 1;
        return dp[i][j];
    }
}
