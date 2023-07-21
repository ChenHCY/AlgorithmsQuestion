/* Leetcode 435. Non-overlapping Intervals

Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

Example 1:

Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.

Example 2:

Input: intervals = [[1,2],[1,2],[1,2]]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.

Example 3:

Input: intervals = [[1,2],[2,3]]
Output: 0
Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 

Constraints:

1 <= intervals.length <= 105
intervals[i].length == 2
-5 * 10^4 <= starti < endi <= 5 * 10^4
*/

//贪心算法：不停的更新一个区间的右边界限，找到不重叠的数组 =》 最后得到需要删除的区间数量
//Time: O(n)  Space: O(n)
class Solution {
    // 我们要找到删除几个区间，可以让剩余整个数组的区间都是不重叠的
    // 需要删除的区间 = 总区间的个数 - 不重叠的区间个数
    public int eraseOverlapIntervals(int[][] intervals) {
        //对于区间进行按照起始点a[0]进行升序排序，
        Arrays.sort(intervals, (a, b) -> {
            return a[0] - b[0];
        });
        
        //统计不重叠数组的数量
        int count = 1; // 因为intervals数组不为空，所以一定存在一个不重叠的数组
        
        //开始遍历intervals所有区间，来找到不重叠数组的数量
        for(int i = 1; i < intervals.length; i++){
            //判断每个数组的最小值是否小于之前数组的最大值
            if(intervals[i][0] < intervals[i - 1][1]){
                //更新这两个区间的右边界（最大值）=> 保留最小的
                //用于后续遍历 继续检查 是否重叠
                intervals[i][1] = Math.min(intervals[i - 1][1], intervals[i][1]);
            } else{ //和之前区间 不重叠
                count++;
            }
        }

        //需要删除的区间 = 总区间的个数 - 不重叠的区间个数
        return intervals.length - count;
    }
}
