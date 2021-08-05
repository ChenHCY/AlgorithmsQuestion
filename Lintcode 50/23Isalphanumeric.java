/* 23 · Is alphanumeric
Description
Given a character c,return ture if it is a letter or a number,otherwise return false.

Example 1:
Input: c = '1'
Output: true
Explanation:
'1'belongs to the number.
*/

public class Solution {
    /**
     * @param c: A character.
     * @return: The character is alphanumeric or not.
     */
    public boolean isAlphanumeric(char c) {
        // write your code here
        if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))
        {
            return true;
        } else {
            return false;
        }
    }
}
