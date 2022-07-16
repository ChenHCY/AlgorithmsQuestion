/*Leetcode 342. Power of Four
Given an integer n, return true if it is a power of four. Otherwise, return false.
An integer n is a power of four, if there exists an integer x such that n == 4x.

Example 1:
Input: n = 16
Output: true

Example 2:
Input: n = 5
Output: false

Example 3:
Input: n = 1
Output: true

Constraints:
-231 <= n <= 231 - 1
 

Follow up: Could you solve it without loops/recursion?
*/

//Solution 1: Used while loop
class Solution {
    public boolean isPowerOfFour(int n) {
        if(n > 1){
            while(n % 4 == 0){
                n /= 4;
            }
        }       
        return n == 1;
    }
}

//Solution 2: solve it without loops/recursion:
class Solution {
    public boolean isPowerOfFour(int n) {
        //In here, it needs to find the number of digits in base 4 form. 
        //But in the JAVA, it did not have a generic log method to calculate in base b. 
        //for that we can use log property "Logba = Logca/Logcb".  
        //if there are no decimals %1 will be zero in such a case. it will be power of 4(true) 
        return (Math.log10(n)/Math.log10(4))%1 == 0;
        
    }
}
