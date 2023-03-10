/* Leetcode 150. Evaluate Reverse Polish Notation

You are given an array of strings tokens that represents an arithmetic expression in a Reverse Polish Notation.

Evaluate the expression. Return an integer that represents the value of the expression.

Note that:

The valid operators are '+', '-', '*', and '/'.
Each operand may be an integer or another expression.
The division between two integers always truncates toward zero.
There will not be any division by zero.
The input represents a valid arithmetic expression in a reverse polish notation.
The answer and all the intermediate calculations can be represented in a 32-bit integer.
 

Example 1:

Input: tokens = ["2","1","+","3","*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9


Example 2:

Input: tokens = ["4","13","5","/","+"]
Output: 6
Explanation: (4 + (13 / 5)) = 6


Example 3:

Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
Output: 22
Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22
 

Constraints:

1 <= tokens.length <= 104
tokens[i] is either an operator: "+", "-", "*", or "/", or an integer in the range [-200, 200].
*/

/* 思路：
此题遵循逆波兰表达式，把String[] array中的数字和符号 转化为 一个数学公式 并且得出结果

*** 逆波兰表达式的特点是：没有括号，运算符总是放在和它相关的操作数之后。因此，逆波兰表达式也称后缀表达式。

逆波兰表达式严格遵循「从左到右」的运算。

所以计算逆波兰表达式的值时，使用一个栈存储数字，从左到右遍历逆波兰表达式，

然后进行如下操作：

1. 如果遇到数字，则将操作数入栈 ==》 stack()的特点：First in Last Out ==> 先进后出

2. 如果遇到运算符，则将Stack()中顶端的两个数字出栈，其中先出栈的是右数，后出栈的是左数，

再使用运算符对两个操作数进行运算，将运算得到的新操作数入栈。

==》 整个逆波兰表达式遍历完毕之后，栈内只有一个元素，该元素即为逆波兰表达式的值。

*/

class Solution {
    public int evalRPN(String[] tokens) {
        //stack()的特点：First in Last Out ==> 先进后出
        Deque<Integer> stack = new ArrayDeque<>();

        for(int i = 0; i < tokens.length; i++){
            String str = tokens[i];
            if(isNumber(str)){
                stack.push(Integer.parseInt(str));
            } else{
              //stack()的特点：First in Last Out ==> 先进后出
              //所以先出栈的是右数，后出栈的是左数，
                int num2 = stack.pop();
                int num1 = stack.pop();
                if(str.equals("+")){
                    stack.push(num1 + num2);
                } else if(str.equals("-")){
                    stack.push(num1 - num2);
                } else if(str.equals("*")){
                    stack.push(num1 * num2);
                } else if(str.equals("/")){
                    stack.push(num1 / num2);
                }
            }
        }
        return stack.pop();
    }

    //check the curr str whether is not number
    public boolean isNumber(String str){
        if(str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")){
            return false;
        }
        return true;
    }
}
