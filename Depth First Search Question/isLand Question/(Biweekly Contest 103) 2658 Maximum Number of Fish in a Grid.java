/* Leetcode (Biweekly Contest 103) 2658 Maximum Number of Fish in a Grid

You are given a 0-indexed 2D matrix grid of size m x n, where (r, c) represents:

     A land cell if grid[r][c] = 0, or
    A water cell containing grid[r][c] fish, if grid[r][c] > 0.
A fisher can start at any water cell (r, c) and can do the following operations any number of times:

    Catch all the fish at cell (r, c), or
    Move to any adjacent water cell.
Return the maximum number of fish the fisher can catch if he chooses his starting cell optimally, or 0 if no water cell exists.

An adjacent cell of the cell (r, c), is one of the cells (r, c + 1), (r, c - 1), (r + 1, c) or (r - 1, c) if it exists.

Example 1:
Input: grid = [[0,2,1,0],[4,0,0,3],[1,0,0,4],[0,3,2,0]]
Output: 7
Explanation: The fisher can start at cell (1,3) and collect 3 fish, then move to cell (2,3) and collect 4 fish.

Example 2:
Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,1]]
Output: 1
Explanation: The fisher can start at cells (0,0) or (3,3) and collect a single fish. 
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 10
0 <= grid[i][j] <= 10
*/

/* 此题是统计渔民在一个matrix中能捕获鱼最多的数量，渔民只能向四个方向移动，当遇到海水或者超出边界时则停止。

==》思路： 此题的思路和 Leetcode 695. Max Area of Island的想法基本一样，只是把每次记录的值改为当前grid[][]的格子值，也就是渔民在这个区域能捕获的鱼数量

和之前的统计岛屿数量 一样的填岛算法，一样的DFS的思路，==》 int dfs(int[][] grid, int m, int n, int i, int j, int area）

 然后向4个方向继续捕鱼，统计所有鱼的数量。直到越出边界 或者 遇到海水
sum += dfs(grid, x + 1, y, m, n, sum); //Up
sum += dfs(grid, x - 1, y, m, n, sum); //Down
sum += dfs(grid, x, y + 1, m, n, sum); //right
sum += dfs(grid, x, y - 1, m, n, sum); //left==》 dfs应该返回一个int值，所以每个grid[][]区域的格子值都加入到sum中

最后在主程序的for-loop遍历时，比较每一个格子为起点时，线路能捕获鱼的数量，最后找到最多的数量
*/

//Time: O(m * n) 其中 m 和 n 分别为 grid 的行数和列数。
//Space:  O(m * n)。递归需要 O(m*n) 的栈空间。

class Solution {
    public int findMaxFish(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int maxSum = 0;

        //以每个格子为起点，进行捕鱼。然后找到最大的捕鱼数量
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                int sum = dfs(grid, i, j, m, n, 0);
                maxSum = Math.max(maxSum, sum);
            }
        }

        return maxSum;
    }

    public static int dfs(int[][] grid, int x, int y, int m, int n, int sum){
        //the exit condition
        if(x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 0){
            return 0;
        }

        sum = grid[x][y]; //记录这个当前格子区域能补到鱼的数量
        grid[x][y] = 0; //把当前格子的值改为0，标记已经访问过这个格子区域

        //check the four ways and continue Catch the fish
        //然后向4个方向继续捕鱼，统计所有鱼的数量。直到越出边界 或者 遇到海水
        sum += dfs(grid, x + 1, y, m, n, sum); //Up
        sum += dfs(grid, x - 1, y, m, n, sum); //Down
        sum += dfs(grid, x, y + 1, m, n, sum); //right
        sum += dfs(grid, x, y - 1, m, n, sum); //left

        return sum;
    }
}
