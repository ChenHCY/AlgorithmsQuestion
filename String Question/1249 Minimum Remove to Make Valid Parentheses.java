/*Leetcode 1249. Minimum Remove to Make Valid Parentheses

Given a string s of '(' , ')' and lowercase English characters.

Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so 
that the resulting parentheses string is valid and return any valid string.

Formally, a parentheses string is valid if and only if:

  It is the empty string, contains only lowercase characters, or
  It can be written as AB (A concatenated with B), where A and B are valid strings, or
  It can be written as (A), where A is a valid string.
  
Example 1:

Input: s = "lee(t(c)o)de)"
Output: "lee(t(c)o)de"
Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.

Example 2:

Input: s = "a)b(c)d"
Output: "ab(c)d"

Example 3:

Input: s = "))(("
Output: ""
Explanation: An empty string is also valid.
 

Constraints:
1 <= s.length <= 10^5
s[i] is either'(' , ')', or lowercase English letter.
*/
//Time: O(2*n) ==> 两个循环for-loop, n is the length of String s
//Space: O(n) ==> 开了一个array来记录String s的字母进行遍历，其中 n 是输入字符串长度。
class Solution {
    public String minRemoveToMakeValid(String s) {
        int leftParen = 0;
        int rightParen = 0;
        char[] charStr = s.toCharArray(); //把字符串更改为char array
        StringBuilder sb = new StringBuilder();

        //1. 遍历整个字符串的array, 删除无法配对的右括号
        for(char c : charStr){
            //找到左括号，记录数量
            if(c == '('){
                leftParen++;
            } 
            
            //如果遍历到右括号，记录数量，然后判断是否和左括号配对
            if(c == ')'){
                rightParen++;
                //如果前面没有左括号，或者左括号的数量小于右括号
                if(leftParen == 0 || leftParen < rightParen){ 
                    rightParen--;
                    continue;//跳过或许步骤
                }
            }
            
            sb.append(c);
        }


        //2. 在删除多余的右括号之后，再检查删除多余的左括号
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < sb.length(); i++){
            //记录左括号的数量
            if(sb.charAt(i) == '('){
                rightParen--;
                //如果超出了可以配对的数量，则都是无法进行配对的左括号，不能加入
                if(rightParen < 0){
                    continue;
                }
            }
            res.append(sb.charAt(i));
        }

        return res.toString();
    }
}
