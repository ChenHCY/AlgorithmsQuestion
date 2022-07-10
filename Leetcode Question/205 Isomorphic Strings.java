/*Leetcode 205. Isomorphic Strings
Given two strings s and t, determine if they are isomorphic.
Two strings s and t are isomorphic if the characters in s can be replaced to get t.
All occurrences of a character must be replaced with another character while preserving the order of characters. 
No two characters may map to the same character, but a character may map to itself.


Example 1:
Input: s = "egg", t = "add"
Output: true

Example 2:
Input: s = "foo", t = "bar"
Output: false

Example 3:
Input: s = "paper", t = "title"
Output: true
 
Constraints:

1 <= s.length <= 5 * 104
t.length == s.length
s and t consist of any valid ascii character.
*/
class Solution {
    public boolean isIsomorphic(String s, String t) {
        int[] mapS = new int[256];
        int[] mapT = new int[256];
        
        // This method assigns the specified data type value to each element of the specified range of the specified array.
        Arrays.fill(mapS, -1);
        Arrays.fill(mapT, -1); // Make all elements of mapS[] and mapT[] equals -1
        
        for(int i = 0; i < s.length(); i++){
            //Get the every character from two Strings
            char charS = s.charAt(i);
            char charT = t.charAt(i);
            
            // Build mapping exists for the two String dictionaries
            if(mapS[charS] == -1 && mapT[charT] == -1){
                mapS[charS] = charT; 
                mapT[charT] = charS; 
            }
            
            // if it doesn't have mapping exist in the two Strings and match in either of the dictionaries or both ===> return false
            if(!(mapS[charS] == charT &&  mapT[charT] == charS)){
                return false;
            }
        }
        
        return true; // if have mapping exist in the two Strings ==> return true (isomorphic)
    }
}
