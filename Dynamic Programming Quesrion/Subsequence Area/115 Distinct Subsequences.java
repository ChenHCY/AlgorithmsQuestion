/* Leetcode 115. Distinct Subsequences

Given two strings s and t, return the number of distinct subsequences of s which equals t.

The test cases are generated so that the answer fits on a 32-bit signed integer.

Example 1:

Input: s = "rabbbit", t = "rabbit"
Output: 3
Explanation:
As shown below, there are 3 ways you can generate "rabbit" from s.
rabbbit
rabbbit
rabbbit

Example 2:

Input: s = "babgbag", t = "bag"
Output: 5
Explanation:
As shown below, there are 5 ways you can generate "bag" from s.
babgbag
babgbag
babgbag
babgbag
babgbag
 

Constraints:

1 <= s.length, t.length <= 1000
s and t consist of English letters.
*/

/* 思路： DP method ==》此题是找到有多少种办法让string s 来组成 string t
        ==> 所以只考虑 s中删除元素的情况，即 不用s[j - 1]来匹配 的情况。

        1. Dp 数组 和 下标的含义：String s[0, j]的字符串可以有多少种方式来组成 String t中[0, i]的字符串

        2. 递推公式：两种情况 ==》因为DP动态规划，也就是记忆化搜索，所以存在初始化的前置 dp[0] = 0
        a. 当遇到字母一样的时候，s.charAt(j - 1) == t.charAt(i - 1) 时，==》dp[i][j]可以有两部分组成。
        ==> 一部分是用s[j - 1](当前String s的字符)来匹配，那么个数为dp[i - 1][j - 1]。
        ==> 另一部分是不用s[j - 1](当前String s的字符)来匹配， 那么个数为dp[i][j - 1]。==》适用于当前字母t.charAt(i - 1)在string 中有多个一样的
        b. 当遇到字母不一样的时候，s.charAt(j - 1) != t.charAt(i - 1) 时，
        ==> 只有一种情况, 不用s[j - 1]来匹配, （就是模拟在s中删除这个元素），即：dp[i][j] = dp[i][j - 1]

        3. DP数组的初始化：可以看出dp[i][j] 是从左边和左上方推导而来，如图：，那么 dp[i][0] 和dp[0][j]是一定要初始化的。
        ==> dp[0][j] 表示：以 j - 1 为结尾的s可以随便删除元素，出现空字符串的个数。
            ==> 那么dp[0][j]一定都是1，因为也就是把以j-1为结尾的s，删除所有元素，出现空字符串的个数就是1。
        ==> dp[i][0]：空字符串s可以随便删除元素，出现以i-1为结尾的字符串t的个数。
            ==> 那么dp[i][0]一定都是0，因为String s 是空字符串，String t不为空。 ==> s无论如论 也不可能通过 来变成t。

        4. 确定遍历的顺序：所以遍历的时候一定是从上到下，从左到右，这样保证dp[i][j]可以根据之前计算出来的数值进行计算。
        5. 举例推导DP数组.
*/

/* 复杂度分析

时间复杂度：O(m*n)，其中 m 和 n 分别是字符串 s 和 t 的长度。二维数组 dp 有 n+1行 和 m+1列，需要对dp 中的每个元素进行计算。

空间复杂度：O(m*n)，其中 m 和 n 分别是字符串 s 和 t 的长度。创建了 n+1行 和 m+1列的二维数组
*/
class Solution {
    public int numDistinct(String s, String t) {        
        int m = s.length();
        int n = t.length();

        // Dp 数组 和 下标的含义： String s[0, j]的字符串可以有多少种方式来组成 String t中[0, i]的字符串
        int[][] dp = new int[n + 1][m + 1]; 
        
        //DP数组的初始化
        // dp[i][0]：空字符串s可以随便删除元素，出现以i-1为结尾的字符串t的个数
        // 那么dp[i][0]一定都是0，因为String s 是空字符串，String t不为空。 ==> s无论如论 也不可能通过 来变成t。
        for(int j = 0; j < m; j++){
            dp[0][j] = 1;
        }

        //Main travser loop
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                if(s.charAt(j - 1) == t.charAt(i - 1)){
                    dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1]; 
                } else{
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        return dp[n][m];
    }
}
