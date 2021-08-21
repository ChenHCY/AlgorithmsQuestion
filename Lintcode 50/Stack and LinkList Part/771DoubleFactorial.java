/* 771 · Double Factorial
Description
Given a number n, return the double factorial of the number.In mathematics, 
the product of all the integers from 1 up to some non-negative integer n that 
have the same parity (odd or even) as n is called the double factorial.
Example1 :
Input: n = 5
Output: 15
Explanation:
5!! = 5 * 3 * 1 = 15
Example2:
Input: n = 6
Output: 48
Explanation:
6!! = 6 * 4 * 2 = 48
*/

public class Solution {
  
    long caluclate(int n) {
        if(n <= 1){
            return 1;
        }
        return caluclate(n-2) * n;
    }
  
    public long doubleFactorial(int n) {
        // Write your code here
        return caluclate(n);
    }
}
