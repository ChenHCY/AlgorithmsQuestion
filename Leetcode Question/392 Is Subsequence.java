/*Leetcode 392. Is Subsequence
Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of 
the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).

Example 1:
Input: s = "abc", t = "ahbgdc"
Output: true

Example 2:
Input: s = "axc", t = "ahbgdc"
Output: false
 

Constraints:

0 <= s.length <= 100
0 <= t.length <= 104
s and t consist only of lowercase English letters.
 

Follow up: Suppose there are lots of incoming s, say s1, s2, ..., sk where k >= 109, 
and you want to check one by one to see if t has its subsequence. In this scenario, how would you change your code?
*/

class Solution {
    public boolean isSubsequence(String s, String t) {
        //beacuse this is check whether s is a subsequence of t
        if(s.length() == 0) // if s is null, return true
            return true;
        if(t.length() == 0) // if t is null, return false
            return false;
        
        int i = 0; // move between in the range of String s
        int j = 0; // move between in the range of String t
        
        while(j < t.length()){
            if(s.charAt(i) == t.charAt(j)){
                i++;
                //if i arrived last element of string s
                if(i == s.length()){
                    return true; //return true, s is a subsequence of t
                }
            }
            j++; //every time move one element of String t
        }
        
        return false;
    }
}
