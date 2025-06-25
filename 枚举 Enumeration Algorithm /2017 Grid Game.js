/* 2017. Grid Game

You are given a 0-indexed 2D array grid of size 2 x n, where grid[r][c] represents the number of points at position (r, c) on the matrix. Two robots are playing a game on this matrix.

Both robots initially start at (0, 0) and want to reach (1, n-1). Each robot may only move to the right ((r, c) to (r, c + 1)) or down ((r, c) to (r + 1, c)).

At the start of the game, the first robot moves from (0, 0) to (1, n-1), collecting all the points from the cells on its path. For all cells (r, c) traversed on the path, 
grid[r][c] is set to 0. Then, the second robot moves from (0, 0) to (1, n-1), collecting the points on its path. Note that their paths may intersect with one another.

The first robot wants to minimize the number of points collected by the second robot. In contrast, the second robot wants to maximize the number of points it collects. 
If both robots play optimally, return the number of points collected by the second robot.

Example 1:
Input: grid = [[2,5,4],[1,5,1]]
Output: 4
Explanation: The optimal path taken by the first robot is shown in red, and the optimal path taken by the second robot is shown in blue.
The cells visited by the first robot are set to 0.
The second robot will collect 0 + 0 + 4 + 0 = 4 points.

Example 2:
Input: grid = [[3,3,1],[8,5,2]]
Output: 4
Explanation: The optimal path taken by the first robot is shown in red, and the optimal path taken by the second robot is shown in blue.
The cells visited by the first robot are set to 0.
The second robot will collect 0 + 3 + 1 + 0 = 4 points.

Example 3:
Input: grid = [[1,3,1,15],[1,3,3,1]]
Output: 7
Explanation: The optimal path taken by the first robot is shown in red, and the optimal path taken by the second robot is shown in blue.
The cells visited by the first robot are set to 0.
The second robot will collect 0 + 1 + 3 + 3 + 0 = 7 points.
 

Constraints:

grid.length == 2
n == grid[r].length
1 <= n <= 5 * 104
1 <= grid[r][c] <= 105
*/

/**
 * @param {number[][]} grid
 * @return {number}
 */
// 枚举：枚举 第一个机器人 在 每一个 index 位置 转弯   ===> 时间复杂度是 o（n) 空间复杂度 O(1)
// 然后比较 得到第二个机器人能取的最大值， 第一行的后半部分  or 第二行的前半部分
// 因为要通过第一个机器人的路径，让第二个机器人得分最少
// 所以 ==》 最后要输出 第二个机器人每一个可能的值（最大值）的最小值 ==〉 min-mx 问题
var gridGame = function(grid) {
    let res = Number.MAX_SAFE_INTEGER; // JS里面最大值的写法
    let top = grid[0].reduce((a, b) => a + b, 0); // 计算第一行整体的值
    let bottom = 0; 
    const n = grid[0].length;

    // 枚举，assume robotA 在第一个格子转，则 robotB 能取的到值 就是 top = top - grid[0][i], bottom = 0 中的最大值
    for(let i = 0; i < n; i++){
        top -= grid[0][i]; // 因为 robotA 把这个格子变成了 0
        res = Math.min(res, Math.max(top, bottom)); //  最后要输出 第二个机器人每一个可能的值（最大值）的最小值 ==〉 min-mx 问题
        bottom += grid[1][i]; // 移动下一个index, 所以robotB在第二行 可以取到这个值了
    }

    return res;
};
