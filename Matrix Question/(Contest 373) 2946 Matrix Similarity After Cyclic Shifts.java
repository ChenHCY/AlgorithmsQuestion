/* Leetcode 2946. Matrix Similarity After Cyclic Shifts

You are given a 0-indexed m x n integer matrix mat and an integer k. You have to cyclically right shift odd indexed rows k times and cyclically left 
shift even indexed rows k times.

Return true if the initial and final matrix are exactly the same and false otherwise.

Example 1:

Input: mat = [[1,2,1,2],[5,5,5,5],[6,3,6,3]], k = 2
Output: true
Explanation:
Initially, the matrix looks like the first figure. 
Second figure represents the state of the matrix after one right and left cyclic shifts to even and odd indexed rows.
Third figure is the final state of the matrix after two cyclic shifts which is similar to the initial matrix.
Therefore, return true.

Example 2:

Input: mat = [[2,2],[2,2]], k = 3
Output: true
Explanation: As all the values are equal in the matrix, even after performing cyclic shifts the matrix will remain the same. Therefeore, we return true.

Example 3:

Input: mat = [[1,2]], k = 1
Output: false
Explanation: After one cyclic shift, mat = [[2,1]] which is not equal to the initial matrix. Therefore we return false.
 

Constraints:

1 <= mat.length <= 25
1 <= mat[i].length <= 25
1 <= mat[i][j] <= 25
1 <= k <= 50
*/

lass Solution {
    public boolean areSimilar(int[][] mat, int k) {
        int n = mat.length;
        int m = mat[0].length;
        
        // 检查奇数行，向右移动k次 是否和原matrix一样
        for(int i = 0; i < n; i+=2){
            for(int j = 0; j < m; j++){
                 //因为是cycliaclly循环的，所以要使用 % 的方法，保证不超过数组长度
                if(mat[i][j] != mat[i][(j + (k % m) + m) % m]){
                    return false;
                }
            }
        }
        
        // 检查偶数行，向左移动k次，是否和原matirx一样
        for(int i = 1; i < n - 1; i+=2){
            for(int j = m - 1; j >= 0; j--){
                //因为是cycliaclly循环的，所以要使用 % 的方法，保证不超过数组长度
                if(mat[i][j] != mat[i][(j - (k % m) + m) % m]){
                    return false;
                }
            }
        }
        
        return true;
    }
}
