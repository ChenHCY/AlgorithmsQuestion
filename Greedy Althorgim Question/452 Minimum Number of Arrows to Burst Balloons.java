/* Leetcode 452. Minimum Number of Arrows to Burst Balloons

There are some spherical balloons taped onto a flat wall that represents the XY-plane. The balloons are represented as a 2D integer 
array points where points[i] = [xstart, xend] denotes a balloon whose horizontal diameter stretches between xstart and xend. You do 
not know the exact y-coordinates of the balloons.

Arrows can be shot up directly vertically (in the positive y-direction) from different points along the x-axis. A balloon with xstart 
and xend is burst by an arrow shot at x if xstart <= x <= xend. There is no limit to the number of arrows that can be shot. A shot arrow 
keeps traveling up infinitely, bursting any balloons in its path.

Given the array points, return the minimum number of arrows that must be shot to burst all balloons.

Example 1:

Input: points = [[10,16],[2,8],[1,6],[7,12]]
Output: 2
Explanation: The balloons can be burst by 2 arrows:
- Shoot an arrow at x = 6, bursting the balloons [2,8] and [1,6].
- Shoot an arrow at x = 11, bursting the balloons [10,16] and [7,12].

Example 2:

Input: points = [[1,2],[3,4],[5,6],[7,8]]
Output: 4
Explanation: One arrow needs to be shot for each balloon for a total of 4 arrows.

Example 3:

Input: points = [[1,2],[2,3],[3,4],[4,5]]
Output: 2
Explanation: The balloons can be burst by 2 arrows:
- Shoot an arrow at x = 2, bursting the balloons [1,2] and [2,3].
- Shoot an arrow at x = 4, bursting the balloons [3,4] and [4,5].
 

Constraints:

1 <= points.length <= 10^5
points[i].length == 2
-2^31 <= xstart < xend <= 2^31 - 1
*/
//贪心算法： 每次更新右边界，来确定能被划分成多少 不重叠数组的数量 也就是 需要的箭枝
//Time: O(n)   Space: O(n)
class Solution {
    public int findMinArrowShots(int[][] points) {
        int res = 0;

      /*********************错误地方***************************************/
        //对于points[] 数组, 按照每个区间的起始点(左边界)进行升序排序
        // Arrays.sort(points, (a, b) -> {
        //     return a[0] - b[0];
        // });
        /*******************错误地方*******************************************/

      
        //对于points[] 数组, 按照每个区间的起始点(左边界)进行升序排序
        // 按照左边界排序 使用Integer内置比较方法，不会溢出
        Arrays.sort(points, (a, b) -> Integer.compare(a[0], b[0]));

        //统计不重叠数组的数量
        int count = 1; // 因为points数组不为空，所以一定存在一个不重叠的数组

        //开始遍历intervals所有区间，来找到不重叠数组的数量
        for(int i = 1; i < points.length; i++){
            //判断每个数组的最小值是否小于之前数组的最大值
            if(points[i][0] <= points[i - 1][1]){
                //更新这两个区间的右边界（最大值）=> 保留最小的
                //用于后续遍历 继续检查 是否重叠
                points[i][1] = Math.min(points[i][1], points[i-1][1]);
            } else{ //不重叠, 需要一支新的箭
                count++;
            }
        }

        System.out.println(count);

        return count;
    }
}
