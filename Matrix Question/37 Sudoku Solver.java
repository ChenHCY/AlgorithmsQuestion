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

// 思路：完成一个数独matirx的填空
// 1. 对于每一个空格，进行 1 - 9 的枚举，然后检查是否可以选择这个数字填空是满足数独要求的
// ==> 如果满足要求，return true 跳过后续步骤，继续填空
// ==> 如果不满足要求，return false 回溯当前恢复空格为'.', 继续枚举数字填入进去 直到满足要求
class Solution {
    public void solveSudoku(char[][] board) {
        solveSudo(board);
    }

    //dfs function: 对于每一个空格 枚举 1 - 9数字，然后看是否满足数独要求 
    public boolean solveSudo(char[][] board){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                //已经存在数字，跳过，不需要填空
                if(board[i][j] != '.'){
                    continue;
                }

                //对于每一个空格，枚举1-9，检查放置那个数字是合适的
                for(char k = '1'; k <= '9'; k++){
                    if(isValid(board, i, j, k)){ //如果得到true, 表示可以填入当前枚举的数字
                        board[i][j] = k;
                        //继续递归，移动到下一个格子
                        if(solveSudo(board)){ //如果得到true, 表示所有格子都符合要求
                            return true;
                        }
                        board[i][j] = '.'; //如果不符合要求，回溯恢复当前格子为空
                    }
                }
                return false; //如果当前格子枚举1-9都不符合要求，输出false
            }
        }
        // 遍历完没有返回false，说明找到了合适棋盘位置了
        return true;
    }

    //Check function: 检查这个数字放置之后，是不是满足数独条件的
    // 同行是否重复 | 同列是否重复 | 9宫格里是否重复
    public boolean isValid(char[][] board, int row, int column, char val){
        //检查 同行 同列 是否有重复的数字
        for(int i = 0; i < 9; i++){
            if(board[row][i] == val){
                return false;
            }

            if(board[i][column] == val){
                return false;
            }
        }

        //检查3*3区域内有没有重复的元素
        int startRow = (row / 3) * 3; 
        int startColumn = (column / 3) * 3;

        for(int i = startRow; i < startRow + 3; i++){
            for(int j = startColumn; j < startColumn + 3; j++){
                if(board[i][j] == val){
                    return false;
                }
            }
        }

        return true; //如果都没有重复的数字，表示符合数独规则
    }
}
