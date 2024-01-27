/* Leetcode 149. Max Points on a Line

Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane, return the maximum number of points that lie on the same straight line.

Example 1:
Input: points = [[1,1],[2,2],[3,3]]
Output: 3

Example 2:
Input: points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
Output: 4
 
Constraints:
1 <= points.length <= 300
points[i].length == 2
-10^4 <= xi, yi <= 10^4
All the points are unique.
*/

// Solution 1：暴力解法 ==> Time: O(n^3) Space: O(1)
// 遍历points整个数组，从左右往右，每次取两个点，确定斜率
// 然后统计总共有多少个点和他们斜率一样，表示在这条直线上
class Solution {
    public int maxPoints(int[][] points) {
        int n = points.length;
        int res = 1;

        for(int i = 0; i < n; i++){
            int[] x = points[i];
            for(int j = i + 1; j < n; j++){
                int cnt = 2;
                int[] y = points[j];
                // 继续枚举其他点 (i,j) 并统计有多少点在该线上, 
                // 起始 cnt = 2 初始化就有两个点在线上
                for(int k = j + 1; k < n; k++){
                    int[] p = points[k];
                    //开始比较斜率: 尽量不要使用除法，因为会存在小数误差
                    int s1 = (y[1] - x[1]) * (p[0] - y[0]);
                    int s2 = (p[1] - y[1]) * (y[0] - x[0]);
                    if(s1 == s2){
                        cnt += 1;
                    }
                }
                res = Math.max(res, cnt);
            }
        }
        return res;
    }
}

// Solution 2：使用HashMap对于斜率储存进行一个优化 ==> Time: O(n^3) Space: O(1)
