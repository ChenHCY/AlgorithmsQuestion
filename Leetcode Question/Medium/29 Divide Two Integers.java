/* Leetcode 29. Divide Two Integers
Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.

The integer division should truncate toward zero, which means losing its fractional part. For example, 8.345 would 
be truncated to 8, and -2.7335 would be truncated to -2.

Return the quotient after dividing dividend by divisor.

Note: Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range: [−2^31, 2^31 − 1]. 

For this problem, 
if the quotient is strictly greater than 2^31 - 1(Integer.MAX_VALUE), then return 2^31 - 1
if the quotient is strictly less than -2^31(Integer.MIN_VALUE), then return -2^31.
*/

class Solution {
    public int divide(int dividend, int divisor) {
        // dividend / divisor = result
        int sign = 1; //use for postive or negative
        
        //if sign of dividend and divisor was not same, sign of result should be negative
        if((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)){
            sign = -1; //so change sign to be -1
        }
        
        //remove the sign of dividend and divisor, and change to be long format
        long ldividend = Math.abs((long)dividend);
        long ldivisor = Math.abs((long)divisor);
        
        //Decision tree pruning：
        //if dividend is small than divisor, Like 1 / 3 ==> just return 0
        //when dividend is 0 ordivisor is 0, the result also is 0 
        if(ldividend < ldivisor || ldividend == 0){
            return 0;
        }
        
        long lres = divide(ldividend, ldivisor); //call the small function divide
        int res = 0;
        
        //the Out of bounds problem：
        // if the quotient is strictly greater than 2^31 - 1(Integer.MAX_VALUE), then return 2^31 - 1
        // if the quotient is strictly less than -2^31(Integer.MIN_VALUE), then return -2^31.
        if(lres > Integer.MAX_VALUE){
            res = (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        } else{
            res = (int)(sign * lres); //change the long format to be integer format 
        }
        
        return res;
    }
    
    //small function to do the divide through use multiplication, division, and mod operator.
    public static long divide(long dividend, long divisor){
        //dividend can not less than divisor
        if(dividend < divisor){
            return 0;
        }
        long sum = divisor;
        long mulitiple = 1;
        // Use "<=" will decrease the space time to be less than O(logn)
        while((sum + sum) <= dividend){
            sum = sum + sum;
            mulitiple += mulitiple;
        }
        
        return mulitiple + divide(dividend - sum, divisor); //continue the recursive 
    }
}
