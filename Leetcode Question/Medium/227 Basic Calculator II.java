/* Leetcode 227. Basic Calculator II

Given a string s which represents an expression, evaluate this expression and return its value. 
The integer division should truncate toward zero.
You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].
Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().

 
Example 1:
Input: s = "3+2*2"
Output: 7

Example 2:
Input: s = " 3/2 "
Output: 1

Example 3:
Input: s = " 3+5 / 2 "
Output: 5
 

Constraints:
1 <= s.length <= 3 * 105
s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
s represents a valid expression.
All the integers in the expression are non-negative integers in the range [0, 231 - 1].
The answer is guaranteed to fit in a 32-bit integer.
*/

class Solution {
    public int calculate(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        
        int res = 0;
        int num = 0; //the number from String S
        char numberSign = '+'; // the number sign
        Deque<Integer> stack = new LinkedList<>(); //Use Stack<> to store and calculate number result
        
        for(int i = 0; i < s.length(); i++){ 
            //isDigit() is used for determines whether a specified character is a digit or not. 
            if(Character.isDigit(s.charAt(i))){ 
                num = num * 10 + s.charAt(i) - '0';  //change the string format into integer
            }  ;//Need to get correct whole number value, the prev digit is tens

            //when the element is sign, not number
            if((!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ') || i == s.length() - 1){
                if(numberSign == '+'){  //when get the sign from String s is '+'
                    stack.push(num);
                } 
                if(numberSign == '-'){ //when get the sign from String s is '-'
                    stack.push(-num);
                }
                if(numberSign == '*'){ // Multiplication and division have computational priority
                    stack.push(stack.pop() * num); //pop() is return first element from stack / push() is add the number in the front of stack
                }
                if(numberSign == '/'){ // Multiplication and division have computational priority
                    stack.push(stack.pop() / num); //pop() is return first element from stack / push() is add the number in the front of stack
                }
                //So both of "num" and "numberSign" need get new value (num=0, numberSign is curr sign)
                numberSign = s.charAt(i);
                num = 0;
            }
        }
        
        //get the reslut sum number from stack
        for(int j : stack){
            res += j;
        }
        
        return res;
    }
}
