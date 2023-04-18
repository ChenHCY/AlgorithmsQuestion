/* Leetcode 329. Longest Increasing Path in a Matrix

Given an m x n integers matrix, return the length of the longest increasing path in matrix.

From each cell, you can either move in four directions: left, right, up, or down. 

You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).

Example 1:
Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
Output: 4
Explanation: The longest increasing path is [1, 2, 6, 9].

Example 2:
Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
Output: 4
Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.

Example 3:

Input: matrix = [[1]]
Output: 1
*/

/* 思路：DFS + DP memorization 

此题事寻找在一个matrix中，最长的递增路径长度

所以我们可以使用DFS 然后从每一个单元格开始向四周遍历扩散，如果发现格子值是大于当前单元格的格子值的时候，则记录 + 1

==》这样，我们就可找到从该单元格开始的最长递增路径。

==》 对每个单元格分别进行深度优先搜索之后，再进行比较，就可得到矩阵中的最长递增路径的长度。

但因为单纯的使用DFS，时间复杂度过于大 ==》通过DP memorization 记忆化搜索进行优化

DP memorization 记忆化搜索 储存 已经访问过的单元格和这些单元格的最长递增路径长度，

==》 这样可以避免同一个单元格会被访问多次，每次访问都要重新计算。

*/

//Time: O(m*n)  Space: O(m*n)
class Solution {
    public int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int res = 0;

        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }

        //DP memorization ==> DP memorization 记忆化搜索 储存 已经访问过的单元格和这些单元格的最长递增路径长度，
        int[][] dpMemo = new int[m][n];

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                //每个格子都进行一次DFS查找，然后找到每个格子最长的递增路径长度 ==》进行比较 得到matrix最长的递增路径长度
                res = Math.max(res, dfs(matrix, i, j, dpMemo));        
            }
        }

        return res;
    }

    //the dfs function to count the lengthe of Increasing Path in matrix
    public int dfs(int[][] matrix, int currX, int currY, int[][] dpMemo){
        //the exit condition ==》 如果DP[][]  array中已经存在这个节点
        //表示我们已经访问过的这个节点，直接把这个节点的递增路径长度输出加入
      // ==》 DP memorization 记忆化搜索 储存 已经访问过的单元格和这些单元格的最长递增路径长度，
        if(dpMemo[currX][currY] != 0){
            return dpMemo[currX][currY];
        }
        
        dpMemo[currX][currY]++; //因为开头的当前格子也算一个

        //main for-loop, 从每一个curr格子向四周扩散，然后统计最长的递增路径值，=> 存入到 DP array中
        for(int[] dir : dirs){
            //路径格子向四周扩散
            int newX = currX + dir[0];
            int newY = currY + dir[1];

            //如果这个节点是在matrix的范围
            if(newX >= 0 && newX < matrix.length && newY >= 0 && newY < matrix[0].length && matrix[currX][currY] < matrix[newX][newY]){
                //DFS向四周发展，然后如果格子的值是大于当前的格子值 ==> 计入路径长度 + 1
                //最后记录当前格子的最长递增路径长度
                dpMemo[currX][currY] = Math.max(dpMemo[currX][currY], dfs(matrix, newX, newY, dpMemo) + 1);
            }
        }
        return dpMemo[currX][currY]; //返回这个格子的路径长度
    }
