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

// 思路：遍历String s中的所有字母，假设每个字母都是回文字符串的中间轴，
// 使用一个小的function 计算可能存在的回文字符串长度
// We observe that a palindrome mirrors around its center. Therefore, a palindrome can be expanded from its center, and there are only 2^n−1 such centers.
// Time: O(n^2)   Space: O(1)
class Solution {
    public String longestPalindrome(String s) {
        int start = 0; // range start postion
        int length = 0; // The Optimal solution length
        
        // Traverse every character from String S as the middle point
        // 我们遍历是把每个index位置对应的character 当做回文字符串的中间字母
        for(int i = 0; i < s.length(); i++){
            // 假设回文字符串的长度是单数，直接从中间点(i) 开始比较是否形成回文
            int length1 = expandofMid(s, i, i); // odd postion

            // 假设回文字符串的长度是双数，没有中间的，从i和 i+1 开始比较
            int length2 = expandofMid(s, i, i+1); // even postion
            int currPostion = Math.max(length1, length2);// get the currPostion length
            
            // 更新 if new length is more than prev length
            if(currPostion > length){ 
                //更新之后的最长回文长度
                length = currPostion; //renew the length

                //最长回文字符串的起始位置
                start = i - (currPostion - 1) / 2; 
                //i is the middle point, so if we want to get start postion, need use i - length/2
                //the need care if length is even, such as "abba" and its center are between the two 'b's
            }
        }
        return s.substring(start, start + length);
    }

    // 计算可能存在的回文字符串长度
    private int expandofMid(String s, int left, int right){

        //while-loop循环，如果左右指针对应的字母一样，则继续 ==> 直到两个指针不一样
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }

        // for example: b a b a d
        //              L       R  
        //==> we need length is "aba" => So is R - L + 1 - 2(need remove L and R postion element)
        // 更新这个回文字符串的长度
        return right - left - 1; // right - left + 1 - 2, 
        
    }
}
