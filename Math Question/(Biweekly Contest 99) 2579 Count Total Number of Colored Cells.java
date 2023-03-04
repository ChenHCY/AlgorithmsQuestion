/* Leetcode (Biweekly Contest 99)  2579. Count Total Number of Colored Cells

There exists an infinitely large two-dimensional grid of uncolored unit cells. You are given a positive integer n, 

indicating that you must do the following routine for n minutes:

At the first minute, color any arbitrary unit cell blue.
Every minute thereafter, color blue every uncolored cell that touches a blue cell.
Below is a pictorial representation of the state of the grid after minutes 1, 2, and 3.

Return the number of colored cells at the end of n minutes.

Example 1:

Input: n = 1
Output: 1
Explanation: After 1 minute, there is only 1 blue cell, so we return 1.


Example 2:

Input: n = 2
Output: 5
Explanation: After 2 minutes, there are 4 colored cells on the boundary and 1 in the center, so we return 5. 
 

Constraints:

1 <= n <= 105
*/

/*  找规律的题目

简单的数学规律计算，每一分钟的格子数 = 当前分钟的平方 + 前一分钟的平方

记住需要把int类型的n转化为long类型，避免因为int类型的大小界限出现计算错误
*/

class Solution {
    public long coloredCells(int n) {
      //记住需要把int类型的n转化为long类型，避免因为int类型的大小界限出现计算错误
        long num = Long.valueOf(n);
        long res = 0;
        
        long temp = num * num;
        long temp2 = (num - 1) * ( num - 1);
        
        res = temp + temp2; 
        return res;
    }
}
