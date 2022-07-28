/* Leetcode 224. Basic Calculator
Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation.

Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().

Example 1:
Input: s = "1 + 1"
Output: 2

Example 2:
Input: s = " 2-1 + 2 "
Output: 3

Example 3:
Input: s = "(1+(4+5+2)-3)+(6+8)"
Output: 23
 

Constraints:
1 <= s.length <= 3 * 105
s consists of digits, '+', '-', '(', ')', and ' '.
s represents a valid expression.
'+' is not used as a unary operation (i.e., "+1" and "+(2 + 3)" is invalid).
'-' could be used as a unary operation (i.e., "-1" and "-(2 + 3)" is valid).
There will be no two consecutive operators in the input.
Every number and running calculation will fit in a signed 32-bit integer.
*/

class Solution {
    public int calculate(String s) {
        int res = 0;
        int numberSign = 1; // the number sign
        Stack<Integer> stack = new Stack<Integer>(); //Use Stack<> to storing and distinguishing between numbers inside and outside parentheses'()'
        
        for(int i = 0; i < s.length(); i++){
            if(Character.isDigit(s.charAt(i))){ //isDigit() is used for determines whether a specified character is a digit or not. 
                int sum = s.charAt(i) - '0'; //change the string format into integer
                while(i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))){ //get the every whole number 
                    sum = sum * 10 + s.charAt(i + 1) - '0';//Need to get correct whole number value, the prev digit is tens
                    i++;//move i-pointer into next digit
                }
                res += sum * numberSign; //need care this current number is negative or positive
            } else if(s.charAt(i) == '+'){ //when get the sign from String s is '+'
                numberSign = 1;
            } else if(s.charAt(i) == '-'){ //when get the sign from String s is '-'
                numberSign = -1;
            } else if(s.charAt(i) == '('){ //The parentheses'()' have higher priority
                stack.push(res); // push(E element) method is used to push an element into the Stack. 
                stack.push(numberSign);
                res = 0; // So both of "res" and "numberSign" have to be reset to their starting values (res=, numberSign=1)
                numberSign = 1; //and then start calculating the sum inside the parentheses.
            } else if(s.charAt(i) == ')'){
                res = res * stack.pop() + stack.pop();
            } // The pop() function is used to remove or 'pop' an element from the top of the stack(newest or the topmost element in the stack).
              // so first "stack.pop()" is number Sign, second is the number before parentheses'()'
        }
        return res;
   }
}
