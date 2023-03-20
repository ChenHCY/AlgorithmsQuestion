/* Leetcode 73. Set Matrix Zeroes

Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.

You must do it in place.

Example 1:
Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
Output: [[1,0,1],[0,0,0],[1,0,1]]

Example 2:
Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]

Constraints:

m == matrix.length
n == matrix[0].length
1 <= m, n <= 200
-231 <= matrix[i][j] <= 231 - 1
*/

/* 思路： 此题就是把matrix矩阵中，所有0值格子所在的行和列格子 都变成0值格子

所以可以通过DFS的办法，首先两个for-loop遍历找到每一个0值格子

然后使用DFS的function, 来改变当前0值格子所在的行和列。把所有格子都变成0

同时使用boolean[][] visited, 来记录访问过的格子，避免重复计算
*/

class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == 0 && !visited[i][j]){
                    dfs(matrix, m, n, i, j, visited);
                }
            }
        }
    }

    public void dfs(int[][] matrix, int m, int n, int x, int y, boolean[][] visited){
        visited[x][y] = true; //标记已经被访问过的格子

        if(x < 0 || x >= m || y < 0 || y >= n){
            return;
        }

        //改变0格子所在行的所有非零值，为0
        for(int i = 0; i < m; i++){
            if(matrix[i][y] != 0){
                visited[i][y] = true;
            }
            matrix[i][y] = 0;
        }

        
        //改变0格子所在行的所有非零值，为0
        for(int j = 0; j < n; j++){
            if(matrix[x][j] != 0){
                visited[x][j] = true;
            }
            matrix[x][j] = 0;
        }
    }
}
