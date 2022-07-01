/* Leetcode 121. Best Time to Buy and Sell Stock
You are given an array prices where prices[i] is the price of a given stock on the ith day.
You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.

Example 1:
Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.

Example 2:
Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transactions are done and the max profit = 0.
*/

// My Solution: Used "Two pointer" and ArrayList to check and get the maximize profit
// Time complexity：O(n)  Space complexity：O(n)
class Solution {
    public int maxProfit(int[] prices) {
        int slow = 0;
        int fast = 1;
        List<Integer> list = new ArrayList<Integer>();
        
        while(fast <= prices.length - 1){
            if(prices[slow] < prices[fast]){
                list.add(prices[fast] - prices[slow]);
                fast++;
            } else{
                slow = fast;
                fast++;
            }
        }
        
        if(list.size() == 0){
            return 0;
        }
        
        return Collections.max(list);
    }
}

**************************************************************************************************
//The best solution: Dynamic Programming Method
// Time complexity：O(n)  Space complexity：O(1)
class Solution {
    public int maxProfit(int[] prices) {
        int minBuyPrice = prices[0];
        int maxProfit = 0;
        
        for (int i = 1; i < prices.length; i++) {
            minBuyPrice = Math.min(prices[i], minBuyPrice);
            maxProfit = Math.max(prices[i] - minBuyPrice, maxProfit);
        }
        
        return maxProfit;
    }
}
