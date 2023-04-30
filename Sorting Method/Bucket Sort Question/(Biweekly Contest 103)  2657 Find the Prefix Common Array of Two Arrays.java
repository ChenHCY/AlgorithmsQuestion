/* Leetcode (Biweekly Contest 103)  2657 Find the Prefix Common Array of Two Arrays

You are given two 0-indexed integer permutations A and B of length n.

A prefix common array of A and B is an array C such that C[i] is equal to the count of numbers that are present at or before the index i in both A and B.

Return the prefix common array of A and B.

A sequence of n integers is called a permutation if it contains all integers from 1 to n exactly once.

Example 1:

Input: A = [1,3,2,4], B = [3,1,2,4]
Output: [0,2,3,4]
Explanation: At i = 0: no number is common, so C[0] = 0.
At i = 1: 1 and 3 are common in A and B, so C[1] = 2.
At i = 2: 1, 2, and 3 are common in A and B, so C[2] = 3.
At i = 3: 1, 2, 3, and 4 are common in A and B, so C[3] = 4.

Example 2:

Input: A = [2,3,1], B = [3,1,2]
Output: [0,1,3]
Explanation: At i = 0: no number is common, so C[0] = 0.
At i = 1: only 3 is common in A and B, so C[1] = 1.
At i = 2: 1, 2, and 3 are common in A and B, so C[2] = 3.
 

Constraints:

1 <= A.length == B.length == n <= 50
1 <= A[i], B[i] <= n
It is guaranteed that A and B are both a permutation of n integers.
*/
//Time: O(n)   Space: O(n)
class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int[] bucketSort = new int[51];
        
        int n = A.length;
        int[] res = new int[n];
        
        if(A[0] == B[0]){
            res[0] = 1;
        } else{
            res[0] = 0;
        }

        bucketSort[A[0]]++;
        bucketSort[B[0]]++;
        
        for(int i = 1; i < n; i++){
            //Used bucketSort to count the frequence of the number of A[] and B[]
            bucketSort[A[i]]++;
            bucketSort[B[i]]++;
            int count = 0;
            //检查所有数字的数量，如果出现两次，代表在A[] 和 B[]中都存在这个数字
            for(int j = 0; j <= 50; j++){
                if(bucketSort[j] == 2){
                    count++;
                }
            }
            res[i] = count;
        }
        
        return res;
    }
}
