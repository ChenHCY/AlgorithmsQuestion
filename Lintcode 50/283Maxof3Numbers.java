/* 283 · Max of 3 Numbers

Description
Given 3 integers, return the max of them.
Example
Example 1:
	Input:  num1 = 1, num2 = 9, num3 = 0
	Output: 9
	Explanation: 
	return the Max of them.

Example 2:
	Input:  num1 = 1, num2 = 2, num3 = 3
	Output: 3
	Explanation: 
	return the Max of them.
*/

public class Solution {
    /**
     * @param num1: An integer
     * @param num2: An integer
     * @param num3: An integer
     * @return: an interger
     */
    public int maxOfThreeNumbers(int num1, int num2, int num3) {
        // write your code here
        int result = 0;

        if(num1 >= num2){
            if(num1 >= num3){
                result = num1;
            } else{
                result = num3;
            }
        } else {
            if (num2 > num3){
                result = num2;
            } else {
                result = num3;
            }
        }

        return result;
    }
}
