/* 766 · Leap Year
Description
Determine whether year n is a leap year.return true if n is a leap year.

Example 1:
Input : n = 2008
Output : true

Example 2:
Input : n = 2018
Output : false
*/

public class Solution {
    /**
     * @param n: a number represent year
     * @return: whether year n is a leap year.
     */
    public boolean isLeapYear(int n) {
        // write your code here
        if((n % 4 == 0) && (n % 100 != 0 || n % 400 == 0))
        {
            return true;
        } else {
            return false;
        }
    }
}
