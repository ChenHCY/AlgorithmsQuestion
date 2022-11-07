/* Leetcode 680. Valid Palindrome II
Given a string s, return true if the s can be palindrome after deleting at most one character from it.

Example 1:
Input: s = "aba"
Output: true

Example 2:
Input: s = "abca"
Output: true
Explanation: You could delete the character 'c'.

Example 3:
Input: s = "abc"
Output: false
 
Constraints:
1 <= s.length <= 105
s consists of lowercase English letters.
*/

//Time: O(N)  Space: O(1）

class Solution {
    public boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        
        while(left < right){
            //if meet the difference character
            if(s.charAt(left) != s.charAt(right)){
                //call function to check next left or next right
               return checkPalindrome(s, left + 1, right) || checkPalindrome(s, left, right - 1);
            }
            left++;
            right--;
        }
        return true;
    }
    
    private static boolean checkPalindrome(String s, int l, int r){
        //if the two pointer move out the line
        if(l >= s.length() || r < 0){
            return false;
        } 
        //basic two pointer while-loop
        while(l < r){
            if(s.charAt(l) != s.charAt(r)){
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}
