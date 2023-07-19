/*Leetcode 97. Interleaving String

Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.

An interleaving of two strings s and t is a configuration where s and t are divided into n and m 
substrings respectively, such that:

    · s = s1 + s2 + ... + sn
    · t = t1 + t2 + ... + tm
    · |n - m| <= 1
    
The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
Note: a + b is the concatenation of strings a and b.

Example 1:
Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
Output: true
Explanation: One way to obtain s3 is:
Split s1 into s1 = "aa" + "bc" + "c", and s2 into s2 = "dbbc" + "a".
Interleaving the two splits, we get "aa" + "dbbc" + "bc" + "a" + "c" = "aadbbcbcac".
Since s3 can be obtained by interleaving s1 and s2, we return true.

Example 2:
Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
Output: false
Explanation: Notice how it is impossible to interleave s2 with any other string to obtain s3.

Example 3:
Input: s1 = "", s2 = "", s3 = ""
Output: true
*/

 /* DP状态方程： dp[i,j] 表示 s1 前i个字符 能与 s2前j个字符 组成 s3 前 i+j 个字符；
        1. dp[0][0] = true; 前置格子标记为true
        
        2. 初始化第一列 dp[i][0] 检查和标记 String s1 中 和 String s3 一样的字母 
        ==> if j = 0: dp[i][0] = s1[0-i) equals s3[0,i) 如果遇到false, 后面格子都为false
        
        3. 初始化第一行 dp[0][j] 检查和标记 String s2 中 和 String s3 一样的字母
        ==> if i = 0: dp[0][j] = s2[0-j) equals s3[0,j) 如果遇到false, 后面格子都为false

        4. 其他情况：遍历整个matrix，能否找到 一条从 左上 到达 右下 的 True 路径
        ==> 到达任意一个点 (i，j):  => 可以是选择 s1[i-1] 到达, 从 [i-1,j）点 向下 移动一步 到 [i,j] 
                                  => 可以是选择 s2[j-1] 到达， 从 [i, j-1] 点 向右 移动一步 到 [i, j] 
        ==> 所以当前坐标[i, j]位置能否组成String s3: 
        dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || (dp[i][j - 1] && s2.charAt(i - 1) == s3.charAt(i + j - 1))
*/
//思路： https://leetcode.cn/problems/interleaving-string/solution/lei-si-lu-jing-wen-ti-zhao-zhun-zhuang-tai-fang-ch/
// 
class Solution {
    //填格子的过程，检查String s1 和 s2 每一个字母 哪些是和String s3相等的
    //然后遍历整个matrix，能否找到 一条从 左上 到达 右下 的 True 路径
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();
        int t = s3.length();
        
        //如果长度不一样，一定不能通过String s1 和 s2 构成 s3
        if(m + n != t){
            return false;
        }
      
        // 动态规划，dp[i,j] 表示 s1 前i个字符 能与 s2前j个字符 组成 s3 前 i+j 个字符；
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true; //1. dp[0][0] = true; 前置格子标记为true

        //2. 检查和标记 String s1 中 和 String s3 一样的字母 
        for(int i = 1; i <= m && s1.charAt(i - 1) == s3.charAt(i - 1); i++){
            dp[i][0] = true;
        }

        //3. 检查和标记 String s2 中 和 String s3 一样的字母
        for(int j = 1; j <= n && s2.charAt(j - 1) == s3.charAt(j - 1); j++){
            dp[0][j] = true;
        }

        //其他情况：遍历整个matrix，能否找到 一条从 左上 到达 右下 的 True 路径
        //存在前导0格子，所以从1开始遍历
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                 dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
            }
        }

        return dp[m][n];
    }
}
