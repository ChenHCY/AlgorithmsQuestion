/* 20 Valid Parentheses
Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
An input string is valid if:
Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
 
Example 1:
Input: s = "()"
Output: true

Example 2:
Input: s = "()[]{}"
Output: true

Example 3:
Input: s = "(]"
Output: false
*/

class Solution {
    public boolean isValid(String s) {
        Stack<Character> result = new Stack<>();
        
        for(char c : s.toCharArray()){
            if(c == '('){
                result.push(')');
            } else if(c == '['){
                result.push(']');
            } else if(c == '{'){
                result.push('}');
            } else if(result.isEmpty() || result.pop() != c){ //when the c is not same with result, should return "false"
                return false;
            }
        }
        
        return result.isEmpty();  // Use this sentence to judge only single characters string, should return "false"
                                 //if c is all same as the result, the result should be empty stack. So return "True"
    }
}
