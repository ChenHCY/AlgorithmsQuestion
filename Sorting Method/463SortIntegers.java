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
        if(A == null || A.length == 0){
            return;
        }
        quickSort(A, 0, A.length-1);
    }

    private void quickSort(int[] A, int start, int end) {
        if(start >= end) {
            return;
        }
        int left = start;
        int right = end;
        int piovt = A[(left + right) / 2];
        
        while(left <= right){
            while(A[left] < piovt) {
                left++;
            }

            while(A[right] > piovt){
                right--;
            }

            if(left <= right){
                int temp = A[left];
                A[left] = A[right];
                A[right] = temp;
                left++;
                right--;
            }
        }

        quickSort(A, start, right);
        quickSort(A, left, end);
    }
}
