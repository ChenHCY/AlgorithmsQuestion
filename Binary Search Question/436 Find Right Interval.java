/* 436. Find Right Interval

You are given an array of intervals, where intervals[i] = [starti, endi] and each starti is unique.

The right interval for an interval i is an interval j such that startj >= endi and startj is minimized. Note that i may equal j.

Return an array of right interval indices for each interval i. If no right interval exists for interval i, then put -1 at index i.

Example 1:
Input: intervals = [[1,2]]
Output: [-1]
Explanation: There is only one interval in the collection, so it outputs -1.

Example 2:
Input: intervals = [[3,4],[2,3],[1,2]]
Output: [-1,0,1]
Explanation: There is no right interval for [3,4].
The right interval for [2,3] is [3,4] since start0 = 3 is the smallest start that is >= end1 = 3.
The right interval for [1,2] is [2,3] since start1 = 2 is the smallest start that is >= end2 = 2.

Example 3:
Input: intervals = [[1,4],[2,3],[3,4]]
Output: [-1,2,-1]
Explanation: There is no right interval for [1,4] and [3,4].
The right interval for [2,3] is [3,4] since start2 = 3 is the smallest start that is >= end1 = 3.
 

Constraints:
1 <= intervals.length <= 2 * 10^4
intervals[i].length == 2
-10^6 <= starti <= endi <= 10^6
The start point of each interval is unique.
*/

// 题意：找到每一个在Interval的子range中，右边的第一个相邻的range代表的index位置
// 思路：排序 + 二分法查找，首先创建一个新的二维数组，来储存每个range的开头值，和 其对应的index位置
// ==> 然后提取每一个range, 进行二分查找，比较 intervals[j][0] > intervals[i][1] 的range，把index加入res
// Time: O(n * logn)   Space: O(n)
class Solution {
    public int[] findRightInterval(int[][] intervals) {
        int n = intervals.length;
        int[][] startIndex = new int[n][2];

        //把每个range的开始值和在intervals中index位置 存入新的二维数组中
        for(int i = 0; i < n; i++){
            startIndex[i][0] = intervals[i][0];
            startIndex[i][1] = i;
        }
        Arrays.sort(startIndex, (a, b) -> a[0] - b[0]); //基于value进行升序排序 => O(logn)

        int[] res = new int[n];
        //main taverse all the element from intervals, and find the right interval
        //for-loop: get every range: O(n)  while-loop: Binary search find O(logn) ==> Total Time: O(n * logn)
        for(int i = 0; i < n; i++){
            int left = 0;
            int right = n - 1;
            
            while(left < right){
                int mid = left + (right - left) / 2; // 二分查找

                //如果当前range的结束值 小于 mid指针位置对应的开始值，==> 符合要求，但我们要找最小的，所以缩小右指针
                if(intervals[i][1] <= startIndex[mid][0]){
                    right = mid; //mid不能删除
                } else { //如果不符合要求，移动左指针
                    left = mid + 1;
                }
            }
        
            //检查左指针对应的是不是 开始值最小(最相邻)的右边range，是的存入结果，不是存入-1
            res[i] = startIndex[left][0] >= intervals[i][1] ? startIndex[left][1] : -1; 
        }

        return res;
    }
}
