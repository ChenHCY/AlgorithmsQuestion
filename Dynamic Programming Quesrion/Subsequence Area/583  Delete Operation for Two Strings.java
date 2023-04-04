/* Leetcode 583. Delete Operation for Two Strings

Given two strings word1 and word2, return the minimum number of steps required to make word1 and word2 the same.

In one step, you can delete exactly one character in either string.

Example 1:

Input: word1 = "sea", word2 = "eat"
Output: 2
Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".

Example 2:

Input: word1 = "leetcode", word2 = "etco"
Output: 4
 

Constraints:
1 <= word1.length, word2.length <= 500
word1 and word2 consist of only lowercase English letters.
*/

/* 思路： DP method 

  ==》因为是要找到最少的删除操作的总次数, 也就是让word1和word2一样，需要删除最少的字符数量
  
  ==》所以我们要找到两个字符串最长的重复子序列  ==》和 1143题的思路一样
  
  ==》然后分别计算两个字符串的长度（m or n）和这个最长公共子序列(maxLen)的长度之差，这就是为两个字符串分别需要删除的字符数
  
  ==> 两个字符串各自需要删除的字符数之和即为 "最少的删除操作的总次数"。

  ==》1143题思路： 通过使用DP 找到两个字符串里面的最长子序列。==》 `子序列是一个字符序列，这些字符可能不连续`
  
   1. Dp 数组 和 下标的含义：dp[i][j]表示word1和word2最长重复子序列 ==》和 1143题的思路一样
   2. 递推公式：  
        a. 当两个String中的字母一样的时候，  ==> text1.charAt(i - 1) == text2.charAt(j - 1)
          ==> dp[i][j] = dp[i - 1][j - 1] + 1;
           
        b. 当两个String中的字母不一样的时候，==> text1.charAt(i - 1) != text2.charAt(j - 1)
          ==> dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
   
    3. DP数组的初始化：dp[i][0] = 0 / dp[0][j] = 0 
    
    4. 确定遍历的顺序：因为这可以看成一个填表格的遍历，当前格子的值 基于 上方，左边，左上 三个格子的值
            ==>  所以要从前向后，从上到下来遍历这个矩阵。
    5. 举例推导DP数组
*/


/*     复杂度分析
时间复杂度：O(mn)，其中 m 和 n 分别是字符串 text 1 和 text 2 的长度。
==> 二维DP的计算有m+1 和 n+1 行， 需要对于DP中的每一个元素都进行计算

空间复杂度：O(mn)，其中 m 和 n 分别是字符串 text 1 和 text 2 的长度。
==> 二维DP的计算有m+1 和 n+1 行， 需要对于DP中的每一个元素都进行储存

GitBook: 详细分析
*/

class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int res = 0;
        int maxLen = 0;
        int[][] dp = new int[m + 1][n + 1];

        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(word1.charAt(i - 1) == word2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else{
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }       
            }
        }

        maxLen = dp[m][n];

        res = m - maxLen + (n - maxLen); //count the min steps need to do that make word1 is same with word2

        return res;
    }
}
