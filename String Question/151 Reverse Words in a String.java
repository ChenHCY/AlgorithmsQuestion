/*Leetcode 151 Reverse Words in a String

Given an input string s, reverse the order of the words.

A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.

Return a string of the words in reverse order concatenated by a single space.

Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.

Example 1:

Input: s = "the sky is blue"
Output: "blue is sky the"

Example 2:

Input: s = "  hello world  "
Output: "world hello"
Explanation: Your reversed string should not contain leading or trailing spaces.

Example 3:

Input: s = "a good   example"
Output: "example good a"
Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
 

Constraints:

1 <= s.length <= 10^4
s contains English letters (upper-case and lower-case), digits, and spaces ' '.
There is at least one word in s.
*/

class Solution {
    public String reverseWords(String s) {
        if(s.length() == 0 || s == " "){
            return "";
        }

        s.trim(); //消除string s的前后空格

        StringBuilder sb = new StringBuilder(); //用来组成新的String
        String[] strArr = s.split(" "); //根据空格， 把String s 划分成 String array

        for(int i = strArr.length - 1; i >= 0; i--){
            //如果字符串为空，不加入到最后结果
            if(strArr[i] == ""){
                continue;
            }
            //如果字符串i不为空，加入到最后结果
            sb.append(" " + strArr[i]);
        }

        return sb.toString().trim(); //消除前后空格
    }
}
