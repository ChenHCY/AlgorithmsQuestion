/* Leetcode 1109. Corporate Flight Bookings
  
There are n flights that are labeled from 1 to n.

You are given an array of flight bookings bookings, where bookings[i] = [firsti, lasti, seatsi] represents a booking for 
flights firsti through lasti (inclusive) with seatsi seats reserved for each flight in the range.

Return an array answer of length n, where answer[i] is the total number of seats reserved for flight i.

Example 1:

Input: bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
Output: [10,55,45,25,25]
Explanation:
Flight labels:        1   2   3   4   5
Booking 1 reserved:  10  10
Booking 2 reserved:      20  20
Booking 3 reserved:      25  25  25  25
Total seats:         10  55  45  25  25
Hence, answer = [10,55,45,25,25]

Example 2:

Input: bookings = [[1,2,10],[2,2,15]], n = 2
Output: [10,25]
Explanation:
Flight labels:        1   2
Booking 1 reserved:  10  10
Booking 2 reserved:      15
Total seats:         10  25
Hence, answer = [10,25]

Constraints:

1 <= n <= 2 * 10^4
1 <= bookings.length <= 2 * 10^4
bookings[i].length == 3
1 <= firsti <= lasti <= n
1 <= seatsi <= 10^4
*/

class Solution {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] res = new int[n];
        int[] diff = new int[n];

        //形成一个差分数组，来对于间断区间的值 进行添加和计算
        for(int[] list : bookings){
            //因为飞机的架次是从 1 - 5， 数组的起始点是 0，所以架次存入数组时 需要 minus 1
            int sFlight = list[0] - 1; //起始飞机
            int eFlight = list[1] - 1; //终点飞机
            int seats = list[2]; //需要保留的座位

            diff[sFlight] += seats; //从起始飞机开始，保留已经预定的座位数量
            if(eFlight + 1 < n){ //到终点飞机的架次结束
                diff[eFlight + 1] -= seats;
            }
        }

        res[0] = diff[0];

        //复原差分数组 返回 原始数组 => 对于差分数组求前缀和
        for(int i = 1; i < n; i++){
            res[i] = res[i - 1] + diff[i];
        }

        return res;
    }
}
