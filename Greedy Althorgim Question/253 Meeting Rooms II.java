/* 253. Meeting Rooms II

Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return the minimum number of conference rooms required.

Example 1:

Input: intervals = [[0,30],[5,10],[15,20]]
Output: 2

Example 2:

Input: intervals = [[7,10],[2,4]]
Output: 1
 

Constraints:
1 <= intervals.length <= 10^4
0 <= starti < endi <= 10^6
*/

//Solution 1： PriorityQueue优先队列 + Arrays.sort() 排序
// 时间复杂度：O(nlogn)
// 空间复杂度：O(n)
// 题意：计算需要的会议室数量：优先队列 + 排序
class Solution {
    // 使用PriorityQueue对于interval的时间进行储存和排序
    public int minMeetingRooms(int[][] intervals) {
        //base case
        if(intervals == null || intervals.length == 0){
            return 0;
        }

        //按照起始时间的升序排序
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        
        //使用PriorityQueue对于遍历的时间进行储存和比较，看是否有时间冲突
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return a - b;
        });
        int count = 0; //会议室数量

        for(int[] time : intervals){
            //如果时间不冲突，则输出pq里面的会议时间
            //System.out.println(pq.peek() + " " + time[0]);
            while(!pq.isEmpty() && pq.peek() <= time[0]){
                pq.poll();
            }
            pq.offer(time[1]); //如果stack为空，或者时间冲突，则加入到stack中统计需要的会议室数量
            count = Math.max(count, pq.size());
        }
        return count;
    }
}
