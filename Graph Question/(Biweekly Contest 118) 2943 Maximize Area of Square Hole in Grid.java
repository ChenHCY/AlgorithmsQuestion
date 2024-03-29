/* Leetcode 2943. Maximize Area of Square Hole in Grid

There is a grid with n + 2 horizontal bars and m + 2 vertical bars, and initially containing 1 x 1 unit cells.

The bars are 1-indexed.

You are given the two integers, n and m.

You are also given two integer arrays: hBars and vBars.

    · hBars contains distinct horizontal bars in the range [2, n + 1].
    · vBars contains distinct vertical bars in the range [2, m + 1].

You are allowed to remove bars that satisfy any of the following conditions:

    · If it is a horizontal bar, it must correspond to a value in hBars.
    · If it is a vertical bar, it must correspond to a value in vBars.

Return an integer denoting the maximum area of a square-shaped hole in the grid after removing some bars (possibly none).

Example 1:
Input: n = 2, m = 1, hBars = [2,3], vBars = [2]
Output: 4
Explanation: The left image shows the initial grid formed by the bars.
The horizontal bars are in the range [1,4], and the vertical bars are in the range [1,3].
It is allowed to remove horizontal bars [2,3] and the vertical bar [2].
One way to get the maximum square-shaped hole is by removing horizontal bar 2 and vertical bar 2.
The resulting grid is shown on the right.
The hole has an area of 4.
It can be shown that it is not possible to get a square hole with an area more than 4.
Hence, the answer is 4.

Example 2:
Input: n = 1, m = 1, hBars = [2], vBars = [2]
Output: 4
Explanation: The left image shows the initial grid formed by the bars.
The horizontal bars are in the range [1,3], and the vertical bars are in the range [1,3].
It is allowed to remove the horizontal bar [2] and the vertical bar [2].
To get the maximum square-shaped hole, we remove horizontal bar 2 and vertical bar 2.
The resulting grid is shown on the right.
The hole has an area of 4.
Hence, the answer is 4, and it is the maximum possible.

Example 3:
Input: n = 2, m = 3, hBars = [2,3], vBars = [2,3,4]
Output: 9
Explanation: The left image shows the initial grid formed by the bars.
The horizontal bars are in the range [1,4], and the vertical bars are in the range [1,5].
It is allowed to remove horizontal bars [2,3] and vertical bars [2,3,4].
One way to get the maximum square-shaped hole is by removing horizontal bars 2 and 3, and vertical bars 3 and 4.
The resulting grid is shown on the right.
The hole has an area of 9.
It can be shown that it is not possible to get a square hole with an area more than 9.
Hence, the answer is 9.
 

Constraints:
1 <= n <= 10^9
1 <= m <= 10^9
1 <= hBars.length <= 100
2 <= hBars[i] <= n + 1
1 <= vBars.length <= 100
2 <= vBars[i] <= m + 1
All values in hBars are distinct.
All values in vBars are distinct.
*/

/* 移除划分线的过程：==》 切蛋糕的反向：
    · 如果不做任何移除，那么最长长度为 1。
    · 如果移除一条线，那么最长长度为 2。
    · 如果移除两条编号相邻的线，那么最长长度为 3。
    · 如果移除三条编号连续的线（例如 2,3, 4），那么最长长度为 4。
·依此类推：
    · 所以把数组排序后，求出最长连续递增长度即可。
*/

// Time: O(nlog⁡n+mlog⁡m) 其中 n 为 hBars  的长度，  m  为 vBars 的长度。
// Space：O(1) 忽略排序的栈开销。
class Solution {
    // 因为是要找到最大面积的正方形，所以长宽需要保持一样
    // 我们可以移除hBars vBars的所有边，来保证面积最大化，所以找到两个数组连续递增的最长长度
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        int len = Math.min(maxLen(hBars), maxLen(vBars));
        return len * len;
    }

    public static int maxLen(int[] nums){
        Arrays.sort(nums);
        int n = nums.length;
        int maxCount = 1;
        int currCount = 1;

        // 找到连续相邻递增的最长长度
        for(int i = 1; i < n; i++){
            if(nums[i] - 1 == nums[i - 1]){
                currCount++;
                maxCount = Math.max(maxCount, currCount);
            } else{
                currCount = 1;
            }
        }

        return maxCount + 1;
    } 
}
