/* Leetcode 695. Max Area of Island

You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) 

You may assume all four edges of the grid are surrounded by water.

The area of an island is the number of cells with a value 1 in the island.

Return the maximum area of an island in grid. If there is no island, return 0.

Example 1:

Input: grid = 

[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]

Output: 6
Explanation: The answer is not 11, because the island must be connected 4-directionally.


Example 2:

Input: grid = [[0,0,0,0,0,0,0,0]]
Output: 0
*/

/*
思路：

和之前的统计岛屿数量 一样的填岛算法，一样的DFS的思路，==》 int dfs(int[][] grid, int m, int n, int i, int j, int area）

区别在于这个问题需要返回最大岛屿的大小，所以dfs应该返回一个int值

当发现grid[i][j] == 1时，表示找到一块岛屿 

==>每次找到一块grid[i][j] == 1 /  岛屿的面积 + 1

然后向4个方向遍历查找，直到越出边界 或者 遇到海水

area = dfs(grid, m, n, i, j + 1, area);//right
area = dfs(grid, m, n, i, j - 1, area);//left
area = dfs(grid, m, n, i - 1, j, area); //down
area = dfs(grid, m, n, i + 1, j, area); //up ==》 dfs应该返回一个int值，所以每个岛屿的所有值加入到area

最后在主程序的for-loop遍历时，比较每一个岛屿大小，找到面积最大的
*/

class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int maxArea = 0;

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1){ //when find the island, start to search
                    int area = dfs(grid, m, n, i, j, 0);
                    //compare it and find the maxArea of islands
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }

    //DFS function
    public int dfs(int[][] grid, int m, int n, int i, int j, int area){
        //the exit condition ==> meet the ocean or out of the line
        if(i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 0){
            return area;
        }

        grid[i][j] = 0; //fill the visited island
        area += 1; //count the isLand area

        //check the for ways ==> find the current island total area
        //然后向4个方向遍历查找，直到越出边界 或者 遇到海水
        area = dfs(grid, m, n, i, j + 1, area);//right
        area = dfs(grid, m, n, i, j - 1, area);//left
        area = dfs(grid, m, n, i - 1, j, area); //down
        area = dfs(grid, m, n, i + 1, j, area); //up

        return area;
    }
}
