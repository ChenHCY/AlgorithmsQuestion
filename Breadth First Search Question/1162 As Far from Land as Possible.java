/* Leetcode 1162. As Far from Land as Possible

Given an n x n grid containing only values 0 and 1, where 0 represents water and 1 represents land, 
find a water cell such that its distance to the nearest land cell is maximized, and return the distance. 

If no land or water exists in the grid, return -1.

The distance used in this problem is the Manhattan distance: the distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.

Example 1:
Input: grid = [[1,0,1],[0,0,0],[1,0,1]]
Output: 2
Explanation: The cell (1, 1) is as far as possible from all the land with distance 2.

Example 2:
Input: grid = [[1,0,0],[0,0,0],[0,0,0]]
Output: 4
Explanation: The cell (2, 2) is as far as possible from all the land with distance 4.
 

Constraints:

n == grid.length
n == grid[i].length
1 <= n <= 100
grid[i][j] is 0 or 1
*/

/* 思路：此题是要求找到当中立陆地最远距离的海洋格子，距离陆地的距离

方法： BFS + 记忆化搜索

1. 首先把所有陆地的岛屿的坐标（格子的值为1）， 存进到Queue中

2. 然后从这些陆地岛屿开始遍历和向四周移动

==》需要用到两个数组帮助
int[] dx = new int[]{1, -1, 0, 0};
int[] dy = new int[]{0, 0, 1, -1};

3. 检查是否越界，然后统计到达这些格子的距离

4. 再把每个到达的格子储存进Queue队列中，避免重复访问

5. 返回输出最后到达的格子和距离

详细思路在GitBook

*/

class Solution {
    public int maxDistance(int[][] grid) {
        //used for move into next grid space
      //用来进行格子移动和检查
        int[] dx = new int[]{1, -1, 0, 0};
        int[] dy = new int[]{0, 0, 1, -1};

        //grid的两个边界
        int m = grid.length;
        int n = grid[0].length;

        //用来判断是否存在陆地和海洋
        boolean hasOcean = false;
        boolean hasisLand = false;
        
        //BFS used the Queue to save the elements
        Queue<int[]> queue = new ArrayDeque<>();
        //首先把所有陆地储存进Queue队列中
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1){
                    hasisLand = true;
                    queue.add(new int[]{i, j});
                }
            }
        }

        // 从各个陆地开始，一圈一圈的遍历海洋，最后遍历到的海洋就是离陆地最远的海洋。
        int[] point = null; //用来查询最后一次遍历到的海洋格子距离
        while(!queue.isEmpty()){
            point = queue.poll();
            int x = point[0];
            int y = point[1];
            for(int i = 0; i < 4; i++){
                int newX = x + dx[i];
                int newY = y + dy[i];

                //如果越界的话， 或者这个格子之前已经访问过，则跳过格子
                if(newX < 0 || newX >= m || newY < 0 || newY >= n || grid[newX][newY] != 0){
                    continue;
                }
                grid[newX][newY] = grid[x][y] + 1; //count the distance value
                hasOcean = true; //可以找到海洋
                queue.offer(new int[] {newX, newY}); // 然后把这个格子加入到Queue队列中
            }
        }

        if(!hasisLand || !hasOcean){
            return -1;
        }

        //因为格子值为1的格子代表陆地，我们统计的距离也是从1开始增长的，所以要减去1 得到正确的distance
        return grid[point[0]][point[1]] - 1; 
    }
}
