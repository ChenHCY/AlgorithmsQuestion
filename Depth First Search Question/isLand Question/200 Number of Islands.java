/* Leetcode 200. Number of Islands

Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 

You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1

Example 2:

Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3
 
Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 300
grid[i][j] is '0' or '1'.
*/

class Solution {
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        int row = grid.length; //the length of row
        int colum = grid[0].length; // the length of column
        int res = 0; // the numbers of island
        
        //travser all the element from grid
        for(int i = 0; i < row; i++){
            for(int j = 0; j < colum; j++){
                if(grid[i][j] == '1'){ //when find 1, means there is a island
                    res += 1; //add into res answer
                    dfs(grid, row, colum, i, j);
                }
            }
        }
        
        return res; //output the result
    }
    
    private static void dfs(char[][] grid, int row, int colum, int i, int j){
        //the exit condition of dfs
        if(i < 0 || i >= row || j < 0 || j >= colum || grid[i][j] == '0'){
            return;
        }
      
        // Mark the land viewed, to avoid add duplicate islands
        grid[i][j] = '0'; //it means this space 1 has visited before.
      
        //move to next grid spaces
        dfs(grid, row, colum, i - 1, j); //down
        dfs(grid, row, colum, i + 1, j); //up
        dfs(grid, row, colum, i, j - 1);//left
        dfs(grid, row, colum, i, j + 1);//right
    }
}
