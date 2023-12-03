/* 1465. Maximum Area of a Piece of Cake After Horizontal and Vertical Cuts

You are given a rectangular cake of size h x w and two arrays of integers horizontalCuts and verticalCuts where:

  · horizontalCuts[i] is the distance from the top of the rectangular cake to the ith horizontal cut and similarly, and
  · verticalCuts[j] is the distance from the left of the rectangular cake to the jth vertical cut.

Return the maximum area of a piece of cake after you cut at each horizontal and vertical position provided in the arrays horizontalCuts 
and verticalCuts. Since the answer can be a large number, return this modulo 109 + 7.

Example 1:
Input: h = 5, w = 4, horizontalCuts = [1,2,4], verticalCuts = [1,3]
Output: 4 
Explanation: The figure above represents the given rectangular cake. Red lines are the horizontal and vertical cuts. After you cut the cake, the green piece of cake has the maximum area.

Example 2:
Input: h = 5, w = 4, horizontalCuts = [3,1], verticalCuts = [1]
Output: 6
Explanation: The figure above represents the given rectangular cake. Red lines are the horizontal and vertical cuts. After you cut the cake, the green and yellow pieces of cake have the maximum area.

Example 3:
Input: h = 5, w = 4, horizontalCuts = [3], verticalCuts = [3]
Output: 9
 

Constraints:
2 <= h, w <= 10^9
1 <= horizontalCuts.length <= min(h - 1, 10^5)
1 <= verticalCuts.length <= min(w - 1, 10^5)
1 <= horizontalCuts[i] < h
1 <= verticalCuts[i] < w
All the elements in horizontalCuts are distinct.
All the elements in verticalCuts are distinct.
*/

// 时间复杂度：O(nlog⁡n+mlog⁡m) 其中 n  为 horizontalCuts 的长度，m 为 verticalCuts 的长度。
// 空间复杂度：O(1)
class Solution {
    // 题意是找到根据horizontalCuts 和 verticalCuts 切割后，蛋糕最大的一块的面积
    // 所以我们需要 找到根据horizontalCuts 和 verticalCuts 切割后，高度 和 宽度 可能存在的最大间隔 ==》 长度
    // 然后举行面积公式：长 * 宽
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        //首先找到 h w 最长的的间隔
        int maxH = getMaxLen(h, horizontalCuts);
        int maxW = getMaxLen(w, verticalCuts);

        //计算面积
        return (int)((long) maxH * maxW % 1000000007);
    }

    // size 表示 蛋糕的长度 和 宽度 / cuts 表示切蛋糕的线
    public static int getMaxLen(int size, int[] cuts){
        Arrays.sort(cuts); // 因为我们要找到最大的间隔，来保证切割之后的最大面积
        // 所以需要排序

        //首先比较第一条线到最左边，和最后一条线到最右边，哪一块蛋糕更大
        int maxL = Math.max(cuts[0] - 0, size - cuts[cuts.length - 1]);

        //然后for-loop比较中间部分的切割之后的区域长度，找到最长的
        for(int i = 1; i < cuts.length; i++){
            maxL = Math.max(maxL, cuts[i] - cuts[i - 1]);
        }

        return maxL;
    }
}
