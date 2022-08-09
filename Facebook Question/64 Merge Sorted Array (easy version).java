/*Lintcode 64 Merge Sorted Array (easy version)

Description
Given two sorted integer arrays A and B, merge B into A as one sorted array.
Modify array A in-place to merge array B into the back of array A.

Example 1:
Input:
A = [1,2,3]
m = 3
B = [4,5]
n = 2

Output:
[1,2,3,4,5]

Explanation:
After merge, A will be filled as [1,2,3,4,5]
Example 2:

Input:
A = [1,2,5]
m = 3
B = [3,4]
n = 2

Output:
[1,2,3,4,5]

Explanation:
After merge, A will be filled as [1,2,3,4,5]
*/
public class Solution {
    /*
     * @param A: sorted integer array A which has m elements, but size of A is m+n
     * @param m: An integer
     * @param B: sorted integer array B which has n elements
     * @param n: An integer
     * @return: nothing
     */
    public void mergeSortedArray(int[] A, int m, int[] B, int n) {
        // write your code here
        for(int i = 0; i != n; i++){
            A[m + i] = B[i];
        }

        Arrays.sort(A);
    }
}
