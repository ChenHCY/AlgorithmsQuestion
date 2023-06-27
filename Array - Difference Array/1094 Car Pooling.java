/*Leetcode 1094. Car Pooling

There is a car with capacity empty seats. The vehicle only drives east (i.e., it cannot turn around and drive west).

You are given the integer capacity and an array trips where trips[i] = [numPassengersi, fromi, toi] indicates that the ith trip has 
numPassengersi passengers and the locations to pick them up and drop them off are fromi and toi respectively. 

The locations are given as the number of kilometers due east from the car's initial location.

Return true if it is possible to pick up and drop off all passengers for all the given trips, or false otherwise.

Example 1:

Input: trips = [[2,1,5],[3,3,7]], capacity = 4
Output: false

Example 2:

Input: trips = [[2,1,5],[3,3,7]], capacity = 5
Output: true
 
Constraints:

1 <= trips.length <= 1000
trips[i].length == 3
1 <= numPassengersi <= 100
0 <= fromi < toi <= 1000
1 <= capacity <= 10^5
*/
//Time: O(n)   Space: O(n)
class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        int[] diff = new int[1001]; // the diff array

        //creat the diff array 
        for(int[] trip : trips){
            int start = trip[1];
            int end = trip[2];
            int people = trip[0];

            diff[start] += people;
            diff[end] -= people; // [start,end)是左闭右开区间，因为end位置时乘客已经下车，不占用车的容量
        }

        //check whether can pick up and drop off all passengers for all the given trip
        int currCap = diff[0]; //每个位置上，汽车内部的人员数量
        for(int i = 1; i < diff.length; i++){
            //如果超过capacity限定，则代表不能 false
            if(currCap > capacity){
                return false;
            }
            currCap += diff[i];
        }
        return true;
    }
}
