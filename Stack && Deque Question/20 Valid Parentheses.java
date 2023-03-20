/* Leetcode  20 Valid Parentheses

Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Every close bracket has a corresponding open bracket of the same type.

Example 1:

Input: s = "()"
Output: true

Example 2:

Input: s = "()[]{}"
Output: true

Example 3:

Input: s = "(]"
Output: false
 

Constraints:

1 <= s.length <= 104
s consists of parentheses only '()[]{}'.
*/

/* 思路： 此题是检查括号的关合顺序是否正确

所以遍历这个string, 每次遇见一个左括号，就把对应的右括号放入stack中，

这样stack就保存了左括号出现的顺序，stack是先进先出，所以这也是需要关闭的顺序

当我们遇见右括号的时候，就可以拿来和stack中的elmenet进行检查

如果不一样 ==》 return false

*/

class Solution {
    public boolean isValid(String s) {
        ArrayDeque<Character> result = new ArrayDeque<>();
        
        //travser every character from string s one by one
        for(char c : s.toCharArray()){
            if(c == '('){ //if c is (
                result.push(')'); //add ) into the Stack res
            } else if(c == '['){ //if c is {
                result.push(']'); //add } into the Stack res
            } else if(c == '{'){//if c is [
                result.push('}');//add ] into the Stack res
            } else if(result.isEmpty() || result.pop() != c){
                return false; //if c is not ( { [, judge whether is Valid Parentheses
            }
        }
        
        //example: '((', so need return .isEmpty() function not return true
        return result.isEmpty();
    }
}
