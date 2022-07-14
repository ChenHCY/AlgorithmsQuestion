/*Leetcode 242. Valid Anagram

Given two strings s and t, return true if t is an anagram of s, and false otherwise.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.


Example 1:
Input: s = "anagram", t = "nagaram"
Output: true

Example 2:
Input: s = "rat", t = "car"
Output: false
 

Constraints:
1 <= s.length, t.length <= 5 * 104
s and t consist of lowercase English letters.
*/

class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }
        
        // HashMap <Key,Value> map;
        HashMap<Character, Integer> map = new HashMap<>();
        
        for(int i = 0; i < s.length(); i++){
            // if s have same character, hashmap value + 1
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);  
            // if t have same character, hashmap value - 1
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) - 1);
        } // so if s and t have the same elements and the same quantity, all values of the hashmap should be 0
        
        //The Java HashMap getOrDefault() method returns the specified default value if the mapping for the specified key is not found in the hashmap. 
     //Otherwise, the method returns the value corresponding to the specified key. 
        
        
        // HashMap <Key,Value> map; => So in here, we use value to check whether is 's' and 't' same elements and the same quantity.
        for(int value : map.values()){
            if(value != 0){ // if there is a element value is equal 0, it means not "Valid Anagram"
                return false; //not "Valid Anagram"
            }
        }
        return true;
    }
}
