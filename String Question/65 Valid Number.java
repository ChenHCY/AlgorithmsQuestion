/* 65. Valid Number

A valid number can be split up into these components (in order):
    1. A decimal number or an integer.
    2. (Optional) An 'e' or 'E', followed by an integer.

A decimal number can be split up into these components (in order):

1. (Optional) A sign character (either '+' or '-').
2. One of the following formats:
    1. One or more digits, followed by a dot '.'.
    2. One or more digits, followed by a dot '.', followed by one or more digits.
    3. A dot '.', followed by one or more digits.

An integer can be split up into these components (in order):

    1. (Optional) A sign character (either '+' or '-').
    2. One or more digits.

For example, all the following are valid numbers: ["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"], 
while the following are not valid numbers: ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"].

Given a string s, return true if s is a valid number.

Example 1:
Input: s = "0"
Output: true

Example 2:
Input: s = "e"
Output: false

Example 3:
Input: s = "."
Output: false
 
Constraints:
1 <= s.length <= 20
s consists of only English letters (both uppercase and lowercase), digits (0-9), plus '+', minus '-', or dot '.'.
*/
/*
==> 设定numSeen，dotSeen和eSeen三种boolean变量，分别代表是否出现数字、点和E
然后遍历目标字符串 String s
1.判断是否属于数字的0~9区间
2.遇到点的时候，判断前面是否有点或者E，都需要return false
3.遇到E的时候，判断前面数字是否合理，是否有E，并把numSeen置为false，防止E后无数字
4.遇到-+的时候，判断是否是第一个，or 是否在E后面，都不满足则return false
5.其他情况都为false
==> 最后返回numSeen的结果即可 ==> numSeen也记录这个字符串S 是不是有效数字
 */
//Time: O(n)   Space: O(n)
class Solution {
    public boolean isNumber(String s) {
        if(s == null || s.length() == 0){
            return false;
        }

        //设定numSeen，dotSeen和eSeen三种boolean变量，分别代表是否出现数字、点和E
        boolean numSeen = false;
        boolean dotSeen = false;
        boolean eSeen = false;
        char[] charArr = s.trim().toCharArray(); //把String s 改成 char[] 的形式

        for(int i = 0; i < charArr.length; i++){
            // 1. 判断是否属于数字的0~9区间
            if(charArr[i] >= '0' && charArr[i] <= '9'){
                numSeen = true;
            } else if(charArr[i] == '.'){ //遇到点的时候，判断前面是否有点或者E
                if(dotSeen || eSeen){
                    return false;
                }
                dotSeen = true;
            } else if(charArr[i] == 'e' || charArr[i] == 'E'){ //遇到E的时候，判断前面数字是否合理，是否有E
                if(!numSeen || eSeen){
                    return false;
                }
                eSeen = true;
                numSeen = false; //并把numSeen改为false，因为E后也需要存在数字
            } else if(charArr[i] == '+' || charArr[i] == '-'){ //遇到-+的时候，判断是否是第一个，or 是否在E后面
                if(i != 0 && charArr[i - 1] != 'e' && charArr[i - 1] != 'E'){
                    return false;
                }
            } else { //其他所有情况 都是false
                return false;
            }
        }
        return numSeen;
    }
}
