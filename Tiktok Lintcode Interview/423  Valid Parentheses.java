/*Lintcode 423 · Valid Parentheses

Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.

Example 1:

Input: "([)]"
Output: False

Example 2:

Input: "()[]{}"
Output: True
挑战
Use O(n) time, n is the number of parentheses.

*/

public class Solution {
    /**
     * @param s: A string
     * @return: whether the string is a valid parentheses
     */
    public boolean isValidParentheses(String s) {
        // write your code here
        ArrayDeque<Character> stack = new ArrayDeque<>();

        //把String s中的字母全部提取出来
        for(char c : s.toCharArray()){
            if(c == '('){
                stack.push(')');
            } else if(c == '{'){
                stack.push('}');
            } else if(c == '['){
                stack.push(']');
            } else if(stack.isEmpty() || c != stack.pop()){
                return false; //如果之前没有对应的符合，return false
            }
        }

        return stack.isEmpty();
    }
}
