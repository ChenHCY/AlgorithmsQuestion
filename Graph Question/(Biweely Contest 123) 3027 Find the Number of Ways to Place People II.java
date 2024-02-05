/* 3027. Find the Number of Ways to Place People II

You are given a 2D array points of size n x 2 representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].

We define the right direction as positive x-axis (increasing x-coordinate) and the left direction as negative x-axis (decreasing x-coordinate). Similarly, 
we define the up direction as positive y-axis (increasing y-coordinate) and the down direction as negative y-axis (decreasing y-coordinate)

You have to place n people, including Chisato and Takina, at these points such that there is exactly one person at every point. Chisato wants to be alone 
with Takina, so Chisato will build a rectangular fence with Chisato's position as the upper left corner and Takina's position as the lower right corner of the 
fence (Note that the fence might not enclose any area, i.e. it can be a line). If any person other than Chisato and Takina is either inside the fence 
or on the fence, Chisato will be sad.

Return the number of pairs of points where you can place Chisato and Takina, such that Chisato does not become sad on building the fence.

Note that Chisato can only build a fence with Chisato's position as the upper left corner, and Takina's position as the lower right corner. 

For example, Chisato cannot build either of the fences in the picture below with four corners (1, 1), (1, 3), (3, 1), and (3, 3), because:

    · With Chisato at (3, 3) and Takina at (1, 1), Chisato's position is not the upper left corner and Takina's position is not the lower right corner of the fence.
    · With Chisato at (1, 3) and Takina at (1, 1), Takina's position is not the lower right corner of the fence.

Example 1:
Input: points = [[1,1],[2,2],[3,3]]
Output: 0
Explanation: There is no way to place Chisato and Takina such that Chisato can build a fence with Chisato's position 
as the upper left corner and Takina's position as the lower right corner. Hence we return 0. 

Example 2:
Input: points = [[6,2],[4,4],[2,6]]
Output: 2
Explanation: There are two ways to place Chisato and Takina such that Chisato will not be sad:
- Place Chisato at (4, 4) and Takina at (6, 2).
- Place Chisato at (2, 6) and Takina at (4, 4).
You cannot place Chisato at (2, 6) and Takina at (6, 2) because the person at (4, 4) will be inside the fence.

Example 3:
Input: points = [[3,1],[1,3],[1,1]]
Output: 2
Explanation: There are two ways to place Chisato and Takina such that Chisato will not be sad:
- Place Chisato at (1, 1) and Takina at (3, 1).
- Place Chisato at (1, 3) and Takina at (1, 1).
You cannot place Chisato at (1, 3) and Takina at (3, 1) because the person at (1, 1) will be on the fence.
Note that it does not matter if the fence encloses any area, the first and second fences in the image are valid.
 

Constraints:
2 <= n <= 1000
points[i].length == 2
-109 <= points[i][0], points[i][1] <= 109
All points[i] are distinct.
*/
// Time: (O^2)   SPACE: O(1)
// Chisato's position as the upper left corner
// Takina's position as the lower right corner of the fence
class Solution {
    // 所以我们只需要先确定左上角Chisato的位置，然后枚举Takina的位置
    // 枚举遍历检查每个点的y坐标，这个矩形区域是否有其他的人，如果没有+1
    public int numberOfPairs(int[][] points) {
        // 因为我们首先要确认左上角Chisato的位置
        // 所以将points 根据从x坐标的小到大进行排序，如果x坐标一样，按照y坐标的从大到小排序
        Arrays.sort(points, (a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);
        int res = 0;
        for(int i = 0; i < points.length; i++){
            int chisY = points[i][1]; //左上角Chisato的位置 Y
            int rangeMaxY = Integer.MIN_VALUE;// 整个矩形区域内的最大Y值

            //枚举后面的每一个坐标作为Takina的位置，检查Y坐标是否满足要求
            for(int j = i + 1; j < points.length; j++){
                int taY = points[j][1]; //右下角Takina的位置
                // 检查这个区域有没有其他店，如果没有，+1
                if(taY <= chisY && taY > rangeMaxY){
                    rangeMaxY = taY; //更新区域的最大Y值
                    res += 1; 
                }
            }
        }

        return res;
    }
}
