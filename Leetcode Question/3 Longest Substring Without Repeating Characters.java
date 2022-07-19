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
        HashSet<Character> set = new HashSet<>(); //hashset did not allowed same element
        int start = 0; // range start pointer
        int end = 0; // range end pointer
        int length = 0; // rang length
        
        //use while-loop traverse each character from the String
        while (end < s.length()) {
        // if HashSet can add the character element, means there is not same element in the HashSet
            if (set.add(s.charAt(end))) {
                end++; //so move end pointer into next character
                //Get the length of set that without repeating characters
                length = Math.max(length, set.size()); 
            } else { //if HashSet can not add the character element, means there is the same element in the HashSet
                set.remove(s.charAt(start)); 
                //remove the character start-point in the start position
                start++; //move start-pointer into next element
            }
        }
        
        return length;
    }
}
