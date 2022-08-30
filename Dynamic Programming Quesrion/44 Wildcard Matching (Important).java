/* Leetcode 44. Wildcard Matching
Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

 
Example 1:
Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".

Example 2:
Input: s = "aa", p = "*"
Output: true
Explanation: '*' matches any sequence.

Example 3:
Input: s = "cb", p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
 
Constraints:
0 <= s.length, p.length <= 2000
s contains only lowercase English letters.
p contains only lowercase English letters, '?' or '*'.
*/

class Solution {
    public boolean isMatch(String s, String p) {
        /* Dp State: dp[][] to check whether s[0-i] is same with p[0-j]
           Dp Initialize: dp[0][0] = true
           Dp function: 1. Normal Character s.charAt(i - 1) = p.charAt(j - 1): Example: s = "aa", p = "aa"
                            ==> dp[i][j] = dp[i-1][j-1]
                        2. p.charAt(j - 1) == '?':  Example: s = "aa", p = "a?"
                            ==> dp[i][j] = dp[i-1][j-1]
                        3. p.charAt(j - 1) == '*':  
                            ==> * can be only one time: dp[i][j] = dp[i][j-1]
                            ==> * can be multiple times: dp[i][j] = dp[i-1][j]
           Dp Answer:  return dp[s.length()][p.length()];
        */
        
        if(s == null || p == null){
            return false;
        }
      
        //Dp state:
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        //Dp Initialize:
        dp[0][0] = true;
        
        //Preprocessing functon for this kinds solution: s = "aab" , p ="c*aab" 
        for(int k = 1; k <= p.length(); k++){
            if(p.charAt(k - 1) == '*'){
                dp[0][k] = dp[0][k - 1];
            }
        }
        
        //Dp Function:
        for(int i = 1; i <= s.length(); i++){
            for(int j = 1; j <= p.length(); j++){
                //1. Normal Character s.charAt(i) = p.charAt(j):
                if(s.charAt(i - 1) == p.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1];
                } 
                //2. p.charAt(j-1) == '?' ==> '?' Matches any single character.
                if(p.charAt(j-1) == '?'){
                    dp[i][j] = dp[i - 1][j - 1];
                }
                
                //3. p.charAt(j-1) == '*':  
                if(p.charAt(j - 1) == '*'){
                    dp[i][j] = dp[i][j - 1] || dp[i-1][j];
                }
            }
        }
        
        
        //Dp Answer:
        return dp[s.length()][p.length()];
        
    }
}
