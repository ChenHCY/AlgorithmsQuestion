/* Leetcode 417. Pacific Atlantic Water Flow
There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean. The Pacific Ocean touches the island's left and top edges, 
and the Atlantic Ocean touches the island's right and bottom edges.

The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).

The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and west if the neighboring cell's height 
is less than or equal to the current cell's height. Water can flow from any cell adjacent to an ocean into the ocean.

Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.

Example 1:
Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
Explanation: The following cells can flow to the Pacific and Atlantic oceans, as shown below:
[0,4]: [0,4] -> Pacific Ocean 
       [0,4] -> Atlantic Ocean
[1,3]: [1,3] -> [0,3] -> Pacific Ocean 
       [1,3] -> [1,4] -> Atlantic Ocean
[1,4]: [1,4] -> [1,3] -> [0,3] -> Pacific Ocean 
       [1,4] -> Atlantic Ocean
[2,2]: [2,2] -> [1,2] -> [0,2] -> Pacific Ocean 
       [2,2] -> [2,3] -> [2,4] -> Atlantic Ocean
[3,0]: [3,0] -> Pacific Ocean 
       [3,0] -> [4,0] -> Atlantic Ocean
[3,1]: [3,1] -> [3,0] -> Pacific Ocean 
       [3,1] -> [4,1] -> Atlantic Ocean
[4,0]: [4,0] -> Pacific Ocean 
       [4,0] -> Atlantic Ocean
Note that there are other possible paths for these cells to flow to the Pacific and Atlantic oceans.

Example 2:

Input: heights = [[1]]
Output: [[0,0]]
Explanation: The water can flow from the only cell to the Pacific and Atlantic oceans.
 

Constraints:

m == heights.length
n == heights[r].length
1 <= m, n <= 200
0 <= heights[r][c] <= 10^5
*/

/* 思路： 此题是找到整张图中哪些节点的水既能流向太平洋，也能流向大西洋

因为存在高度问题， 使用dfs递归 查找每个格子的高度

我们要找到所有能流向太平洋和大西洋的格子坐标 + 我们是反向查找

所以如果之前的高度(preHeight) 大于 当前格子高度(heights[x][y])

 => 则这条路线被截断，无法流向两端， 
*/

class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> res = new ArrayList<>();
        //base case
        if(heights == null || heights.length == 0){
            return res;
        }

        int m = heights.length;
        int n = heights[0].length;

        //使用boolean[][] 储存每个节点是否能到达 太平洋 和 大西洋
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];

        //开始遍历：从太平洋和大西洋的 出发点开始：
        for(int i = 0; i < m; i++){
            dfs(heights, pacific, Integer.MIN_VALUE, i, 0); // 第一列
            dfs(heights, atlantic, Integer.MIN_VALUE, i, n - 1); // 最后一列
        }

        for(int j = 0; j < n; j++){
            dfs(heights, pacific, Integer.MIN_VALUE, 0, j); //第一行的所有格子
            dfs(heights, atlantic, Integer.MIN_VALUE, m - 1, j); //最后一行的所有格子
        }

        //遍历找到所有能同时流向太平洋和大西洋的格子
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(pacific[i][j] && atlantic[i][j]){
                    res.add(Arrays.asList(i, j));
                }
            }
        }

        return res;  
    }

    //the dfs function to check whether this point height can flow to the Pacific Ocean or the Atlantic Ocean
    //通过dfs遍历这张图中的每个节点高度，然后检查这些点中 哪些是可以流向 太平洋 和 大西洋的
    private void dfs(int[][] heights, boolean[][] visited, int preHeight, int x, int y){
        //exit condition: 边界条件, 如果当前遍历到的坐标 超出了这个图的边界，跳出递归
        if(x >= heights.length || x < 0 || y < 0 || y >= heights[0].length){
            return;
        }

        //exit conidtion: 当前节点之前已经访问过，跳出递归
        if(visited[x][y]){
            return;
        }

        //因为我们要找到所有能流向太平洋和大西洋的格子坐标 + 我们是反向查找
        //所以如果之前的高度(preHeight) 大于 当前格子高度(heights[x][y])
        // => 则这条路线被截断，跳出递归
        if(heights[x][y] < preHeight){
            return;
        }
       
        visited[x][y] = true;
       
        //移动到遍历下一个格子坐标， 四个方向
        dfs(heights, visited, heights[x][y], x + 1, y); // Down
        dfs(heights, visited, heights[x][y], x - 1, y); // Up
        dfs(heights, visited, heights[x][y], x, y - 1); // Left
        dfs(heights, visited, heights[x][y], x, y + 1); // right
    }
}
