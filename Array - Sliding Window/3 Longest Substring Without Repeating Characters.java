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
 

Constraints:
0 <= s.length <= 5 * 10^4
s consists of English letters, digits, symbols and spaces.
*/
//Time: O(logn)  Space: O(n)
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s.length() == 0 || s == null){
            return 0;
        }
        HashSet<Character> set = new HashSet<>();
        int res = 1;
        int left = 0;
        int right = 0;

        while(right < s.length()){
            //可以加入右指针，说明滑动窗口里面没有重复的character
            if(set.add(s.charAt(right))){
                set.add(s.charAt(right));
                right++;
                res = Math.max(res, set.size());
            } else{ //说明有重复的character
                //移动左指针 缩小滑动窗口
                set.remove(s.charAt(left));
                left++;
            }
        }

        return res;
    }
}
