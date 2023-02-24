/* Leetcode 387  First Unique Character in a String

Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1.

Example 1:

Input: s = "leetcode"
Output: 0

Example 2:

Input: s = "loveleetcode"
Output: 2

Example 3:

Input: s = "aabb"
Output: -1
 

Constraints:

1 <= s.length <= 105
s consists of only lowercase English letters.
*/

/* 思路：

HashMap记录每个字母和出现的次数

然后返回出现次数为1的字母

*/

class Solution {
    public int firstUniqChar(String s) {
        //HashMap<key, value>
        HashMap<Character, Integer> map = new HashMap<>();

        for(char c : s.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1); // add all the character from string s into hashmap
        }
        
        for(int j = 0; j < s.length(); j++){ // find the non-repeating character, means value of hashmap is 1
            if(map.get(s.charAt(j)) == 1){ // find the non-repeating character, means value of hashmap is 1
                return j; // return the index position of character
            }
        }
        
        return -1; // If it does not exist, return -1.
    }
}
