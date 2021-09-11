/* 353 · Largest letter
Description
Given a string S, find the largest alphabetic character, whose both uppercase and lowercase appear in S. The uppercase character should be returned. 
If there is no such character, return "NO".

Example

Input: S = "admeDCAB"
Output: "D"

Input: S = "adme"
Output: "NO"
*/

public class Solution {
    /**
     * @param s: a string
     * @return: a string
     */
    public String largestLetter(String s) {
        // write your code here
        char[] result = s.toCharArray();
        HashSet<Character> hashset = new HashSet<>();
        for(char c : result){
            hashset.add(c);
        }

        Character maxC = '0';
        for(Character c : hashset){
            if('a' <= c && c <= 'z'){
                char large = Character.toUpperCase(c);
                if(hashset.contains(large) && large > maxC){
                    maxC = large;
                }
            }
        }
        if(maxC != '0'){
            return maxC.toString();
        }

        return "NO";
    }
}
