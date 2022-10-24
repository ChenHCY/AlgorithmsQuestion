/* Leetcode 56. Merge Intervals
Given an array of intervals where intervals[i] = [starti, endi], 
merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

Example 1:
Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].

Example 2:
Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 
Constraints:
1 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 104
*/
//Time: O(nlogn)   Space: O(logn)

class Solution {
    public int[][] merge(int[][] intervals) {
        if(intervals.length <= 1){
            return intervals;
        }
        //the arraylist res
        LinkedList<int[]> res = new LinkedList<>();
        //Sort the depends on the first element of every[]
        //time: O(logn)
        Arrays.sort(intervals, (a,b) -> Integer.compare(a[0], b[0]));
        
        //travser every int[] array from intervals  ==> time: O(n)
        //int[0][1] ==> 0 is means first, 1 is means last
        for(int[] interval : intervals){
            //res is empty || or not Overlapping intervals ==> res.getLast()[1] < interval[0]
            if(res.isEmpty() || res.getLast()[1] < interval[0]){
                res.add(interval); //add the new intervals into LinkedList
            } else{ //overlapping the intervals ==> move the end if needed
                res.getLast()[1] = Math.max(res.getLast()[1], interval[1]);
            }
        }
        
        //change to be int[][] array format 
        return res.toArray(new int[res.size()][]);
    }
}
