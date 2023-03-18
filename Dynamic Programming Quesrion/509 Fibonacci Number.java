/* Leetcode 509. Fibonacci Number

The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, 
such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,

F(0) = 0, F(1) = 1
F(n) = F(n - 1) + F(n - 2), for n > 1.
Given n, calculate F(n).

Example 1:

Input: n = 2
Output: 1
Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.

Example 2:

Input: n = 3
Output: 2
Explanation: F(3) = F(2) + F(1) = 1 + 1 = 2.
Example 3:

Input: n = 4
Output: 3
Explanation: F(4) = F(3) + F(2) = 2 + 1 = 3.
 

Constraints:
0 <= n <= 30
*/

/* 思路： DP method
        1. Dp 数组 和 下标的含义（dp[i] 定义为什么）：Fibonacci 数列中 第i个数字的值
        2. 递推公式 (状态转移方程)： dp[i] = dp[i - 1] + dp[i - 2]
        3. DP数组的初始化 (DP方程递推基础值)：dp[0] = 1  dp[1] = 1
        4. 确定遍历的顺序：从递推公式中可以看出，遍历顺序一定是从前往后进行
        5. 举例推导DP数组：验证DP方程是否附后题意
*/

class Solution {
    public int fib(int n) {
        //base case
        if(n <= 1){
            return n;
        }

        // Dp 数组 和 下标的含义 （dp[i] 定义为什么）
        int[] dp = new int[n + 1];

        //DP数组的初始化
        dp[0] = 0;
        dp[1] = 1;

        //确定遍历的顺序
        for(int i = 2; i <= n; i++){
            dp[i] = dp[i - 1] + dp[i - 2]; // 递推公式 (状态转移方程)
        }

        return dp[n];
    }
}
