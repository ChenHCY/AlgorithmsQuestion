/*Lintcode 141 Sqrt(x)

Description
Implement int sqrt(int x).
Compute and return the square root of x.

Example

Example 1:
	Input:  0
	Output: 0


Example 2:
	Input:  3
	Output: 1
	
	Explanation:
	return the largest integer y that y*y <= x. 
	
Example 3:
	Input:  4
	Output: 2
*/

public class Solution {
    /**
     * @param x: An integer
     * @return: The sqrt of x
     */
    public int sqrt(int x) {
        // write your code here
        int left = 0;
        int right = x;
        int res = 0;

        while(left <= right){
            int mid = left + (right - left) / 2;
            long temp = (long)mid * mid;

            if(temp <= x){
                res = (int)mid;
                left = mid + 1;
            } else if(temp > x){
                right = mid - 1;
            } 
        }

        return res;
    }
}
