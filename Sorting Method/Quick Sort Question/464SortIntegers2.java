/* 464 · Sort Integers II
Description
Given an integer array, sort it in ascending order in place. Use quick sort, merge sort, heap sort or any O(nlogn) algorithm.

Example1:

Input: [3, 2, 1, 4, 5], 
Output: [1, 2, 3, 4, 5].
Example2:

Input: [2, 3, 1], 
Output: [1, 2, 3].
*/

public class Solution {
    /**
     * @param A: an integer array
     * @return: nothing
     */
     
    public void sortIntegers2(int[] A) {
        // write your code here
        if(A == null || A.length == 0){
            return;
        }

        quickSort(A, 0, A.length-1);
    }

    private void quickSort(int[] A, int start, int end){
        if(start >= end){
            return;
        }
        //define let, right and find a piovt
        int left = start;
        int right = end;
        int pivot = A[start + (end - start) / 2];

        while(left <= right){
            while(left <= right && A[left] < pivot){
                left++;
            }

            while(left <= right && A[right] > pivot){
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
