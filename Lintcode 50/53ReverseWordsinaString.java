/* 53 · Reverse Words in a String

Description
Given an input string, reverse the string word by word.

Example 1:
Input:
s = "the sky is blue"
Output:
"blue is sky the"

Explanation:
return a reverse the string word by word.

Example 2:
Input:
s = "hello world"
Output:
"world hello"

Explanation:
return a reverse the string word by word.
*/

public class Solution {
    /*
     * @param s: A string
     * @return: A string
     */
    public String reverseWords(String s) {
        // write your code here
        if (s.length() == 0 || s == " ") {
            return "";
        }

        s.trim();

        StringBuffer result =  new StringBuffer();
        String[] temp = s.split(" ");

        for(int i = temp.length - 1; i >= 0; i--){
            if(temp[i].equals("")){
                continue;
            }
            result.append(" " + temp[i]);
        }
        return result.toString().trim();
    }
}
