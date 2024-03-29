/*Leetcode 7. Reverse Integer

Given a signed 32-bit integer x, return x with its digits reversed. 
If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.

Assume the environment does not allow you to store 64-bit integers (signed or unsigned).


Example 1:
Input: x = 123
Output: 321

Example 2:
Input: x = -123
Output: -321

Example 3:
Input: x = 120
Output: 21
 

Constraints:
-231 <= x <= 231 - 1
*/

class Solution {
    public int reverse(int x) {
        long res = 0;
        //traverse every number digit from x and reversed it
        while(x != 0){
            res = res * 10 + x % 10; // get every digit that starts at the end of x, and reversed the number x
            x /= 10; //move to the next digit of x 
        }
        // if outside the signed 32-bit integer range [-2^31, 2^31 - 1], then return 0.
        if(res > Integer.MAX_VALUE || res < Integer.MIN_VALUE){
            return 0;
        } else{
            return (int)res; //if not, return the current result value
        }
    }
}
