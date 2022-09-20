/* Leetcode 36. Valid Sudoku
Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

Each row must contain the digits 1-9 without repetition.
Each column must contain the digits 1-9 without repetition.
Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.

Note:
A Sudoku board (partially filled) could be valid but is not necessarily solvable.
Only the filled cells need to be validated according to the mentioned rules.

Example 1:
Input: board = 
[["5","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
Output: true

Example 2:
Input: board = 
[["8","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
Output: false
Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
 

Constraints:
board.length == 9
board[i].length == 9
board[i][j] is a digit 1-9 or '.'.
*/

class Solution {
    public boolean isValidSudoku(char[][] board) {
        //traverse all all the numbers element from 9 x 9 board[][]
        //board[i][j]  ==> first [] is rwo, second [] is column
        for(int i = 0; i < board.length; i++){
            HashSet<Character> row = new HashSet<>(); // row
            HashSet<Character> colu = new HashSet<>(); // column
            HashSet<Character> boxes = new HashSet<>(); // every 3 x 3 sub-boxes
            
            for(int j = 0; j < board[0].length; j++){
                //Check every row whether have the repetition number 
                //row = 0, column = 0 - 8
                if(!row.add(board[i][j]) && board[i][j] !='.'){
                    return false;
                }
                
                //Check every column whether have the repetition number 
                //row = 0 - 8, column = 0
                if(!colu.add(board[j][i]) && board[j][i] !='.'){
                    return false;
                }
                
                //Check every 3 x 3 boxes whether have the repetition number
                int rowIndex = 3 * (i / 3);
                int colIndex = 3 * (i % 3);
                
                //Check Process:
                /*       i: 0 1 2 | 3 4 5 | 6 7 8       j: 0 1 2 | 3 4 5 | 6 7 8
                  rowIndex: 0 0 0 | 3 3 3 | 6 6 6     j/3: 0 0 0 | 1 1 1 | 2 2 2  for use in the row
                  colIndex: 0 3 6 | 0 3 6 | 0 3 6     j%3: 0 1 2 | 0 1 2 | 0 1 2  for use in the column
                  
                  i = 0 j = 0 - 8: ==> row: 0 1(j=3) 2(j=6)  column = colIndex(0) + 0 || 1 || 2 (0,0) (0,1) (0,2) ... (2, 2)
                  i = 1 j = 0 - 8: ==> row: 0 1(j=3) 2(j=6)  column = colIndex(3) + 0 || 1 || 2 (0,3) (0,4) (0,5) ... (2, 5)
                  i = 2 j = 0 - 8: ==> row: 0 1(j=3) 2(j=6)  column = colIndex(6) + 0 || 1 || 2 (0,6) (0,7) (0,8) ... (2, 8)
                  
                  i = 3 j = 0 - 8: ==> row: 3 4(j=3) 5(j=6)  column = colIndex(0) + 0 || 1 || 2 (3,0) (3,1) (3,2) ... (5, 2)
                  i = 4 j = 0 - 8: ==> row: 3 4(j=3) 5(j=6)  column = colIndex(3) + 0 || 1 || 2 (3,3) (3,4) (3,5) ... (5, 5)
                  i = 5 j = 0 - 8: ==> row: 3 4(j=3) 5(j=6)  column = colIndex(6) + 0 || 1 || 2 (3,6) (3,7) (3,8) ... (5, 8)
                  
                  i = 6 j = 0 - 8: ==> row: 6 7(j=3) 8(j=6)  column = colIndex(0) + 0 || 1 || 2 (6,0) (6,1) (6,2) ... (8, 2)
                  i = 7 j = 0 - 8: ==> row: 6 7(j=3) 8(j=6)  column = colIndex(3) + 0 || 1 || 2 (6,3) (6,4) (6,5) ... (8, 5)
                  i = 8 j = 0 - 8: ==> row: 6 7(j=3) 8(j=6)  column = colIndex(6) + 0 || 1 || 2 (6,6) (6,7) (6,8) ... (8, 8)
                */
                
                 //Check every 3 x 3 boxes whether have the repetition number
                if(!boxes.add(board[rowIndex + j / 3][colIndex + j % 3]) && board[rowIndex + j / 3][colIndex + j % 3] != '.'){
                    return false;
                }
            }
        }
        
        return true;
    }
}
