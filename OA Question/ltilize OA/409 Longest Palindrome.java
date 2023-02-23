/* Leetcode 409. Longest Palindrome

Given a string s which consists of lowercase or uppercase letters, return the length of the longest palindrome that can be built with those letters.

Letters are case sensitive, for example, "Aa" is not considered a palindrome here.

Example 1:

Input: s = "abccccdd"
Output: 7
Explanation: One longest palindrome that can be built is "dccaccd", whose length is 7.

Example 2:

Input: s = "a"
Output: 1
Explanation: The longest palindrome that can be built is "a", whose length is 1.
 

Constraints:

1 <= s.length <= 2000
s consists of lowercase and/or uppercase English letters only.
*/

/*

思路：

此题是找到最长的回文字符串

所有我们可以通过使用HashSet找到String s中出现次数为单数的character

然后两种情况：

1. 如果存在有单数出现次数的字母 ==》 最长回文串的长度 为 双数出现次数的字母长度 + 1 = 总长度 - 单个出现的字符长度 + 1
==》  res = length - set.size() + 1;

2. 如果不存在任何单数出现的字母 ==》 直接返回总长度（也是双数出现次数的字母长度）
*/

class Solution {
    public int longestPalindrome(String s) {
        if(s.length() < 1 || s == null){
            return 0;
        }
        int length = s.length();
        int res = 0;
        
        //HashSet did not allowed have same number
        HashSet<Character> set = new HashSet<>();
        
        //Traverse every character from String s and try to find singular letters
        for(char c : s.toCharArray()){
            if(set.contains(c)){ //if hashset ready have curr character
                set.remove(c); // remove curr characrer from hashset
            } else{
                set.add(c); // if not, add in hashset => finally will get all singular letters
            }
        }
        
        //if we want to find the Longest Palindrome, the palindrome consists of letters with equal partners, plus possibly a unique center 
        if(set.size() == 0){ 
            //if there is did not have any singular letters, the longest Palindrome is the Length of double-digit letters
            res = length - set.size(); 
        } else{
             //if there is any singular letters, the longest Palindrome is the Length of double-digit letters and plue one (unique center)
            res = length - set.size() + 1;
        }
        
        return res;
        
    }
}
