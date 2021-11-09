/* 172. Factorial Trailing Zeroes
Given an integer n, return the number of trailing zeroes in n!.
Note that n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1.

Example 1:
Input: n = 3
Output: 0
Explanation: 3! = 6, no trailing zero.

Example 2:
Input: n = 5
Output: 1
Explanation: 5! = 120, one trailing zero.

Example 3:
Input: n = 0
Output: 0
 
Constraints:
0 <= n <= 104
*/

class Solution {
    public int trailingZeroes(int n) {
        if(n < 5){
            return 0;
        }
        
        int result = 0;
        while(n != 0){
            result += n / 5;
            n /= 5;
        }
        
        return result;
    }
}

// Lintcode: 

public class Solution {
    /**
     * @param n: A long integer
     * @return: An integer, denote the number of trailing zeros in n!
     */
    public long trailingZeros(long n) {
        // write your code here
        long result = 0;
        while(n != 0){
            result += n / 5;
            n /= 5;
        }
       return result;
    }
}


