/* Leetcode 50. Pow(x, n)

Implement pow(x, n), which calculates x raised to the power n (i.e., xn).

Example 1:

Input: x = 2.00000, n = 10
Output: 1024.00000

Example 2:

Input: x = 2.10000, n = 3
Output: 9.26100

Example 3:

Input: x = 2.00000, n = -2
Output: 0.25000
Explanation: 2-2 = 1/22 = 1/4 = 0.25
 

Constraints:

-100.0 < x < 100.0
-2^31 <= n <= 2^31-1
n is an integer.
Either x is not zero or n > 0.
-10^4 <= xn <= 10^4
*/

class Solution {
    public double myPow(double x, int n) {
        double res = 0.0;
        if(n > 0){ //幂大于0时，计算乘积
            res = pow(x, n); //call the pow() function to calculate
        } else if(n < 0){ //幂小于0时，计算除数
            // x = 2.00000, n = -2 ==> 2^-2 = 1/2^2 = 1/4 = 0.25
            res = 1.0 / pow(x, n); // call the pow() function to calculate
        } if(n == 0){ // 0次幂的任何数都为1
            return 1.0;
        }
        
        return res;
    }
    
    /* Recursive Process: 
    如果n为双数，可以化简成：2^n = 2^(n/2) * 2^(n/2)
        ==> 2^2 = 2^1 * 2^1 => (2^0 * 2^0 * 2) * (2^0 * 2^0 * 2) = (1 * 1 * 2) * (1 * 1 * 2) = 2 * 2 = 4
    如果n为单数，可以化简成：2^n = 2^(n/2) * 2^(n/2) * 2
        ==> 2^3 = 2^1 * 2^1 * 2 => (2^0 * 2^0 * 2) * (2^0 * 2^0 * 2) * 2 = (1 * 1 * 2) * (1 * 1 * 2)  * 2 = 8
    */
    public static double pow(double x, int n){
        if(n == 0){
            return 1;
        }
        
        double y = pow(x, n / 2);

        if(n % 2 == 0){
            return y * y;
        } else{
            return y * y * x;
        }
    }
}
