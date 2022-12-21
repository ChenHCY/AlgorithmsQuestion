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
        //Used a int[][] array res to save the distance of the nearest 0 for each cell.
        int[][] res = new int[mat.length][mat[0].length];

        //for-loop to travser all the element cell
        for(int i = 0; i < mat.length; i++){ //i ==> x row
            for(int j = 0; j < mat[0].length; j++){ //j ==> y column
                //start at 0 to find 1
                if(mat[i][j] == 0){
                    dfs(mat, res, i, j, 0); // call dfs function
                }
            }
        }
        //output and return the result
        return res;
    }

    //dfs function
    public void dfs(int[][] mat, int[][] res, int x, int y, int currDistance){
        //the exit contidion
        if(x < 0 || y < 0 || x > mat.length - 1|| y > mat[0].length - 1){
            return;
        }

        //main function
        if((currDistance == 0 || mat[x][y] == 1) && (res[x][y] == 0 || res[x][y] > currDistance)){
            res[x][y] = currDistance; // renew the new distance of the nearest 0 for current cell.
            dfs(mat, res, x + 1, y, currDistance + 1); // left
            dfs(mat, res, x - 1, y, currDistance + 1); // right
            dfs(mat, res, x, y + 1, currDistance + 1); // top
            dfs(mat, res, x, y - 1, currDistance + 1); // down
        }
    }
}
