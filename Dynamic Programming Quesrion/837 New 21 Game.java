/* Leetcode 837. New 21 Game

Alice plays the following game, loosely based on the card game "21".

Alice starts with 0 points and draws numbers while she has less than k points. During each draw, 

she gains an integer number of points randomly from the range [1, maxPts], where maxPts is an integer. Each draw is independent and the outcomes have equal probabilities.

Alice stops drawing numbers when she gets k or more points.

Return the probability that Alice has n or fewer points.

Answers within 10-5 of the actual answer are considered accepted.

Example 1:

Input: n = 10, k = 1, maxPts = 10
Output: 1.00000
Explanation: Alice gets a single card, then stops.

Example 2:

Input: n = 6, k = 1, maxPts = 10
Output: 0.60000
Explanation: Alice gets a single card, then stops.
In 6 out of 10 possibilities, she is at or below 6 points.

Example 3:

Input: n = 21, k = 17, maxPts = 10
Output: 0.73278
 
Constraints:

0 <= k <= n <= 10^4
1 <= maxPts <= 10^4
*/

/* 题意：==》 21点游戏，然后求Alice的获胜概率

1. 给定一个最大积分牌maxPts, 一个终止继续抽牌的积分值K， 一个判断获胜的积分值n
2. Alice的起始积分是0分
3. 然后会在 【1 - maxPts】中随机抽取一张牌，每张积分牌的概率始终一样
4. 当累积积分大于 k 时，就会停止抽牌
5. 最后如果这个值小于 n Alice 获胜，==》计算这个概率是多少
*/

/* 思路： 倒序DP的方法 + 滑动窗口，==》 从后往前推导每个积分值获胜的概率，最后得到在这种n k maxPts的情况下，从0开始的获胜概率
    DP里面储存的是当前拥有积分 能获胜的概率
    
    1. 首先要确定最右边的边界，就是找到能100%赢得游戏的界限
    ==> 如果手上的点数为K - 1时，能拿到的一个新的积分值W，如果 K - 1 + W 不大于 n, 这个界限就是能100%赢得游戏
    
    2. 往前遍历普通情况, 我们时从后往前逆向求, 同时由于当前积分不大于 K， 所以需要【1 - maxPts】区域内再抽一张牌
     所以i积分能获胜的概率为 （i积分值 + 1） - （i积分值 + maxPts） / maxPts
    所以比手上点数为 16 时赢得游戏的概率，从而在往前求手上点数为 15 时的概率，最后求出手上点数为 0 时的赢得游戏的概率
     这就是滑动窗口的思想，每次往前添加一个，同时删除最后面的一个
*/
// 时间复杂度：O(N - K + K) ==> O(N)
// 空间复杂度：O(K + maxPts)
class Solution {
    public double new21Game(int n, int k, int maxPts) {
        //1. 首先要确定最右边的边界，就是找到能100%赢得游戏的界限
        if(k - 1 + maxPts <= n){
            return 1.0;
        }

        //空间复杂度：O(K + maxPts)
        double[] dp = new double[k + maxPts]; //size为最大可能性，在拥有K积分时，再次抽到最大积分牌maxPts
        //然后确定100%赢的游戏的积分值区域
        for(int i = k; i <= n; i++){
            dp[i] = 1.0;
        }
        
        // 2.往前遍历普通情况, 我们是从后往前逆向计算，最后得到0积分开始获胜的概率
        
        //==> 计算第一个滑动窗口区域的概率和
        double sumPro = n - k + 1; //因为在【k + 1, n】这个区域的每个积分值获胜的概率都是1.0，所以直接计算差值
        //在【n + 1, k + maxPts】这个区域所有的积分值获胜概率都为0，所有不用放入统计

        //main for-loop: 从后往前逆向计算，最后得到0积分开始获胜的概率
        for(int i = k - 1; i >= 0; i--){
            // 点数为 i 的赢得游戏的概率为 （i积分值 + 1） - （i积分值 + maxPts）的概率和除以  maxPts
            dp[i] = sumPro / maxPts;
            //然后滑动窗口，继续往前遍历计算 ==> 每次往前添加一个，同时删除最后面的一个
            sumPro = sumPro - dp[i + maxPts] + dp[i];  
        }

        return dp[0];
    }
}

