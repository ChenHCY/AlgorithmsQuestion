/* leetcode 322. Coin Change
You are given an integer array coins representing coins of different denominations and 
an integer amount representing a total amount of money.

Return the fewest number of coins that you need to make up that amount. 
If that amount of money cannot be made up by any combination of the coins, return -1.

You may assume that you have an infinite number of each kind of coin.

Example 1:
Input: coins = [1,2,5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1

Example 2:
Input: coins = [2], amount = 3
Output: -1

Example 3:
Input: coins = [1], amount = 0
Output: 0
 
Constraints:
1 <= coins.length <= 12
1 <= coins[i] <= 231 - 1
0 <= amount <= 104
*/
//Used Memorize Search (dp method)
//Time: O(n) 
class Solution {
    public int coinChange(int[] coins, int amount) {
        //Memorize Search Method and dp method
        int[] dp = new int[amount + 1];// dp state
        
        //fill every space in dp with Integer.MAX_VALUE
        Arrays.fill(dp, amount + 1);
        
        dp[0] = 0; //dp initialize 
        
        /*
        dp state: dp[i] means there is min coins used for every amount
        dp initialize: dp[0] = 0
        dp function: dp[i] = Math.min(dp[i], dp[i - coin] + 1);
        dp return: return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
        */
        
        //travser all the coins value from coins[] list
        for(int i = 0; i <= amount; i++){
            for(int coin : coins){
                if(i - coin >= 0){ //choose the coins kinds to use, that find use min numbers of coin
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1); // dp function:
                }
            }
        }
        
        //dp return:
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}
