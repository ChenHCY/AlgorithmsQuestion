/* Leetcode 1232. Check If It Is a Straight Line
You are given an array coordinates, coordinates[i] = [x, y], where [x, y] represents the coordinate of a point. 

Check if these points make a straight line in the XY plane.

Example 1:
Input: coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
Output: true

Example 2:
Input: coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
Output: false
 

Constraints:

2 <= coordinates.length <= 1000
coordinates[i].length == 2
-10^4 <= coordinates[i][0], coordinates[i][1] <= 10^4
coordinates contains no duplicate point.
*/

class Solution {
    public boolean checkStraightLine(int[][] coordinates) {
        int n = coordinates.length;
        int x0 = coordinates[0][0]; //first point x
        int y0 = coordinates[0][1]; //first point y
        int x1 = coordinates[1][0]; //Second point x
        int y1 = coordinates[1][1]; //Second point y
        
        for(int i = 2; i < n; i++){
            int currX = coordinates[i][0]; 
            int currY = coordinates[i][1];
          
            //应该使用乘法来计算差异，不能使用除法
            //因为当使用除法计算斜率时，如果两个点在垂直的情况时，使用除法会产生计算误差
            if((currY - y0) * (currX - x1) != (currY - y1) * (currX - x0)){
                return false;
            }
        }

        return true;
    }
}
