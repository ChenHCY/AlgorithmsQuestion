/* Leetcode 2520. Count the Digits That Divide a Number

Given an integer num, return the number of digits in num that divide num.
An integer val divides nums if nums % val == 0.

Example 1:

Input: num = 7
Output: 1
Explanation: 7 divides itself, hence the answer is 1.

Example 2:

Input: num = 121
Output: 2
Explanation: 121 is divisible by 1, but not 2. Since 1 occurs twice as a digit, we return 2.

Example 3:

Input: num = 1248
Output: 4
Explanation: 1248 is divisible by all of its digits, hence the answer is 4.
 

Constraints:

1 <= num <= 109
num does not contain 0 as one of its digits.
*/

class Solution {
    public int countDigits(int num) {
        int res = 0;
        int number = num;
        
        //while-loop to traver and divisible every digits
        while(number != 0){
            //get the digits from num one by one
            int digits = number % 10;
            number /= 10;
            
            //find An integer val divides nums if nums % val == 0.
            if(digits > 0 && num % digits == 0){
                res += 1;
            }
            
        }
        
        return res;
    }
}
