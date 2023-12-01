/* Leetcode 1456. Maximum Number of Vowels in a Substring of Given Length

Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.

Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.

Example 1:
Input: s = "abciiidef", k = 3
Output: 3
Explanation: The substring "iii" contains 3 vowel letters.

Example 2:
Input: s = "aeiou", k = 2
Output: 2
Explanation: Any substring of length 2 contains 2 vowels.

Example 3:
Input: s = "leetcode", k = 3
Output: 2
Explanation: "lee", "eet" and "ode" contain 2 vowels.
 
Constraints:
1 <= s.length <= 10^5
s consists of lowercase English letters.
1 <= k <= s.length
*/
// Time: O(n) n is the string s length
// Space: O(1)
class Solution {
    public int maxVowels(String s, int k) {
        int count = 0;

        //首先统计第一个长度为k的窗口内，有多少个原因字母
        for(int i = 0; i < k; i++){
            char c = s.charAt(i);
            if("aeiou".indexOf(c) != -1){
                count++;
            }
        }

        int res = count; //得到了第一个长度为k的滑动窗口的元音字母数量
        
      // 继续遍历后面，检查每个长度为k的滑动窗口，找到最多的元音数量
        for(int j = k; j < s.length(); j++){
            //最右边 新加入的字母是元音字母
            if("aeiou".indexOf(s.charAt(j)) != -1){
                count++;
            }

            //最左边 需要删除的字母也是元音
            if("aeiou".indexOf(s.charAt(j - k)) != -1){
                count--;
            }

            //更新数量
            res = Math.max(res, count);
        }
        return res;
    }
}
