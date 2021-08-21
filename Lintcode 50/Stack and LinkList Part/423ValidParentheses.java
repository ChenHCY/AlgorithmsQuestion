/* 423 · Valid Parentheses
Description
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.

Example 1:
Input: "([)]"
Output: False

Example 2:
Input: "()[]{}"
Output: True

Challenge
Use O(n) time, n is the number of parentheses.
*/

public class Solution {
    /**
     * @param s: A string
     * @return: whether the string is a valid parentheses
     */
    public boolean isValidParentheses(String s) {
        // write your code here
        Stack<Character> result = new Stack<>();

        for(char c : s.toCharArray()){
            if(c == '('){
                result.push(')');
            } else if (c == '{') {
                result.push('}');
            } else if (c == '['){
                result.push(']');
            } else if(result.isEmpty() || result.pop() != c){
                //Java中的Java.util.Stack.pop()方法用于从堆栈中弹出元素。 该元素从堆栈顶部弹出，并从堆栈顶部移除
                // pop() is for move the first character from the stack.
                return false;
            }
        }
        return result.isEmpty();
        // 但是在Java中，isEmpty()可以判断一个顺序容器里面是否有元素，
        // 如果有的话返回一个Boolean类型的值false，否则返回true。
    }
}
