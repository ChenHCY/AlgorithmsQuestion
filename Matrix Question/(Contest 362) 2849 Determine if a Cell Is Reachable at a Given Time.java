/*Leetcode (Contest 362)  2849. Determine if a Cell Is Reachable at a Given Time

You are given four integers sx, sy, fx, fy, and a non-negative integer t.

In an infinite 2D grid, you start at the cell (sx, sy). Each second, you must move to any of its adjacent cells.

Return true if you can reach cell (fx, fy) after exactly t seconds, or false otherwise.

A cell's adjacent cells are the 8 cells around it that share at least one corner with it. You can visit the same cell several times.

Example 1:
Input: sx = 2, sy = 4, fx = 7, fy = 7, t = 6
Output: true
Explanation: Starting at cell (2, 4), we can reach cell (7, 7) in exactly 6 seconds by going through the cells depicted in the picture above. 

Example 2:
Input: sx = 3, sy = 1, fx = 7, fy = 3, t = 3
Output: false
Explanation: Starting at cell (3, 1), it takes at least 4 seconds to reach cell (7, 3) by going through the cells depicted in the picture above. Hence, we cannot reach cell (7, 3) at the third second.
 

Constraints:
1 <= sx, sy, fx, fy <= 10^9
0 <= t <= 10^9
*/
//Time: O(1)  Space: O(1)
class Solution {
    //sx sy是起始点， fx fy是结束点，验证t秒内能否到达，一秒只能移动一步
    //先找出 sx-fx 和 sy-fy中较小的值 ==> 这表示斜向走的步数
    // 然后计算这两个差异，表示斜向走完之后 还距离 （fx, fy）的距离 和 需要的步数
    public boolean isReachableAtTime(int sx, int sy, int fx, int fy, int t) {
        int diffX = Math.abs(sx - fx);
        int diffY = Math.abs(sy - fy);

        //当起始点和结束点一样，并且需要移动1秒时 ==> 表示1秒之后, 不可能位于结束点 
        if(diffX == 0 && diffY == 0 && t == 1){
            return false;
        }

        int digonal = Math.min(diffX, diffY); //沿着对角线方向(digonal direction)前进
        int straight = Math.abs(diffX - diffY); //表示斜向走完之后 还距离 （fx, fy）的距离 和 需要的步数

        //如果需要的时间，小于t, 表示是可以到达结束点的
        if(digonal + straight <= t){
            return true;
        }

        return false; //不能到达结束点
    }
}
