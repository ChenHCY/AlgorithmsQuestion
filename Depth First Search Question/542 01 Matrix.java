/* Leetcode 542. 01 Matrix

Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.

Example 1:
Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
Output: [[0,0,0],[0,1,0],[0,0,0]]

Example 2:
Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
Output: [[0,0,0],[0,1,0],[1,2,1]]
 

Constraints:
m == mat.length
n == mat[i].length
1 <= m, n <= 104
1 <= m * n <= 104
mat[i][j] is either 0 or 1.
There is at least one 0 in mat.
*/

class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int result[][] = new int[mat.length][mat[0].length];
        for(int i=0;i<mat.length;i++) {
            for(int j=0;j<mat[0].length;j++) {
                if(mat[i][j]==0) {
                    dfs(mat,result,i,j, 0);
                }
            }
        }
        return result;
    }
    private void dfs(int[][] mat, int[][] result, int i, int j, int currentDistance) {
        if(i<0||j<0||i==mat.length||j==mat[0].length) 
            return;
        if(currentDistance ==0 || mat[i][j]==1 && (result[i][j]==0 || result[i][j] > currentDistance)) {
            result[i][j] = currentDistance;
            dfs(mat,result,i+1,j,currentDistance+1);
            dfs(mat,result,i,j+1,currentDistance+1);
            dfs(mat,result,i-1,j,currentDistance+1);
            dfs(mat,result,i,j-1,currentDistance+1);
        }
    }
}
