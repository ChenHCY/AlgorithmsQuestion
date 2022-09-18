/* Leetcode 32. Longest Valid Parentheses
Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

Example 1:
Input: s = "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()".

Example 2:
Input: s = ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()".

Example 3:
Input: s = ""
Output: 0
 
Constraints:
0 <= s.length <= 3 * 104
s[i] is '(', or ')'.
*/

//Time: O(n)  Space: O(n)

class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>(); //use for save the left parenthese "(" 
        int res = 0; //the final result
        int start = -1; // save the start position for every "()" 
        
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                stack.push(i); //save the the left Parentheses into stack list
            }else{
                if(stack.isEmpty()){
                    //Because need always keep the start position in the prev of the valid position
                    start = i; //So if there is not "(" in the before, just mover start into current i position
                }else{
                    stack.pop(); //pop the one left Parentheses from the stack list
                    if(stack.isEmpty()){
                        // the start position always in the prev of this current Valid position
                        res = Math.max(res, i - start); 
                    } else{
                        //this case is used for there are ready have two left parentheses in the stack 
                        //For example: ( ( ) )  the result is 4  => Need to count the Valid Parentheses in the inner layer first
                        res = Math.max(res, i - stack.peek());
                    }
                }
            }
        }
        
        return res;
    }
}
