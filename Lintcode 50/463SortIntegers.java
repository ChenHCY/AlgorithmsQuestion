/* 463 · Sort Integers
Description
Given an integer array, sort it in ascending order. Use selection sort, bubble sort, insertion sort or any O(n2) algorithm.

Example 1:
	Input:  [3, 2, 1, 4, 5]
	Output: [1, 2, 3, 4, 5]
	
	Explanation: 
	return the sorted array.

Example 2:
	Input:  [1, 1, 2, 1, 1]
	Output: [1, 1, 1, 1, 2]
	
	Explanation: 
	return the sorted array.
*/

public class Solution {
    /**
     * @param A: an integer array
     * @return: nothing
     */
    public void sortIntegers(int[] A) {
        // write your code here
        // used 2 For-loop way to sort the list

        for(int i = 1;i < A.length; i++)
        {           
            for(int j = 0;j < A.length-i; j++)
            {           
                if(A[j] > A[j+1])
                {
                    int temp = A[j];
                    A[j] = A[j+1];
                    A[j+1] = temp;
                }
            }           
        }
    }
}
