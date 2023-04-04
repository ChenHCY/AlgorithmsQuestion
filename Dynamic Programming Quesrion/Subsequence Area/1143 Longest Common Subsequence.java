/* Leetcode 1143. Longest Common Subsequence
Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the 
relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".
A common subsequence of two strings is a subsequence that is common to both strings. 

Example 1:

Input: text1 = "abcde", text2 = "ace" 
Output: 3  
Explanation: The longest common subsequence is "ace" and its length is 3.

Example 2:

Input: text1 = "abc", text2 = "abc"
Output: 3
Explanation: The longest common subsequence is "abc" and its length is 3.

Example 3:

Input: text1 = "abc", text2 = "def"
Output: 0
Explanation: There is no such common subsequence, so the result is 0.
 

Constraints:

1 <= text1.length, text2.length <= 1000
text1 and text2 consist of only lowercase English characters.
*/

 /* 思路： DP method
        1. Dp 数组 和 下标的含义：i, j表示text1中的字母，DP数组记录的是在这个位置时两个的重复长度是多少
        ==》 dp[i][j]：长度为[0, i - 1]的字符串text1与长度为[0, j - 1]的字符串text2的最长公共子序列为dp[i][j]   
        
        2. 递推公式：两种情况：
        ==》1. 当两个字母一样的时候，text1.charAt(i - 1) == text2.charAt(j - 1) ==》 dp[i][j] = dp[i - 1][j - 1] + 1;
        ==》2. 当两个字母不一样的时候， text1.charAt(i - 1) != text2.charAt(j - 1) 
            ==>那就看看 text1[0, i - 2]与text2[0, j - 1]的最长公共子序列 和 text1[0, i - 1]与text2[0, j - 2]的最长公共子序列，取最大的。
            ==>  dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
        ==> 因为DP数组中是存在前置0的，所以容量会增加1，DP中的i, j 对应在String 中 为 i - 1 和 j - 1
        
        3. DP数组的初始化：dp[i][0] = 0  / dp[0][j] = 0 表示在 0-index 位置时， String text1 和 text2 没有字符是一样的
        
        4. 确定遍历的顺序：因为这可以看成一个填表格的遍历，当前格子的值 基于 上方，左边，左上 三个格子的值 
            ==》所以要从前向后，从上到下来遍历这个矩阵。
            
        5. 举例推导DP数组
*/

/*
复杂度分析
时间复杂度：O(mn)，其中 m 和 n 分别是字符串 text 1 和 text 2 的长度。
==> 二维DP的计算有m+1 和 n+1 行， 需要对于DP中的每一个元素都进行计算
空间复杂度：O(mn)，其中 m 和 n 分别是字符串 text 1 和 text 2 的长度。
==> 二维DP的计算有m+1 和 n+1 行， 需要对于DP中的每一个元素都进行储存

GitBook: 详细分析
*/

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        int[][ ] dp = new int[m + 1][n + 1]; // DP数组的初始化：
        
        //递推公式：两种情况
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(text1.charAt(i - 1) == text2.charAt(j - 1)){ //字母一样
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else{ //字母不一样
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        return dp[m][n];
    }
}
