/* Leetcode 57. Insert Interval
You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] 
represent the start and the end of the ith interval and intervals is sorted in ascending order 
by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval.

Insert newInterval into intervals such that intervals is still sorted in ascending order by 
starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).

Return intervals after the insertion.

Example 1:
Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]

Example 2:
Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 
Constraints:
0 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 105
intervals is sorted by starti in ascending order.
newInterval.length == 2
0 <= start <= end <= 105
*/
//Time: O(n)  Space: O(N) 
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        //declare the res LinkedList
        //arraylist used for search && LinkedList used for add and delete
        LinkedList<int[]> res = new LinkedList<>();
        
        //traverse all the int[] array from intervals[][]
        for(int[] i : intervals){
            //have 3 solution
            //1. intervals[1] < newInterval[0] or newInterval finished add it
            if(newInterval == null || i[1] < newInterval[0] ){
                res.add(i);
            } else if(newInterval[1] < i[0]){ //2. newInterval[1] < intervals[0] 
                res.add(newInterval);
                res.add(i);
                newInterval = null;
            } else{ // 3.intervals[1] > newInterval[0]
                newInterval[0] = Math.min(i[0], newInterval[0]);
                newInterval[1] = Math.max(i[1], newInterval[1]);
            }
        }
        
        //the last solution: if all the i[endi] from intervals[][] array is less than newInterval[starti]
        if(newInterval != null){
            res.add(newInterval);
        }
        
        return res.toArray(new int[res.size()][]);
    }
}
