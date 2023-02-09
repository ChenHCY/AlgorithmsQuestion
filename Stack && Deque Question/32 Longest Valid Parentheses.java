/* Leetcode 32. Longest Valid Parentheses

Given a string containing just the characters '(' and ')', return the length of the longest valid (well-formed) parentheses 
substring

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

/* 思路: 使用stack记录左括号的下标位置

因为题意是找到有效括号的最长长度

所以使用for-loop遍历String s, 

1. 然后当遇到左括号的时候，记录下标的位置

2. 当遇见右括号的时候，

==》首先，检查stack里是否存在左括号， 

如果没有（stack为空） ==》表示这个右括号之前都没有左括号，绝对无法组成有效的括号，移动start pointer到这个当前右括号的位置

如果存在 （stack不为空） ==》 当前右括号之前是有左括号的，
 
    ==》删除stack里面左括号，表示形成了有效括号组， stack.pop(); 
          
          ==》然后开始计算长度，因为已经删除了一个，我们计算长度的时候，应该是 i的下标位置（当前的右括号）- 左括号之前一位的位置
          
          ==》所以这里要判断，当前右括号之前有几个左括号，
          
          1. 如果只有一个时，  res = Math.max(res, i - start); 

          2. 当有两个以上时，  res = Math.max(res, i - stack.peek());
*/

class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        int start = -1; // save the start position for every "()" 
        
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                stack.push(i); //save the the left Parentheses into stack list
            }else{
                if(stack.isEmpty()){
                    //Because need always keep the start position in the prev of the valid position
                    start = i; //if there is not "(" in the before, just mover start into current i position
                }else{
                    stack.pop(); //pop the left Parentheses from the stack list, let next Parentheses can use
                    if(stack.isEmpty()){
                        // the start position always in the prev of this current Valid position
                        res = Math.max(res, i - start); 
                    } else{
                        //this case is used for there are ready have two left parentheses in the stack 
                        //For example: (())            the result is 4
                        res = Math.max(res, i - stack.peek());
                    }
                }
            }
        }
        
        return res;
    }
}
