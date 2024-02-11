/* 3033. Modify the Matrix

Given a 0-indexed m x n integer matrix matrix, create a new 0-indexed matrix called answer. 
Make answer equal to matrix, then replace each element with the value -1 with the maximum element in its respective column.

Return the matrix answer.

Example 1:
Input: matrix = [[1,2,-1],[4,-1,6],[7,8,9]]
Output: [[1,2,9],[4,8,6],[7,8,9]]
Explanation: The diagram above shows the elements that are changed (in blue).
- We replace the value in the cell [1][1] with the maximum value in the column 1, that is 8.
- We replace the value in the cell [0][2] with the maximum value in the column 2, that is 9.

Example 2:
Input: matrix = [[3,-1],[5,2]]
Output: [[3,2],[5,2]]
Explanation: The diagram above shows the elements that are changed (in blue).
 
Constraints:
m == matrix.length
n == matrix[i].length
2 <= m, n <= 50
-1 <= matrix[i][j] <= 100
The input is generated such that each column contains at least one non-negative integer.
*/

// Time: O(m * n)   Space: O(n)
class Solution {
    public int[][] modifiedMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        int[][] res = new int[m][n];
        
        //1: find the all the max value of each column
        int[] maxColumn = new int[n];
        for(int i = 0; i < n; i++){
            int currMax = matrix[0][i];
            for(int j = 1; j < m; j++){
                currMax = Math.max(currMax, matrix[j][i]);
            }
            maxColumn[i] = currMax;
        }
        
        //2. travser all the element, if find the value is -1; add the current maxColumn[j] into res matrix
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == -1){
                    res[i][j] = maxColumn[j];
                } else{
                    res[i][j] = matrix[i][j];
                }
            }
        }
        
        return res;
    }
}
