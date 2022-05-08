/* 3. Longest Substring Without Repeating Characters
Given a string s, find the length of the longest substring without repeating characters.

Example 1:
Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.

Example 2:
Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.

Example 3:
Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
*/
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0)
        {
            return 0;
        }
        
        int maxLength = 0; // the longest substring
        int left = 0;//first pointer
        int current = 0; // move pointer
        
        Set<Character> uniqueCharacters = new HashSet<>();
        
        while(current < s.length())
        {
            if(uniqueCharacters.add(s.charAt(current))){
                current++;
                maxLength = Math.max(maxLength, uniqueCharacters.size());
            } else{
                uniqueCharacters.remove(s.charAt(left));
                left++;
            }
        }
        return maxLength;
    }
}
