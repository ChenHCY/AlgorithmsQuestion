/* Leetcode 258. Add Digits

Given an integer num, repeatedly add all its digits until the result has only one digit, and return it.

Example 1:
Input: num = 38
Output: 2
Explanation: The process is
38 --> 3 + 8 --> 11
11 --> 1 + 1 --> 2 
Since 2 has only one digit, return it.

Example 2:
Input: num = 0
Output: 0
 

Constraints:

0 <= num <= 231 - 1
 
Follow up: Could you do it without any loop/recursion in O(1) runtime?
*/

class Solution {
    public int addDigits(int num) {
        if(num < 10){
            return num;
        } 
        
        // Therefore the 'digital root' for All multiples of 9 is always 9. 
        if(num % 9 == 0){
            return 9;
        }
        
        // for any number that isn't 0 and isn't divisible by 9, the 'digital root' will always n % 9 for a given number n.
        return num % 9;
    }
}
