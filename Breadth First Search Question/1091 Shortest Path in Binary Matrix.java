/* Leetcode 1091. Shortest Path in Binary Matrix

Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.

A clear path in a binary matrix is a path from the top-left cell (i.e., `(0, 0)`) to the bottom-right cell (i.e., `(n - 1, n - 1)`) such that:

    ·All the visited cells of the path are 0.
    ·All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).

The length of a clear path is the number of visited cells of this path.

Example 1:

Input: grid = [[0,1],[1,0]]
Output: 2

Example 2:

Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
Output: 4

Example 3:

Input: grid = [[1,0,0],[1,1,0],[1,1,0]]
Output: -1
 

Constraints:

n == grid.length
n == grid[i].length
1 <= n <= 100
grid[i][j] is 0 or 1
*/
//Time: O(n^2) 遍历了每一个格子的可能性，所以时间复杂度是 n * n = n^2    
//Space: O(n^2) 遍历了每一个格子，同时储存了每一个格子的最短clear path值在一个新的matrix里面，空间复杂度为 n * n = n^2
class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        //base case: if the start position is not 0, return -1, can not find the shortest clear path
        if(grid[0][0] == 1){
            return -1;
        }
        //row and column is same length
        int n = grid.length; 
        //遍历时，移动格子的坐标
        int[][] nextGird = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

     
        //因为此题是找到grid[][]中从左上角到右下角的最短路径， 所以可以看成一个有向无权图，每一个格子看成一个节点
        //每一个层级对应clear path的长度
        
        //used a matrix[][] to save the minium length of every node clear path  
        int[][] shortPath = new int[n][n]; //记录到达每个格子的最短clear path
        
      //因为要找到最短路径，所以给每个格子首先填写最大值 用于后续比较
        for(int[] matrixArr : shortPath){
            Arrays.fill(matrixArr, Integer.MAX_VALUE);
        }

        //BFS的解题模板，使用Queue进行层级分层,
        //Queue队列里面存储的是，grid[][] 对应的坐标
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0}); //放入起始点的坐标

        //起始点，(0, 0); 每个格子都是1个长度
        shortPath[0][0] = 1;

        //traver all the gird from matrix
        while(!queue.isEmpty()){
            //提取队列中的节点
            int[] curr = queue.poll();
            int currX = curr[0]; //当前节点的x坐标
            int currY = curr[1]; //当前节点的y坐标

            //如果到达终点
            if(currX == n - 1 && currY == n - 1){
                return shortPath[currX][currY];
            }

            //遍历下一层的格子，主要查找相邻值为0的格子
            for(int[] next : nextGird){
                int nextX = currX + next[0];
                int nextY = currY + next[1];
                //检查是否越界
                if(nextX < 0 || nextX >= n || nextY < 0 || nextY >= n){
                    continue; //如果越界，直接跳过后续步骤
                }

                //检查格子的值是否为0， 和 格子是否之前已经访问过
                //因为可以把这个matrix看成一个二叉树，如果之前格子在之前step层级被访问到，对应的层级一定小于新的step层级（当前层级+1）
                if(grid[nextX][nextY] == 1 || shortPath[nextX][nextY] <= shortPath[currX][currY] + 1){
                    continue;
                }

                //如果没有被访问过，且没有越界，格子值为0 ==> 统计新格子的step层级（clear path 长度）
                shortPath[nextX][nextY] = shortPath[currX][currY] + 1;
                //同时把新的点坐标放入队列，继续遍历
                queue.offer(new int[]{nextX, nextY});

            }
        }

        return -1;
    }
}
