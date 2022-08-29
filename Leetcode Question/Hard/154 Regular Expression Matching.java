/* Lintcode 154 · Regular Expression Matching

Description
The implementation supports regular expression matching for '.' and '*'. 

  '.' matches any single character. 
  '*' matches zero or more of the preceding elements, before '*' is guaranteed to be a non-'*' element. 

The match should cover the entire input string, not just a part of it. The function that needs to be implemented is: bool isMatch(string s, string p)

isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true

Example

Example 1:
Input: "aa", "a"
Output: false
Explanation:
Can't match

Example 2:
Input: "aa", "a*"
Output: true
Explanation:
'*' can repeat a

Example 3:
Input: "aab", "c*a*b"
Output: true
Explanation:
"c*" matches 0'c' as a whole, which is ""
"a*" matches 2'a' as a whole, which is "aa"
"b" matches "b"
So "c*a*b" can match "aab"
``` repeat a
*/

public class Solution {
    /**
     * @param s: A string 
     * @param p: A string includes "." and "*"
     * @return: A boolean
     */
    public boolean isMatch(String s, String p) {
        // write your code here
        if(s == null || p == null){
            return false;
        }
        /*  Dp State: Used dp[][] to check whether s[0-i] and p[0-j] is same
            Dp Initialize: dp[0][0] = true
            Dp Function: 1. s.charAt(i) = p.charAt(j) ==> dp[i][j] = dp[i-1][j-1]: Example: s="aa" ; p="aa" 
                         2. p.charAt(j) == '.': do not need check s.charAt(i) ==> dp[i][j] = dp[i-1][j-1]: 
                            Example: s="aa" ; p="a."
                         3. p.charAt(j) == '*': 
                            A. p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.':
                               Example: s="baab" ; p="bc*aab" ==> dp[i][j] == dp[i][j-2] 
                            B. p.charAt(j - 1) == s.charAt(i) && p.charAt(j - 1) == '.':
                               a. Example: s="aa" ; p="a*"  ==> '*' be only single time: dp[i][j] =  dp[i][j-1]
                               b. Example: s="aaa" ; p="a*" ==> '*' be multiple times: dp[i][j] = dp[i - 1][j]
                               c. Example: s="aab" ; p="c*aab" ==> '*' be empty case: dp[i][j] = dp[i][j-2]
            Dp Answer: return dp[s.length()][p.length()]
        */

        // Dp State
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        // Dp Initialize
        dp[0][0] = true; 
        //Preprocessing functon for this kinds solution: s = "aab" , p ="c*aab" 
        for(int k = 0; k < p.length(); k++){
            if(k > 0 && p.charAt(k) == '*' && dp[0][k - 1]){
                dp[0][k + 1] = true;
            }
        }

        //Main Dp Function:
        for(int i = 0; i < s.length(); i++){
            for(int j = 0; j < p.length(); j++){
                //1. s.charAt(i) = p.charAt(j) ==> dp[i][j] = dp[i-1][j-1]
                if(s.charAt(i) == p.charAt(j)){
                    dp[i + 1][j + 1] = dp[i][j];
                }
                //2. p.charAt(j) == '.':
                if(p.charAt(j) == '.'){
                    dp[i + 1][j + 1] = dp[i][j];
                }
                //3. p.charAt(j) == '*': 
                if(p.charAt(j) == '*'){
                    //3A:
                    if(p.charAt(j-1) != s.charAt(i) && p.charAt(j - 1) != '.'){
                        dp[i + 1][j + 1] = dp[i + 1][j - 1];
                    } else{ //3B: 
                        dp[i + 1][j + 1] = (dp[i + 1][j] || dp[i][j + 1] || dp[i + 1][j - 1]);
                    }
                }
            }
        }

        return dp[s.length()][p.length()]; //Dp Answer
    }
}
