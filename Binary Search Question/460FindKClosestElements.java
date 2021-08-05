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

public int[] kClosestNumbers(int[] A, int target, int k) {
        // write your code here
        int left = 0;
        int right = A.length - 1;

        while(left + 1 < right){
            int mid = (left + right) / 2;
            if(A[mid] <= target){
                left = mid;
            } else {
                right = mid;
            } 
        }

        //move the point to left and right, and compare them
        int result[] = new int[k]; 
        int start = left, end = right;
        for(int i = 0; i < result.length; i++) {
            if(start >= 0 && end < A.length - 1){
                if(Math.abs(A[start]-target) <= Math.abs(A[end]-target)) {
                    result[i] = A[start];
                    start--;
                } else {
                    result[i] = A[end];
                    end++;
                }
            } else if(start >= 0 && end >= A.length - 1){
                result[i] = A[start];
                start--;
            } else {
                result[i] = A[end];
                end++;
            }
        }
        return result;
    }
}
