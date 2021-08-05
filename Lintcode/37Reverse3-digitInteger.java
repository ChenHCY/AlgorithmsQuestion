/** 37 · Reverse 3-digit Integer
Description
Reverse a 3-digit integer.
Example 1:
Input:
number = 123
Output:
321
Explanation:
Reverse the number.

Example 2:
Input:
number = 900
Output:
9
Explanation:
Reverse the number.
*/

public class Solution {
    /**
     * @param number: A 3-digit number.
     * @return: Reversed number.
     */
    public int reverseInteger(int number) {
        // write your code here
        int num1 = number % 10;
        int num2 = (number / 10) % 10;
        int num3 = (number / 10) / 10 % 10;

        return num3 + num2*10 + num1*100;
    }
}
