/* Leetcode 54. Spiral Matrix
Given an m x n matrix, return all elements of the matrix in spiral order.

Example 1:
Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,3,6,9,8,7,4,5]

Example 2:
Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]

Constraints:
m == matrix.length
n == matrix[i].length
1 <= m, n <= 10
-100 <= matrix[i][j] <= 100
*/

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int row = matrix.length;
        int column = matrix[0].length;
      
        /* (rowStart, colStart)    ->   (rowStart,colEnd)
              |                             |
            (rowEnd, colStart)    <-     (rowEnd,colEnd)
        */
        
        int rowStart = 0;
        int colStart = 0;
        int colEnd = column - 1;
        int rowEnd = row - 1;
        int step = 0; //declare a integer to save the steps 
        
        while(rowStart <= rowEnd && colStart <= colEnd){
            //1.move right
            if(step == 0){
                for(int colCur = colStart; colCur <= colEnd; colCur++){
                    res.add(matrix[rowStart][colCur]);
                }
                rowStart++; //Record visited one row, the next step will not visit this row again
            }
            
            //2. move down
            if(step == 1){
                for(int rowCur = rowStart; rowCur <= rowEnd; rowCur++){
                    res.add(matrix[rowCur][colEnd]);
                }
                colEnd--;//Record visited one column, the next step will not visit this column again
            }
            
            //3.move left
            if(step == 2){
                for(int colCur = colEnd; colCur >= colStart; colCur--){
                    res.add(matrix[rowEnd][colCur]);
                }
                rowEnd--; //Record visited one column, the next step will not visit this column again
            }
            
            //4.move up
            if(step == 3){
                for(int rowCur = rowEnd; rowCur >= rowStart; rowCur--){
                    res.add(matrix[rowCur][colStart]);
                }
                colStart++;//Record visited one row, the next step will not visit this row again
            }
            
            step = (step + 1) % 4; //get the next step
            
        }
        
        return res;
    }
}
