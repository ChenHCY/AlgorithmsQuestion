/* Leetcode 694 Number of Distinct Islands && Lintcode 860 Number of Distinct Islands

Describe:

Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical). 
You may assume all four edges of the grid are surrounded by water.

Count the number of distinct islands. An island is considered to be the same as another if and only if one island has the same shape as another 
island (and not rotated or reflected).

Notice that:

11
1
and

 1
11
are considered different island, because we do not consider reflection / rotation.


Example 1:

Input: 
  [
    [1,1,0,0,1],
    [1,0,0,0,0],
    [1,1,0,0,1],
    [0,1,0,1,1]
  ]
Output: 3
Explanation:
  11   1    1
  1        11   
  11
   1
Example 2:

Input:
  [
    [1,1,0,0,0],
    [1,1,0,0,0],
    [0,0,0,1,1],
    [0,0,0,1,1]
  ]
Output: 1
*/

/* 思路：

DFS的想法：也是通过DFS找到每一个isLand， 填岛算法

然后记录这个岛屿的查找的路径，因为DFS遍历的规则都是一样的

所以两个岛屿的查找路径字符串的相等 代表 这两个岛屿时一样的形状

*/

public class Solution {
    /**
     * @param grid: a list of lists of integers
     * @return: return an integer, denote the number of distinct islands
     */
    public int numberofDistinctIslands(int[][] grid) {
        // write your code here
        int row = grid.length;
        int column = grid[0].length;

        //used the hashset to remove the duplicate islands
        HashSet<String> set = new HashSet<>();

        //travese all the element form grid[][]
        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++){
                if(grid[i][j] == 1){
                    StringBuilder sb = new StringBuilder();
                    dfs(grid, row, column, i, j, '0', sb);
                    set.add(sb.toString());
                }
            }
        }

        return set.size();
    }

    //DFS Question: Find and save the islands shape
    public static void dfs(int[][] grid, int row, int colum, int i, int j, char path, StringBuilder str){
        //the exit condition of dfs
        if(i < 0 || i >= row || j < 0 || j >= colum || grid[i][j] == 0){
            return;
        }

        // Mark the land viewed, to avoid add duplicate islands
        grid[i][j] = 0; //it means this space 1 has visited before.
      
        str.append(path); //add the character into stringbuilder
      
         //move to next grid spaces
        dfs(grid, row, colum, i, j + 1, 'R', str);//right
        dfs(grid, row, colum, i, j - 1, 'L', str);//left
        dfs(grid, row, colum, i - 1, j, 'D', str); //down
        dfs(grid, row, colum, i + 1, j, 'U', str); //up
      
        str.append(path); //add the character into stringbuilder
    }
}
