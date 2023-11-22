/*Leetcode 518. Coin Change II

You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

Return the number of combinations that make up that amount. If that amount of money cannot be made up by any combination of the coins, return 0.

You may assume that you have an infinite number of each kind of coin.

The answer is guaranteed to fit into a signed 32-bit integer.

Example 1:
Input: amount = 5, coins = [1,2,5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1

Example 2:
Input: amount = 3, coins = [2]
Output: 0
Explanation: the amount of 3 cannot be made up just with coins of 2.

Example 3:
Input: amount = 10, coins = [10]
Output: 1
 

Constraints:
1 <= coins.length <= 300
1 <= coins[i] <= 5000
All the values of coins are unique.
0 <= amount <= 5000
*/


/*
        1. 确定dp数组（dp table）以及下标的含义：
            表示使用前 i 个物品（可以重复使用)，放进 容量为 j 的背包，有 dp[i][j] 种方法可以装满背包
        2. 确定递推公式：
            a. i = 0 | j = 0 ==> 表示能选择的coins为 0 or 总额amount为 0 ==> 组成的方式也为0 
            b. 如果当前coin硬币值大于总额剩余的空间，无法放入： dp[i][j] = dp[i - 1][j]
            c. 如果当前coin硬币值不大于总额剩余的空间，可以选择放入， 计算所有的方式数量(包括放入不放入)：
                ==> dp[i][j] =  dp[i - 1][j] + dp[i - 1][j - weight[i]]
        3. dp数组初始化：dp[i][j] = 0 ==> when i = 0, j = 0; 
        4. 确定遍历顺序： 从左上往右下 ==> 当前格子的最大价值 取决于之前 格子的价值
*/
class Solution {
    //有一个背包，最大容量为 amount，有一系列物品 coins，每个物品的重量为 coins[i]，每个物品的数量无限。
    //请问有多少种方法，能够把背包恰好装满？
    public int change(int amount, int[] coins) {
        int n = coins.length;
        // n 为 coins 数组的大小  amount 为总额
        int[][] dp = new int[n + 1][amount + 1];

        //dp 初始化：当amount总额为0时，至少有一种方式
        for(int i = 0; i < n + 1; i++){
            dp[i][0] = 1;  
        }

        //遍历整个DP数组，选 OR 不选的思路
        for(int i = 1; i < n + 1; i++){
            for(int j = 1; j < amount + 1; j++){
                //2. 如果当前coin硬币值大于总额剩余的空间，无法放入
                if(coins[i - 1] > j){//==> 因为存在前导0的部分，所以第i个物品对应的硬币值为coins[i-1]
                    dp[i][j] = dp[i - 1][j]; 
                    //因为无法选择这个硬币，那么凑出面额 j 的方法数 dp[i][j] 应该等于 dp[i-1][j]，继承之前的结果。
                } 
                else{ // 3. 如果当前coin硬币值不大于总额剩余的空间，可以选择放入
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i-1]];
                    // ==> 因为存在前导0的部分，所以第i个物品对应的硬币值为coins[i-1]
                }
            }
        }

        return dp[n][amount];
    }
}
