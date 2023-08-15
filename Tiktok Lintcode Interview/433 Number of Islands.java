/*Lintcode 433 · Number of Islands

Given a boolean 2D matrix, 0 is represented as the sea, 1 is represented as the island. 

If two 1 is adjacent, we consider them in the same island. We only consider up/down/left/right adjacent.

Find the number of islands.

Example 1:

Input:
[
  [1,1,0,0,0],
  [0,1,0,0,1],
  [0,0,0,1,1],
  [0,0,0,0,0],
  [0,0,0,0,1]
]
Output:
3


Example 2:

Input:
[
  [1,1]
]
Output:
1
*/
//Solution 1: Used DFS Method
public class Solution {
    /**
     * @param grid: a boolean 2D matrix
     * @return: an integer
     */
    public int numIslands(boolean[][] grid) {
        // write your code here
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){ //when find 1, means there is a island
                if(grid[i][j] == true){
                    res += 1; //add into res answer
                    dfs(grid, m, n, i, j);
                }
            }
        }

        return res;
    }

    private void dfs(boolean[][] grid, int row, int colum, int i, int j){
        //如果越界 or 遇到海洋
        if(i < 0 || i >= row || j < 0 || j >= colum || grid[i][j] == false){
            return;
        }

        // Mark the land viewed, to avoid add duplicate islands
        grid[i][j] = false;

        //move to next grid spaces
        dfs(grid, row, colum, i - 1, j); //down
        dfs(grid, row, colum, i + 1, j); //up
        dfs(grid, row, colum, i, j - 1);//left
        dfs(grid, row, colum, i, j + 1);//right
    }
}
