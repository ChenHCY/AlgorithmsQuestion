/* Leetcode 746. Min Cost Climbing Stairs
You are given an integer array cost where cost[i] is the cost of ith step on a staircase. Once you pay the cost, you can either climb one or two steps.

You can either start from the step with index 0, or the step with index 1.

Return the minimum cost to reach the top of the floor.

Example 1:

Input: cost = [10,15,20]
Output: 15
Explanation: You will start at index 1.
- Pay 15 and climb two steps to reach the top.
The total cost is 15.

Example 2:

Input: cost = [1,100,1,1,1,100,1,1,100,1]
Output: 6
Explanation: You will start at index 0.
- Pay 1 and climb two steps to reach index 2.
- Pay 1 and climb two steps to reach index 4.
- Pay 1 and climb two steps to reach index 6.
- Pay 1 and climb one step to reach index 7.
- Pay 1 and climb two steps to reach index 9.
- Pay 1 and climb one step to reach the top.
The total cost is 6.
 
Constraints:

2 <= cost.length <= 1000
0 <= cost[i] <= 999
*/

/* 思路： DP 动态规划 记忆化搜索 

使用DP 动态规划 记忆化搜索 记住到达每一个阶梯需要的最小值

因为可以一次跨一级阶梯 或者 两级阶梯，每一层加上的一定是当前层前两级阶梯的最小值

并且for-loop循环 从第2th阶梯 开始计算

 ==> dp[i] = cost[i] + Math.min(dp[i-1], dp[i-2]); ==》 每一层加上的一定是当前层前两级阶梯的最小值
 
*/
//Time: O(n)  Space: O(n)
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        if(cost.length == 0){
            return 0;
        }

        //call dp 
        int[] dp = new int[cost.length];
        dp[0] = cost[0]; // the 0th step
        dp[1] = cost[1]; // the 1th step

        //travser all the steps from cost start at 2th step
        for(int i = 2; i < cost.length; i++){
            //beacuse can climb one or two steps. so need judge and find the minvalue step
            dp[i] = cost[i] + Math.min(dp[i-1], dp[i-2]);
        } 

        //beacuse can climb one or two steps. so need judge and find the minvalue step
        return Math.min(dp[cost.length - 1], dp[cost.length - 2]);
    }
}
