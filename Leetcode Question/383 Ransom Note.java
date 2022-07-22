/* Leetcode 383. Ransom Note
Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.
Each letter in magazine can only be used once in ransomNote.

Example 1:
Input: ransomNote = "a", magazine = "b"
Output: false

Example 2:
Input: ransomNote = "aa", magazine = "ab"
Output: false

Example 3:
Input: ransomNote = "aa", magazine = "aab"
Output: true
*/

class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        //HashMap <Key, Value>
        HashMap<Character, Integer> map1 = new HashMap<>(); // Hash
        
        //Put all the element from magazine String into HashMap
        for(char c : magazine.toCharArray()){
            map1.put(c, map1.getOrDefault(c, 0) + 1);
        }
        
        //check whether ransomNote can be constructed by using the letters from magazine
        for(char r : ransomNote.toCharArray()){
            // get the “ransomNote” character's correspond quantity from Hashmap()
            int number = map1.getOrDefault(r, 0); //getOrDefault function() will return 0, if not have correspond character
            
            if(number == 0){ // if correspond quantity is 0, it means there can not constructed it
                return false;
            } else{
                map1.put(r, number - 1); 
                // if number is not 0, just number - 1 and continue to check whether have same quantity
            }
        }
        
        return true; // finally, return true => ransomNote can be constructed by using the letters from magazine
    }
}
