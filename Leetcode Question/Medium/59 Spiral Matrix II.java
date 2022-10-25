/*Leetcode 59. Spiral Matrix II

Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.

Example 1:
Input: n = 3
Output: [[1,2,3],[8,9,4],[7,6,5]]

Example 2:
Input: n = 1
Output: [[1]]
 
Constraints:
1 <= n <= 20
*/

//most smilar with Leetcode 54 Spiral Matrix
//just need change to add the number into every spaces from int[][] array

//Time: O(n^2)  Space: O(1)
class Solution {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        
        /* (rowStart, colStart)    ->   (rowStart,colEnd)
              |                             |
            (rowEnd, colStart)    <-     (rowEnd,colEnd)
        */
        
        int rowStart = 0;
        int rowEnd = n - 1;
        int colStart = 0;
        int colEnd = n - 1;
        int num = 1; //use number to add in every space
        int step = 0;
        
        while(rowStart <= rowEnd && colStart <= colEnd){
            //1.move right
            if(step == 0){
                for(int colCur = colStart; colCur <= colEnd; colCur++){
                    res[rowStart][colCur] = num++;
                }
                rowStart++; //Record visited one row, the next step will not visit this row again
            }
            
            //2. move down
            if(step == 1){
                for(int rowCur = rowStart; rowCur <= rowEnd; rowCur++){
                    res[rowCur][colEnd] = num++;
                }
                colEnd--;//Record visited one column, the next step will not visit this column again
            }
            
            //3.move left
            if(step == 2){
                for(int colCur = colEnd; colCur >= colStart; colCur--){
                    res[rowEnd][colCur] = num++;
                }
                rowEnd--; //Record visited one column, the next step will not visit this column again
            }
            
            //4.move up
            if(step == 3){
                for(int rowCur = rowEnd; rowCur >= rowStart; rowCur--){
                    res[rowCur][colStart] = num++;
                }
                colStart++;//Record visited one row, the next step will not visit this row again
            }
            
            step = (step + 1) % 4; //get the next step
        }
        
        return res;
    }
}
