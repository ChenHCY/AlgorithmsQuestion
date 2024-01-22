/* 72. Edit Distance

Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.

You have the following three operations permitted on a word:

Insert a character
Delete a character
Replace a character

Example 1:
Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')

Example 2:
Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation: 
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')
 

Constraints:
0 <= word1.length, word2.length <= 500
word1 and word2 consist of lowercase English letters.
*/

// Time: O(m * n)  Space: O(m * n)

// 思路： 动态规划：找到使word1成为word2的 最小修改距离
// dp[i][j]: 表示以下标 i-1 为结尾的字符串 word1，和以下标 j-1为 结尾的字符串word2，最近编辑距离为dp[i][j]。
// 如果字母相同，if(word1.charAt(i - 1) == word2.charAt(j - 1)): dp[i][j] = dp[i - 1][j - 1], 需要的改变距离不变
// 如果字母不相同，改变word1：选择需要距离最近的一种方式
// Insert a character in word1: dp[i][j] = dp[i - 1][j] + 1
// Delete a character in word1 = Insert a character in word2: dp[i][j] = dp[i][j - 1] + 1
// Replace a character in word1: dp[i][j] = dp[i - 1][j - 1] + 1
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1]; //dp存在前缀0

        //dp初始化：
        for(int i = 1; i <= m; i++){
            dp[i][0] = i;
        }

        for(int j = 1; j <= n; j++){
            dp[0][j] = j;
        }

        //main travser: 如果两个index的字母相等，不增加改变次数，如果改变 选择最小的一种形式
        for(int i = 1; i < m + 1; i++){
            for(int j = 1; j < n + 1; j++){
                if(word1.charAt(i - 1) == word2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1];
                } else{
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }

        return dp[m][n];
    }
}
