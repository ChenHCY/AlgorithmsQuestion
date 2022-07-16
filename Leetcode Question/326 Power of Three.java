/*Leetcode 326. Power of Three
Given an integer n, return true if it is a power of three. Otherwise, return false.
An integer n is a power of three, if there exists an integer x such that n == 3x.

Example 1:
Input: n = 27
Output: true

Example 2:
Input: n = 0
Output: false

Example 3:
Input: n = 9
Output: true
 

Constraints:
-231 <= n <= 231 - 1
 
Follow up: Could you solve it without loops/recursion?
*/

//Solution 1:  Used Loop and recursion:
class Solution {
    public boolean isPowerOfThree(int n) {
        if(n == 0){
            return false;
        }
        
        if(n == 1){
            return true;
        }
        
        if(n % 3 != 0){
            return false;
        }
        
        return isPowerOfThree(n/3);
    }
}

//Solution 2 solve it without loops/recursion:
class Solution {
    public boolean isPowerOfThree(int n) {
        // 3 ^ 19 = 1162261467
        // 3 ^ 19 < Integer.MAX_VALUE < 3 ^ 20
       return (n > 0 && 1162261467 % n == 0);
    }
}
