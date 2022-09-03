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

//1. Used one pointer(sp) moves in the String s, the second pointer(pp) moves in the String p
//2. Used star to save the position of '*' in String p
//3. Used match to save the position in String s when pp is '*'
//4. if two pointer can match it follow the rules, advancing it.
//5. Check whether pp-pointer can arrived last element of Stirng p. Yes -> True // No -> False

// Time: O(n)  This solution is the optimal solution of the time complexity of the problem

class Solution {
    public boolean isMatch(String s, String p) {
        int sp = 0; // first-pointer use for save the postion of s
        int pp = 0; // second-pointer use for save the position of p
        int star = -1; // use to save the postion of there is '*'
        int match = 0; //use to save the sp position when pp is '*'
        
        while(sp < s.length()){
            //if the sp pointer is same with pp or pp pointer is '?', advancing both of them
            if(pp < p.length() && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?')){
                sp++;
                pp++;
            } 
            
            //when the curr pointer is '*', only advancing the pp pointer in the String p
            else if (pp < p.length() && p.charAt(pp) == '*'){
                star = pp; // use star save the current position of String p, also is '*' position
                match = sp; // use the match save the current postion of String s
                pp++; 
            } 
            
            // This part is used for if the last element of p was *, advancing String s pointer 
            else if (star != -1){ 
                pp = star + 1;
                match++;
                sp = match;
            } 
            
            // if all of the solution did not match, return false
            else{ 
                return false;
            }
        }
        
        //when the sp arrived s.length(), checking the remaining characters in the String p
        while(pp < p.length() && p.charAt(pp) == '*'){
            pp++;
        }
        
        //check whether pp arrived last element of String p length
        return pp == p.length();
    }
}
