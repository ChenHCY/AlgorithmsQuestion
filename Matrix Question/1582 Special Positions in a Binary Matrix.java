/*Leetcode 1582. Special Positions in a Binary Matrix

Given an m x n binary matrix mat, return the number of special positions in mat.

A position (i, j) is called special if mat[i][j] == 1 and all other elements in row i and column j are 0 (rows and columns are 0-indexed).

Example 1:
Input: mat = [[1,0,0],[0,0,1],[1,0,0]]
Output: 1
Explanation: (1, 2) is a special position because mat[1][2] == 1 and all other elements in row 1 and column 2 are 0.

Example 2:
Input: mat = [[1,0,0],[0,1,0],[0,0,1]]
Output: 3
Explanation: (0, 0), (1, 1) and (2, 2) are special positions.
 

Constraints:
m == mat.length
n == mat[i].length
1 <= m, n <= 100
mat[i][j] is either 0 or 1.
*/

class Solution {
    public int numSpecial(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int res = 0;

        //使用两个数组row和column分别统计 每一行 每一列存在1的数量
        int[] row = new int[m];
        int[] column = new int[n];
        //遍历每一个格子，统计每一行 每一列数值为1的格子数量
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(mat[i][j] == 1){
                    row[i] += 1;
                    column[j] += 1;
                }
            }
        }

        //再次遍历格子，统计特殊位置的数量
        //当遍历到格子值为1的时候，检查row[] column[] 当前行和列 是否只存在当前唯一一个1
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(mat[i][j] == 1 && row[i] == 1 && column[j] == 1){
                    res++;
                }
            }
        }

        return res;
    }
}
