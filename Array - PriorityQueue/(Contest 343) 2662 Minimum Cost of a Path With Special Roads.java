/*Leetcode (Contest 343) 2662 Minimum Cost of a Path With Special Roads

You are given an array start where start = [startX, startY] represents your initial position (startX, startY) in a 2D space. 

You are also given the array target where target = [targetX, targetY] represents your target position (targetX, targetY).

The cost of going from a position (x1, y1) to any other position in the space (x2, y2) is |x2 - x1| + |y2 - y1|.

There are also some special roads. You are given a 2D array specialRoads where specialRoads[i] = [x1i, y1i, x2i, y2i, costi] 
indicates that the ith special road can take you from (x1i, y1i) to (x2i, y2i) with a cost equal to costi. You can use each special road any number of times.

Return the minimum cost required to go from (startX, startY) to (targetX, targetY). 

Example 1:

Input: start = [1,1], target = [4,5], specialRoads = [[1,2,3,3,2],[3,4,4,5,1]]
Output: 5
Explanation: The optimal path from (1,1) to (4,5) is the following:
- (1,1) -> (1,2). This move has a cost of |1 - 1| + |2 - 1| = 1.
- (1,2) -> (3,3). This move uses the first special edge, the cost is 2.
- (3,3) -> (3,4). This move has a cost of |3 - 3| + |4 - 3| = 1.
- (3,4) -> (4,5). This move uses the second special edge, the cost is 1.
So the total cost is 1 + 2 + 1 + 1 = 5.
It can be shown that we cannot achieve a smaller total cost than 5.

Example 2:

Input: start = [3,2], target = [5,7], specialRoads = [[3,2,3,4,4],[3,3,5,5,5],[3,4,5,6,6]]
Output: 7
Explanation: It is optimal to not use any special edges and go directly from the starting to the ending position with a cost |5 - 3| + |7 - 2| = 7.
 

Constraints:

start.length == target.length == 2
1 <= startX <= targetX <= 10^5
1 <= startY <= targetY <= 10^5
1 <= specialRoads.length <= 200
specialRoads[i].length == 5
startX <= x1i, x2i <= targetX
startY <= y1i, y2i <= targetY
1 <= costi <= 10^5
*/

/* 此题是找到从start[]点 到 target点中最短的距离，specialRoads是提供了几条特殊路径和距离，可以使用，也可以不使用

Step1. 所以如果最终的结果用到了一些specialRoads的路径，那么一定是从这specialRoads的起点进，终点出， 我们只需要关注最后一个用到的中间的点，然后计算出到达最后一个中间点的最短路径。

==> 所以题目就变成 求 start点到中间某一个点的最短路 + 中间这个点到target的路径。

Step2. 使用Dijkstra算法求得 从start到这些点的最短路径，每次都加入到优先队列中进行排序。

Step3. 最后枚举当前已经计算出的所有路径的终点，比较找到能到达最终终点的最短距离

*/

class Solution {
    public int minimumCost(int[] start, int[] target, int[][] specialRoads) {
        int n = specialRoads.length;
        int[] d = new int[n]; // d[i]表示从start出发，到specialRoads里面一个节点的最短路径

        //因为每次要拿最小的距离值，所以优先队列按照升序排列
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> (d[a] - d[b]));
        for(int i  = 0; i < n; i++){
            //计算出start到specialRoads终点的最短距离
            d[i] = Math.min(Math.abs(start[0] - specialRoads[i][0]) + Math.abs(start[1] - specialRoads[i][1]) + specialRoads[i][4], Math.abs(start[0] - specialRoads[i][2]) + Math.abs(start[1] - specialRoads[i][3]));
            //然后把这个最短距离的index存入到优先队列中
            pq.offer(i);
        }

        while(!pq.isEmpty()){
            int currIndex = pq.poll();
            for(int j = 0; j < n; j++){
                int minTargetDis = Math.min(Math.abs(specialRoads[currIndex][2] - specialRoads[j][0]) + Math.abs(specialRoads[currIndex][3] - specialRoads[j][1]) +  specialRoads[j][4], Math.abs(specialRoads[currIndex][2] - specialRoads[j][2]) + Math.abs(specialRoads[currIndex][3] - specialRoads[j][3])) + d[currIndex];
                
                if(minTargetDis < d[j]){
                    d[j] = minTargetDis;
                    pq.offer(j);
                }
            }
        }

        int resDistance = Math.abs(target[0] - start[0]) + Math.abs(target[1] - start[1]);
        //枚举当前已经计算出的所有路径的终点，比较找到能到达最终终点的最短距离
        for(int i = 0; i < n; i++){
            resDistance = Math.min(resDistance, Math.abs(target[0] - specialRoads[i][2]) + Math.abs(target[1] - specialRoads[i][3]) + d[i]);
        }

        return resDistance;
    }
}
