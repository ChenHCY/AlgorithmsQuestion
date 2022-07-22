/* Leetcode 387. First Unique Character in a String

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

class Solution {
    public int firstUniqChar(String s) {
        //HashMap<key, value>
        HashMap<Character, Integer> map = new HashMap<>();
        char[] ch = s.toCharArray(); // use char[] array to find the index postion of non-repeating character 
        
        for(int i = 0; i < s.length(); i++){
            map.put(ch[i], map.getOrDefault(ch[i], 0) + 1); // add all the character from string s into hashmap
        }
        
        for(int j = 0; j < s.length(); j++){ // find the non-repeating character, means value of hashmap is 1
            if(map.get(ch[j]) == 1){ // find the non-repeating character, means value of hashmap is 1
                return j; // return the index position of character
            }
        }
        
        return -1; // If it does not exist, return -1.
    }
}
