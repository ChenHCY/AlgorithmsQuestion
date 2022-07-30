/* Leetcode 482. License Key Formatting
You are given a license key represented as a string s that consists of only alphanumeric characters and dashes. 
The string is separated into n + 1 groups by n dashes. You are also given an integer k.

We want to reformat the string s such that each group contains exactly k characters, except for the first group, 
which could be shorter than k but still must contain at least one character. Furthermore, there must be a dash 
inserted between two groups, and you should convert all lowercase letters to uppercase.Return the reformatted 
license key.

 
Example 1:
Input: s = "5F3Z-2e-9-w", k = 4
Output: "5F3Z-2E9W"
Explanation: The string s has been split into two parts, each part has 4 characters.
Note that the two extra dashes are not needed and can be removed.

Example 2:
Input: s = "2-5g-3-J", k = 2
Output: "2-5G-3J"
Explanation: The string s has been split into three parts, each part has 2 characters except the first part as it could be shorter as mentioned above.
 

Constraints:
1 <= s.length <= 105
s consists of English letters, digits, and dashes '-'.
1 <= k <= 104
*/

class Solution {
    public String licenseKeyFormatting(String s, int k) {
        StringBuilder sb = new StringBuilder();
        String S = s.toUpperCase();
        int j = 0;
        int i = S.length() - 1;
        
        //Traverse every element of string s start at the back to front
        while(i >= 0){ 
            char c = S.charAt(i); 
            if(c == '-'){ //when the element is '-'
                i--; //just contiue move
                continue; // beacuse we need reformat the string s such that each group contains exactly k characters, 
            }
            //StringBuilder insert() function was used for add the element at specified position
            sb.insert(0, c); // add the character of String S into StringBuilder
            j++; // Record the number of characters in each group
            if(j == k){ //when the number of characters of the group equals K (each group contains exactly k characters)
                sb.insert(0, '-'); //add '-' at the beginning of StringBuilder
                j = 0; //reset the j back to 0, It use for Record the number of characters in next group 
            }
            i--; //continue to traverse next element
        }
        
        //Check and delete if there have '-' at the beginning of StringBuilder
        if(sb.length() > 0 && sb.charAt(0) == '-'){
            sb.deleteCharAt(0); 
        }
        return sb.toString(); //StringBuilder.toString() was used for as String to output
    }
}
