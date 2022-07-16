/* Leetcode 290. Word Pattern
Given a pattern and a string s, find if s follows the same pattern.
Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s.

Example 1:
Input: pattern = "abba", s = "dog cat cat dog"
Output: true

Example 2:
Input: pattern = "abba", s = "dog cat cat fish"
Output: false

Example 3:
Input: pattern = "aaaa", s = "dog cat cat dog"
Output: false

Constraints:
1 <= pattern.length <= 300
pattern contains only lower-case English letters.
1 <= s.length <= 3000
s contains only lowercase English letters and spaces ' '.
s does not contain any leading or trailing spaces.
All the words in s are separated by a single space.

*/

class Solution {
    public boolean wordPattern(String pattern, String s) {
       //HashMap<Key,Value> map = new HashMap<>();
        HashMap<Character, String> Cmap = new HashMap<>();
        HashMap<String, Character> Smap = new HashMap<>();
            
        String[] strArray = s.split(" "); // get a String Array from String s that split(" ")
        
        //if pattern length is not same with s length, return false 
        if(pattern.length() != strArray.length){
            return false; 
        }
        
        // traverse all element
        for(int i = 0; i < pattern.length(); i++){
            char curChar = pattern.charAt(i);
            String curStr = strArray[i];
            
            //If the Character value corresponds and maps the String value is not different, return false 
            if(Cmap.containsKey(curChar)){
                if(!strArray[i].equals(Cmap.get(curChar))) {
                    return false;
                }
            }
            
            //If the String value corresponds and maps the Character value is not different, return false
            if(Smap.containsKey(curStr)){
                if(Smap.get(curStr) != curChar){
                    return false;
                }
            }
            
            //if did not have the same maps Character and String in HashMap
            if(!Cmap.containsKey(curChar) && !Smap.containsKey(curStr)){
                //create the new maps and put them in HashMap
                Cmap.put(curChar, curStr);
                Smap.put(curStr, curChar);
            }
        }
       
        return true;
    }
}
