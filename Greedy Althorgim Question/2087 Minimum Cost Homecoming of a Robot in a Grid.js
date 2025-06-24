/* 2087. Minimum Cost Homecoming of a Robot in a Grid

There is an m x n grid, where (0, 0) is the top-left cell and (m - 1, n - 1) is the bottom-right cell. You are given an integer array startPos 
where startPos = [startrow, startcol] indicates that initially, a robot is at the cell (startrow, startcol). You are also given an integer array 
homePos where homePos = [homerow, homecol] indicates that its home is at the cell (homerow, homecol).

The robot needs to go to its home. It can move one cell in four directions: left, right, up, or down, and it can not move outside the boundary. 
Every move incurs some cost. You are further given two 0-indexed integer arrays: rowCosts of length m and colCosts of length n.

If the robot moves up or down into a cell whose row is r, then this move costs rowCosts[r].
If the robot moves left or right into a cell whose column is c, then this move costs colCosts[c].
Return the minimum total cost for this robot to return home.

Example 1:
Input: startPos = [1, 0], homePos = [2, 3], rowCosts = [5, 4, 3], colCosts = [8, 2, 6, 7]
Output: 18
Explanation: One optimal path is that:
Starting from (1, 0)
-> It goes down to (2, 0). This move costs rowCosts[2] = 3.
-> It goes right to (2, 1). This move costs colCosts[1] = 2.
-> It goes right to (2, 2). This move costs colCosts[2] = 6.
-> It goes right to (2, 3). This move costs colCosts[3] = 7.
The total cost is 3 + 2 + 6 + 7 = 18

Example 2:
Input: startPos = [0, 0], homePos = [0, 0], rowCosts = [5], colCosts = [26]
Output: 0
Explanation: The robot is already at its home. Since no moves occur, the total cost is 0.
 

Constraints:
m == rowCosts.length
n == colCosts.length
1 <= m, n <= 105
0 <= rowCosts[r], colCosts[c] <= 104
startPos.length == 2
homePos.length == 2
0 <= startrow, homerow < m
0 <= startcol, homecol < n
*/

/**
 * @param {number[]} startPos
 * @param {number[]} homePos
 * @param {number[]} rowCosts
 * @param {number[]} colCosts
 * @return {number}
 */
// 时间复杂度是 O(m + n)，其中 m 和 n 是起点和终点在行和列上的差值，而不是整个 rowCosts 或 colCosts 的长度。
// 空间复杂度：O(1) 没有使用额外的数据结构
// greedy althorgim: 贪心算法，因为无论往上 还是 往下 or 往左 往右 移动的cost 都是一样的，所以最小的cost 就是直接走回家
var minCost = function(startPos, homePos, rowCosts, colCosts) {
    let res = 0;

    // 需要判断是向上还是向下
    let rowDir = startPos[0] <= homePos[0] ? 1 : -1;
    // 向左还是向右走
    let columnDir = startPos[1] <= homePos[1] ? 1 : -1;

    let rowIndex = startPos[0]; // the index in the row ==> calculate the cost
    while(rowIndex != homePos[0]){
        rowIndex += rowDir; // follow the direaction
        res += rowCosts[rowIndex]; // the cost in the row
    }

    let columnIndex = startPos[1];
    while(columnIndex != homePos[1]){
        columnIndex += columnDir; // follow the direction back home
        res += colCosts[columnIndex]; // used index to added the cost in the column
    }
    
    return res;
};
