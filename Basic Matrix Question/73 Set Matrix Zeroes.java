/* Leetcode 73. Set Matrix Zeroes
Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.

You must do it in place.

Example 1:
Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
Output: [[1,0,1],[0,0,0],[1,0,1]]

Example 2:
Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 

Constraints:
m == matrix.length
n == matrix[0].length
1 <= m, n <= 200
-231 <= matrix[i][j] <= 231 - 1
*/

//Time: O(m * n)   Space: O (m + n)
class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        //used HashSet to saved all the 0's space row and column value
        HashSet<Integer> row = new HashSet<>();
        HashSet<Integer> colum = new HashSet<>();
        
        //check every space and marked it
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                //add the row value and cloum value of element is 0
                if(matrix[i][j] == 0){
                    row.add(i);
                    colum.add(j);
                }
            }
        }
        
        //find the all marked 0 spaces, and change all row and column to 0's
       for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(row.contains(i) || colum.contains(j)){
                    matrix[i][j] = 0;
                }
            }
       }
    }
}
