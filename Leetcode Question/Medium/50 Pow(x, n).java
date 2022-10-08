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
-231 <= n <= 231-1
n is an integer.
-104 <= xn <= 104
*/

//Binary Searach Idea, Time: O(logm) Space: O(n)
class Solution {
    public double myPow(double x, int n) {
        double res = 0.0;
        if(n > 0){
            res = pow(x, n); //call the pow() function to calculate
        } else if(n < 0){
            // x = 2.00000, n = -2 ==> 2^-2 = 1/2^2 = 1/4 = 0.25
            res = 1.0 / pow(x, n); // call the pow() function to calculate
        } if(n == 0){
            return 1.0;
        }
        
        return res;
    }
    
    /* Recursive Process:
    2^2 = 2^1 * 2^1 = (2^0 * 2^0 * 2) * (2^0 * 2^0 * 2) = (1 * 1 * 2) * (1 * 1 * 2) = 2 * 2 = 4
    2^3 = 2^1 * 2^1 * 2 = (2^0 * 2^0 * 2) * (2^0 * 2^0 * 2) * 2 = (1 * 1 * 2) * (1 * 1 * 2)  * 2 = 8
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
