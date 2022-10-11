/** 460 · Find K Closest Elements
Description
Given target, a non-negative integer k and an integer array A sorted in ascending order, 
find the k closest numbers to target in A, sorted in ascending order by the difference between 
the number and target. Otherwise, sorted in ascending order by number if the difference is same.

Example
Example 1:

Input: A = [1, 2, 3], target = 2, k = 3
Output: [2, 1, 3]
Example 2:

Input: A = [1, 4, 6, 8], target = 3, k = 3
Output: [4, 1, 6]
**/

public class Solution {
    public int[] kClosestNumbers(int[] A, int target, int k) {
        // write your code here
        int left = 0;
        int right = A.length - 1;
        int[] result = new int[k];

        while(left + 1 < right){
            int mid = (left + right) / 2;
            if(target < A[mid]){
                right = mid;
            } else if (target > A[mid]) {
                left = mid;
            } else {
                right = mid;
            }
        }

        result = findKthClosestNumber(A, target, k, left, right);

        return result; 
    }

    //write a small function to output a Kth closest list
    private int[] findKthClosestNumber(int[] A, int target, int k, int left, int right){
        int start = left;
        int end = right;
        int[] result = new int [k];
        int n = 0; // use a number to make sure the result list size
        int i = 0; // every move a number to left need -1;
        int j = 0; // every move a number to right need +1;

        while (n < k){
            if(start - i >= 0 && end + j <= A.length - 1){
                if(Math.abs(target - A[start - i]) <= Math.abs(A[end + j] - target)){
                    result[n] = A[start - i];
                    i++;
                    n++;
                } else{
                    result[n] = A[end + j];
                    j++;
                    n++;
                }
            } else if (start - i >= 0){
                result[n] = A[start - i];
                i++;
                n++;
            } else {
                result[n] = A[end + j];
                j++;
                n++;
            }
        }
        return result;
    }
}
}
