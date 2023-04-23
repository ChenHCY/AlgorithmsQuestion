/* Leetcode 123. Best Time to Buy and Sell Stock III

You are given an array prices where prices[i] is the price of a given stock on the ith day.

Find the maximum profit you can achieve. You may complete at most two transactions.

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

Example 1:

Input: prices = [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.


Example 2:

Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.


Example 3:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
 

Constraints:

1 <= prices.length <= 10^5
0 <= prices[i] <= 10^5
*/

 /* 思路： 此题是计算买卖两次能获得的最大利润, 也就是自己还剩多少钱
        所以可以分成5种状态, 因为不能同时进行交易，所以这5个状态是相互联系的
        1. 刚开始的时候： 0 dp[i][0]  0 没有任何操作
        2. 第一次买入：buy1 dp[i][1]  Math.max(buy1, -prices[i]); 花钱第一次买入，所以是减去prices[i]的钱 现在手上的钱为buy1'
        3. 第一次卖出: sell1 dp[i][2] Math.max(sell1, buy1 + prices[i]); 卖出时赚钱，所以要加上prices[i]的钱 现在手上的钱为sell1'
        4. 第二次买入: buy2 dp[i][3] Math.max(buy2, sell1 - prices[i]); 再次花钱买入，所以要再次减去prices[i]的钱 现在手上的钱为buy2'            
        5. 第二次卖出: sell2 dp[i][4] Math.max(sell2, buy2 + prices); 再次卖出时赚钱， 所以要加上prices[i]的钱 现在手上的钱为sell2'
        ==》最后得到的sell2'， 也就是买卖两次能取得的最大利润
*/
//Time: O(n)   Space: O(1)
class Solution {
    public int maxProfit(int[] prices) {
        if(prices.length == 0){
            return 0;
        }

        int buy1 = -prices[0];
        int buy2 = -prices[0];
        int sell1 = 0;
        int sell2= 0;

        for(int i = 0; i < prices.length; i++){
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, buy1 + prices[i]);
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }

        return sell2;
    }
}
