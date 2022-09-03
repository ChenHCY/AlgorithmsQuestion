/* LintCode 192 · Wildcard Matching
Description
Given an input string s and a pattern p, implement wildcard pattern matching with support for '?' and '*'. The matching rules are as follows：

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

Example 1
Input:
"aa"
"a"
Output: false

Example 2
Input:
"aa"
"aa"
Output: true

Example 3
Input:
"aaa"
"aa"
Output: false

Example 4
Input:
"aa"
"*"
Output: true
Explanation: '*' can replace any string

Example 5
Input:
"aa"
"a*"
Output: true

Example 6
Input:
"ab"
"?*"
Output: true
Explanation: '?' -> 'a' '*' -> 'b'

Example 7
Input:
"aab"
"c*a*b"
Output: false
*/

Problem Solving Step Instructions：
1, If p.charAt(j-1) == s.charAt(i-1) :  dp[i][j] = dp[i-1][j-1];
2, If p.charAt(j-1) == '.' : dp[i][j] = dp[i-1][j-1];
3, If p.charAt(j-1) == '*': 
   here are two sub conditions:
               1. * counts as singe time or empty case: dp[i][j] = dp[i][j-1]
               2. * counts as multiple times: dp[i][j] = dp[i-1][j] 

public class Solution {
    /**
     * @param s: A string
     * @param p: A string includes "?" and "*"
     * @return: is Match?
     */
    public boolean isMatch(String s, String p) {
        // write your code here
        /*Dp State: dp[i][j]的含义是s[0-i] 与 p[0-i]是否匹配
          Dp Initialize: dp[0][0] = true
          Dp function: 1. s.charAt(i - 1) = p.charAt(j - 1) => dp[i][j] = dp[i-1][j-1]
                       2. p.charAt(j - 1) = '?'  // '?' Matches any single character.
                          ==> dp[i][j] = dp[i-1][j-1]
                       3. p.charAt(j - 1) = '*' // '*' Matches any sequence of characters 
                          ==> * Matches a single time: dp[i][j] = dp[i][j-1]
                          ==> * Matches a multiple times: dp[i][j] = dp[i-1][j]
          Dp Answer: return dp[s.length()][p.length()]
        */

        if(s == null || p == null){
            return false;
        }
        //Dp State:
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        //Dp Initialize:
        dp[0][0] = true;

        //Preprocessing functon for this kinds solution: s = "aab" , p ="c*aab" 
        for(int k = 1; k <= p.length(); k++){
            if(p.charAt(k - 1) == '*'){
                dp[0][k] = dp[0][k - 1];
            }
        }

        //Dp function:
        for(int i = 1; i <= s.length(); i++){
            for(int j = 1; j <= p.length(); j++){
                //1. s.charAt(i) = p.charAt(j)
                if(s.charAt(i - 1) == p.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1];
                }
                //2. p.charAt(j) = '?'
                if(p.charAt(j - 1) == '?'){
                    dp[i][j] = dp[i - 1][j - 1];
                }
                //3. p.charAt(j) = '*' 
                if(p.charAt(j - 1) == '*' ){
                    dp[i][j] = dp[i][j-1] || dp[i-1][j];
                }
            }
        }

        //Dp Answer:
        return dp[s.length()][p.length()];
    }
}
