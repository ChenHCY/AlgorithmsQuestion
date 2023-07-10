/*Leetcode (Biweekly Contest 108) 2767 Partition String Into Minimum Beautiful Substrings

Given a binary string s, partition the string into one or more substrings such that each substring is beautiful.

A string is beautiful if:

      · It doesn't contain leading zeros.
       · It's the binary representation of a number that is a power of 5.

Return the minimum number of substrings in such partition. If it is impossible to partition the string s into beautiful substrings, return -1.

A substring is a contiguous sequence of characters in a string.

Example 1:

Input: s = "1011"
Output: 2
Explanation: We can paritition the given string into ["101", "1"].
- The string "101" does not contain leading zeros and is the binary representation of integer 51 = 5.
- The string "1" does not contain leading zeros and is the binary representation of integer 50 = 1.
It can be shown that 2 is the minimum number of beautiful substrings that s can be partitioned into.

Example 2:

Input: s = "111"
Output: 3
Explanation: We can paritition the given string into ["1", "1", "1"].
- The string "1" does not contain leading zeros and is the binary representation of integer 50 = 1.
It can be shown that 3 is the minimum number of beautiful substrings that s can be partitioned into.

Example 3:

Input: s = "0"
Output: -1
Explanation: We can not partition the given string into beautiful substrings.
 

Constraints:

1 <= s.length <= 15
s[i] is either '0' or '1'.

*/

class Solution {
    //此题是计算String s 划分之后，最小的数量的美丽字符串(没有前导0，字符串二进制的对应值是5的幂次方)
    public int minimumBeautifulSubstrings(String s) {
        int n = s.length();
        
        //1.预处理可能会用到和存在的5的幂次方 二进制值
        HashSet<String> set = new HashSet<>();
        //i每次乘以5
        for(int i = 1; ; i *= 5){
            String binaryNumber = Integer.toBinaryString(i); //把一个十进制的数 改成 二进制String字符串
            set.add(binaryNumber);
            //如果当前到达的5的幂次方 二进制长度 已经超越 String s的字符串长度，则之后的都不在需要
            if(binaryNumber.length() >= n){
                break;
            }
        }

        //dp method: dp[i]表示以i为结尾的最少子字符串个数
        int[] dp = new int[n + 1];
        //dp初始化：
        final int INF = 0x3f3f3f3f; // 避免潜在的溢出问题并简化后续检查时的比较。
        Arrays.fill(dp, INF);
        dp[0] = 0; 

        for(int i = 1; i <= n; i++){
            String currStr = ""; //每次形成的二进制字符串
            for(int j = i - 1; j >= 0; j--){
                currStr = s.charAt(j) + currStr;
                if(set.contains(currStr)){
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        //使用 INF 这样的特定值可以提高代码可读性
        return dp[n] == INF ? -1 : dp[n];
    }
}
