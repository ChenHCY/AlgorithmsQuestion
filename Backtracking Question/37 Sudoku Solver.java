/* Leetcode 37. Sudoku Solver

Write a program to solve a Sudoku puzzle by filling the empty cells.
A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
The '.' character indicates empty cells.

Example 1:
Input: 
board = 
[["5","3",".",".","7",".",".",".","."],
["6",".",".","1","9","5",".",".","."],
[".","9","8",".",".",".",".","6","."],
["8",".",".",".","6",".",".",".","3"],
["4",".",".","8",".","3",".",".","1"],
["7",".",".",".","2",".",".",".","6"],
[".","6",".",".",".",".","2","8","."],
[".",".",".","4","1","9",".",".","5"],
[".",".",".",".","8",".",".","7","9"]]

Output: 
[["5","3","4","6","7","8","9","1","2"],
["6","7","2","1","9","5","3","4","8"],
["1","9","8","3","4","2","5","6","7"],
["8","5","9","7","6","1","4","2","3"],
["4","2","6","8","5","3","7","9","1"],
["7","1","3","9","2","4","8","5","6"],
["9","6","1","5","3","7","2","8","4"],
["2","8","7","4","1","9","6","3","5"],
["3","4","5","2","8","6","1","7","9"]]
Explanation: The input board is shown above and the only valid solution is shown below:

Constraints:
board.length == 9
board[i].length == 9
board[i][j] is a digit or '.'.
It is guaranteed that the input board has only one solution.
*/

class Solution {
    public void solveSudoku(char[][] board) {
        //Used Backtracking 
        //board[row][column], it can not have repetition number
        if(board == null || board.length == 0){
            return;
        }
        //Call the small function to check and add number
        doSolve(board);
    }
    
    //Small function to use isValid() check and put number into empty cells
    public boolean doSolve(char[][] board){
        //Traverse every cells from this 9x9 board[][]
        for(int row = 0; row < board.length; row++){
            for(int colum = 0; colum < board[0].length; colum++){
                //Check whether the current cells is empty  
                if(board[row][colum] == '.'){
                    //Use a for-loop to try digits 1-9 in every current cells
                    for(char num = '1'; num <= '9'; num++){ 
                        if(isValid(board, row, colum , num)){
                            //Check put which number 1-9, the Sudoku would still be valid
                            board[row][colum] = num; //Find request number, Then put it to current cells
                            //if success add, Move to check and put the next empty cell case
                            if(doSolve(board)){
                                return true;
                            } else{
                                board[row][colum] = '.'; //if can not add any number in the current cells, back to empty cell
                            }
                        }
                    }
                    return false; //if all the number can not add it, return false
                }
            }
        }
        return true;
    }
    
    //Small function to check whether the 9*9 board can be Valid for Sudoku rules 
    public boolean isValid(char[][] board, int row, int colum, int num){
        for(int k = 0; k < 9; k++){
            //Check whether rwo have repetition number
            if(board[k][colum] != '.' && board[k][colum] == num) 
                return false;
            //Check whether column have repetition number
            if(board[row][k] != '.' && board[row][k] == num) 
                return false;
            //Check every 3 x 3 boxes whether have the repetition number
            int rowIndex = 3 * (row / 3);
            int colIndex = 3 * (colum / 3);
            if(board[rowIndex + k / 3][colIndex + k % 3] != '.' && board[rowIndex + k / 3][colIndex + k % 3] == num){
                return false;
            }
        }
        //If all the rules can be passed, return true;
        return true;
    }   
}
