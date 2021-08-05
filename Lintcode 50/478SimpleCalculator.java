/* 478 · Simple Calculator
Description
Given two integers a and b, an operator, choices:  +, -, *, /

Example 1:
Input:
a = 1
b = 2
operator = +
Output: 3
Explanation: return the result of : 1 + 2.

Example 2:
Input:
a = 10
b = 20
operator = *
Output: 200
Explanation: return the result of: 10 * 20.

Example 3:
Input:
a = 3
b = 2
operator = /
Output: 1
Explanation: return the result of: 3 / 2.
*/

public class Calculator {
    /**
     * @param a: An integer
     * @param operator: A character, +, -, *, /.
     * @param b: An integer
     * @return: The result
     */
    public int calculate(int a, char operator, int b) {
        // write your code here
        int result = 0;

        if(operator == '+'){
            result = a + b;
        } else if (operator == '-'){
            result = a - b;
        } else if (operator == '*'){
            result = a * b;
        } else if (operator == '/'){
            result = a / b;
        }

        return result;
    }
}

