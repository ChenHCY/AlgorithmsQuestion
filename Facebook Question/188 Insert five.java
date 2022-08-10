/* Lintcode 188 · Insert five
Description
Given a number, insert a 5 at any position of the number to make the number largest after insertion.

Example

Example 1:
Input:  a = 234
Output: 5234
*/

public class Solution {
    /**
     * @param a: A number
     * @return: Returns the maximum number after insertion
     */
    public int insertFive(int a) {
        // write your code here
        String str = String.valueOf(a);
        int i = 0;
        String res = "";

        if (a >= 0) { //when a is postive, we should insert 5 into when the next element is small or equal 5 
            while(i < str.length() && str.charAt(i) >= '5'){
                i++;
            }
        } 
        
        else { //when a is negative, we should insert 5 into when the next element is large or equal to 5 
            while(i < str.length() && str.charAt(i) <= '5'){
                i++;
            }
        } 

        res = str.substring(0, i) + '5' + str.substring(i); // insert 5 into string str(Integer a)
        return Integer.parseInt(res); // change string to be Integer
    }
}
