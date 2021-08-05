/* 214 · Max of Array
Description
Given an array of float numbers. Return the max value of them.

Example
Example 1:

Input:  [1.0, 2.1, -3.3]
Output: 2.1	
Explanation: return the Max one.
Example 2:

Input:  [1.0, 1.0, -3.3]
Output: 1.0	
Explanation: return the Max one.
*/

public class Solution {
    /**
     * @param A: An integer
     * @return: a float number
     */
    public float maxOfArray(float[] A) {
        // write your code here
        //Float.MAX_VALUE is a very small postive number
        float result = -Float.MAX_VALUE;

        for(int i = 0; i < A.length; i++){
            if(A[i] >= result)
            {
                result = A[i];
            }
        }
        return result;
    }
}
