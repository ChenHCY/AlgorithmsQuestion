/* 279. Perfect Squares

Given an integer n, return the least number of perfect square numbers that sum to n.

A perfect square is an integer that is the square of an integer; in other words, it is the product of 
some integer with itself. For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.

Example 1:
Input: n = 12
Output: 3
Explanation: 12 = 4 + 4 + 4.

Example 2:
Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.
 
Constraints:
1 <= n <= 10^4
*/

class Solution {
    public int numSquares(int n) {
        /*最少用几个完全平方数把n组成出来
        Dp的思想： 拿前面的结果推后面的结果，要看前面每一步的结果。
        */
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE); //使用最大的数组来

        dp[0] = 0; //Dp start:

        //Dp function ==> 假设每一个数都是一个因子
        for(int i = 1; i <= n; i++){
            for(int j = 1; j * j <= i; j++){
                // i - j * j 只要再加一个平方数 j * j 即可凑出 i
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

        return dp[n]; //可以得到最后结束的数字需要几个数字
    }
}
