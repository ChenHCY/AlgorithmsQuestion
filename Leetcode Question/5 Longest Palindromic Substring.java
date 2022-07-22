/* Leetcode 5. Longest Palindromic Substring
Given a string s, return the longest palindromic substring in s.

Example 1:
Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.

Example 2:
Input: s = "cbbd"
Output: "bb"
 

Constraints:

1 <= s.length <= 1000
s consist of only digits and English letters.
*/
// Question Idea:Find a middle element, and the expand to both sides that check where is Palindromic
// We observe that a palindrome mirrors around its center. Therefore, a palindrome can be expanded from its center, and there are only 2^n−1 such centers.
class Solution {
    public String longestPalindrome(String s) {
        int start = 0; // range start postion
        int length = 0; // The Optimal solution length
        
        //Traverse every character from String S as the middle point
        for(int i = 0; i < s.length(); i++){
            int length1 = expandofMid(s, i, i); // odd postion
            int length2 = expandofMid(s, i, i+1); // even postion
            int currPostion = Math.max(length1, length2);// get the currPostion length
            
            if(currPostion > length){ //if new length is more than prev length
                length = currPostion; //renew the length
                start = i - (currPostion - 1) / 2; 
                //i is the middle point, so if we want to get start postion, need use i - length/2
                //the need care if length is even, such as "abba" and its center are between the two 'b's
            }
        }
        return s.substring(start, start + length);
    }
    //start at midd and expand to both sides that check where is Palindromic 
    private int expandofMid(String s, int left, int right){
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }
        return right - left - 1; // right - left + 1 - 2, 
        //for example: b a b a d
        //             L       R  we need length is "aba" => So is R - L + 1 - 2(need remove L and R postion element)
    }
}
