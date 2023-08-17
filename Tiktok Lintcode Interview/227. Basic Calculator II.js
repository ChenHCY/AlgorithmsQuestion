/*Leetcode 227. Basic Calculator II

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

1 <= s.length <= 3 * 10^5
s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
s represents a valid expression.
All the integers in the expression are non-negative integers in the range [0, 231 - 1].
The answer is guaranteed to fit in a 32-bit integer.

*/

/**
 * @param {string} s
 * @return {number}
 */
var calculate = function(s) {
    s = s.trim();
    let stack = [];
    let preSign = '+';
    let num = 0;  //the number from String S

    for(let i = 0; i < s.length; i++){
        let curr = s[i];
        //如果遍历到数字，则提取这个数字值
        if(/\d/.test(curr)){
            num = num * 10 + (s[i] - '0');
        }

        if(isNaN(Number(curr)) || i === s.length - 1){
            switch(preSign) {
                case '+':
                    stack.push(num);
                    break;
                case '-':
                    stack.push(-num);
                    break;
                case '*':
                    stack.push(stack.pop() * num);
                    break;
                default:
                    //`|0`: 除法的结果然后与 0 进行按位或运算。此运算有效地截断结果的小数部分，仅保留整数部分。
                    stack.push(stack.pop() / num | 0);
                    break;
            }

            preSign = s[i];
            num = 0; // renew the number value;
        }
    }
    //reduce() 求arr总和
    return stack.reduce((a, b) => {return a + b}, 0);
};
