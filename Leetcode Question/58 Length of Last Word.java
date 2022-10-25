/* 58. Length of Last Word

Given a string s consisting of some words separated by some number of spaces, return the length of the last word in the string.
A word is a maximal substring consisting of non-space characters only.

Example 1:
Input: s = "Hello World"
Output: 5
Explanation: The last word is "World" with length 5.

Example 2:
Input: s = "   fly me   to   the moon  "
Output: 4
Explanation: The last word is "moon" with length 4.

Example 3:
Input: s = "luffy is still joyboy"
Output: 6
Explanation: The last word is "joyboy" with length 6.
*/

class Solution {
    public int lengthOfLastWord(String s) {
        int res = 0;
        
        //traversa all the element start at last character in String s
        for(int i = s.length() - 1; i >= 0; i--){
            //When meet the space and have gotten the length of the last word, end the for-loop
            if(res != 0 && s.charAt(i) == ' '){
                break;
            } 
            
            //when it meet the spaces, do not need to add length + 1
            if(s.charAt(i) == ' '){
                res += 0;
            } else{ //when it meet character, add the length + 1
                res += 1;
            }
        }
        
        return res; //return the result
    }
}
