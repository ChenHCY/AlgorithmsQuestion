/* Leetcode 827. Making A Large Island

You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.

Return the size of the largest island in grid after applying this operation.

An island is a 4-directionally connected group of 1s.

 

Example 1:

Input: grid = [[1,0],[0,1]]
Output: 3
Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.


Example 2:

Input: grid = [[1,1],[1,0]]
Output: 4
Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.


Example 3:

Input: grid = [[1,1],[1,1]]
Output: 4
Explanation: Can't change any 0 to 1, only one island with area = 4.
 

Constraints:

n == grid.length
n == grid[i].length
1 <= n <= 500
grid[i][j] is either 0 or 1.
*/

/*

思路：

首先DFS function给每一个岛屿编号，并且统计面积

第一次遍历：遍历每一个岛屿 ==》 然后把岛屿编号和面积 存入HashMap中

第二次遍历：遍历每一个ocean的格子，检查把这个ocean变成陆地后，是否上下能联通岛屿

然后把联通的岛屿放入hashset中，避免出现一个岛屿被多次计算。

最后比较 ==》 找到可以制造出来的最大岛屿面积
*/
class Solution {
    public int largestIsland(int[][] grid) {
        //base case 
        if(grid == null || grid.length == 0){
            return 1;
        }

        int maxArea = 0; //the result of large island
        int label = 2; //the label of island
        int row = grid.length;
        int colu = grid[0].length;

        //connect the isLand lab with the area <编号, 岛屿面积>
        HashMap<Integer, Integer> map = new HashMap<>();
        //travser to find every island and count the label and area
        //将(岛屿索引，岛屿面积)存入map中。==> 编号后，每个岛屿的面积
        for(int i = 0; i < row; i++){
            for(int j = 0; j < colu; j++){
                if(grid[i][j] == 1){ //find the island
                    map.put(label, isLandArea(grid, row, colu, i, j, label));
                    label++;
                }
            }
        }

        //if the matrix did not have any island, all grid is ocea
        if(map.size() == 0){
            return 1; // change at most one 0 to be 1.
        }

        //当遇到一个非岛屿时，则判断上下左右是否连通。
	      //使用set去重，防止重复计算。
        for(int i = 0; i < row; i++){
            for(int j = 0; j < colu; j++){
                int area = 0;
                HashSet<Integer> set = new HashSet<>();
                if(grid[i][j] == 0){
                    area += 1; // change at most one 0 to be 1.

                    //Up ways
                    if(isLegal(grid, row, colu, i - 1, j) && grid[i-1][j] != 0 && set.add(grid[i-1][j])){
                        area += map.get(grid[i-1][j]);
                    }

                    //Down ways
                    if(isLegal(grid, row, colu, i + 1, j) && grid[i+1][j] != 0 && set.add(grid[i+1][j])){
                        area += map.get(grid[i+1][j]);
                    }

                    //Left ways
                    if(isLegal(grid, row, colu, i, j - 1) && grid[i][j-1] != 0 && set.add(grid[i][j-1])){
                        area += map.get(grid[i][j-1]);
                    }

                    //Right ways
                    if(isLegal(grid, row, colu, i, j + 1) && grid[i][j+1] != 0 && set.add(grid[i][j+1])){
                        area += map.get(grid[i][j+1]);
                    }
                }
                maxArea = Math.max(maxArea, area);
            }
        }

        //if all the matrix grid is island, did not have any ocean
        if(maxArea == 0){
            return map.get(2); //so there is only one island with label 2
        }

        return maxArea;
    }


    //DFS function: ==> give the label to island, and count the area
    public int isLandArea(int[][] grid, int row, int colu, int i, int j, int indexL){
        //the exit condition ==> if out of the bounds, or meat ocean, meet the island visited before
        if(!isLegal(grid, row, colu, i, j) || grid[i][j] != 1){
            return 0;
        }
        grid[i][j] = indexL; //give the island label index

        //move into next matrix grid(4 ways) and count the island area
        return isLandArea(grid, row, colu, i + 1, j, indexL) + isLandArea(grid, row, colu, i - 1, j, indexL) + isLandArea(grid, row, colu, i, j + 1, indexL) + isLandArea(grid, row, colu, i, j - 1, indexL) + 1;
    }

    //boolean function ==> check whether it out of bounds
    public boolean isLegal(int[][] grid, int row, int colu, int i, int j){
        if(i >= 0 && i < row && j >= 0 && j < colu){
            return true;
        }
        return false;
    }
}
