/* 145 · Lowercase to Uppercase
Description
Convert a lowercase character to uppercase.

Example 1:
Input: 'a'
Output: 'A'

Example 2:
Input: 'b'
Output: 'B'
*/

public class Solution {
    /**
     * @param character: a character
     * @return: a character
     */
    public char lowercaseToUppercase(char character) {
        // write your code here
    
        if(character >= 'a' && character <= 'z'){
            character = Character.toUpperCase(character);
        } 

        return character;
    }
}
