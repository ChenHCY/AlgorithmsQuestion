/*Leetcode 10. Regular Expression Matching*/
/* Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:

'.' Matches any single character.
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

Example 1:
Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".

Example 2:
Input: s = "aa", p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".

Example 3:
Input: s = "ab", p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".
 
Constraints:
1 <= s.length <= 20
1 <= p.length <= 30
s contains only lowercase English letters.
p contains only lowercase English letters, '.', and '*'.
It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.
*/

class Solution {
    public boolean isMatch(String s, String p) {
    /*  Dp State: check whetehr s[0-i] is same with p[0-j]
        Dp Initialize: dp[0][0] = true 
        Dp Function: 1. s.charAt(i) = p.charAt(j) ==> dp[i][j] = dp[i-1][j-1] (Normal Character)
                     2. p.charAt(j) = "." ==> s = "aa", p = "a."  ==>  "True" 
                        '.' Matches any single character ==> so do not need to check s.charAt(i)
                        just back to dp[i][j] = dp[i-1][j-1]
                     3. p.charAt(j) = "*"
                        1. p.charAt(j - 1) != s.charAt(i) ==> s = "baab" , p ="bc*aab" 
                           '*' Matches zero or more of the preceding element. ==> So need to check the character in the "*" before
                            ==>  dp[i][j] == dp[i][j-2] ==> in this case. c* counts as Empty, means remove "c*"
                        2. p.charAt(j - 1) == s.charAt(j) or p.charAt(j-1) = '.':
                            a. * be single time: dp[i][j] = dp[i][j-1] ==> s = "aa", p = "a*" 
                            b. * be multiple times: dp[i][j] = dp[i-1][j] ==> s = "aaa", p = "a*" 
                               i = 0, j = 0, s.charAt(i) = 'a', p.charAt(i) = 'a'  ==> True
                               i = 1, j = 1, s.charAt(i) = 'a', p.charAt(i) = '*'  ==> True 
                               i = 2. check whether it equals i-1, which means '*' can match one more same element
                            c. * be Empty: dp[i][j] = dp[i][j-2] ==> in this case. a* counts as Empty, means remove "a*"
         Dp Answer: return dp[s.length()][p.length()]
     */
        
        if(s == null || p == null){
          return false;
        }
        //Dp state: check whether s[0-i] is same with p[0-j]
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true; //  Dp Initialize
        
        //Preprocessing functon for this kinds solution: s = "aab" , p ="c*aab" 
        for(int k = 0; k < p.length(); k++){
            // k can not out of bounds ==> K should large than 0
            if(k > 0 && p.charAt(k) == '*' && dp[0][k - 1]){ //check whether dp[0][k - 1] is true 
              dp[0][k + 1] = true;  
            } 
        }
        
        //Dp Function: Memorize Search
        for(int i = 0; i < s.length(); i++){
            for(int j = 0; j < p.length(); j++){
                // 1. s.charAt(i) = p.charAt(j) ==> dp[i][j] = dp[i-1][j-1] (Normal Character)
                if(s.charAt(i) == p.charAt(j)){  
                    dp[i + 1][j + 1] = dp[i][j];
                }
                // 2. p.charAt(j) = "."  just back to dp[i][j] = dp[i-1][j-1]
                if(p.charAt(j) == '.'){
                    dp[i + 1][j + 1] = dp[i][j];
                }
                // 3. p.charAt(j) = "*"
                if(p.charAt(j) == '*'){
                    //a. p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.'
                    // Example: s = "baab" , p ="bc*aab"  
                    if(p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.'){
                        dp[i + 1][j + 1] = dp[i + 1][j - 1]; 
                    } else{ //b. Other solution: '"*" be empty, single time, or multiple times
                        dp[i + 1][j + 1] = (dp[i + 1][j] || dp[i][j + 1] || dp[i +1 ][j - 1]);
                    }
                }   
            }
        }
        
        return dp[s.length()][p.length()];
    }
}
