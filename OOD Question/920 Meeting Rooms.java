/*Lintcode 920 · Meeting Rooms

Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.


Example1

Input: intervals = [(0,30),(5,10),(15,20)]
Output: false
Explanation: 
(0,30), (5,10) and (0,30),(15,20) will conflict

Example2

Input: intervals = [(5,8),(9,15)]
Output: true
Explanation: 
Two times will not conflict 

*/

/**
 * Definition of Interval:
 * public class Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */

public class Solution {
    /**
     * @param intervals: an array of meeting time intervals
     * @return: if a person could attend all meetings
     */
    public boolean canAttendMeetings(List<Interval> intervals) {
        // Write your code here
        //使用Collections.sort方法对于intervals，基于 起始时间 进行排序
        //在Java中，该Comparator接口用于自定义对象比较。
        Collections.sort(intervals, new Comparator<Interval>() {
            //compare是一个比较器，通常用于比较两个对象并确定它们的相对顺序
            public int compare(Interval i1, Interval i2){
                return i1.start - i2.start; //基于 起始时间 进行排序
            } 
        });

        //这样可以把intervals中所有的element 基于 起始时间进行排序
        //然后只用检查是否有结束时间 超过了 下一个起始时间的 存在
        for(int i = 0; i < intervals.size() - 1; i++){
            if(intervals.get(i).end > intervals.get(i+1).start){
                return false;
            }
        }

        return true;
    }
}
