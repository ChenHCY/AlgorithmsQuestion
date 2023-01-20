/* Leetcode 130. Surrounded Regions

Given an m x n matrix board containing 'X' and 'O', capture all regions that are 4-directionally surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

Example 1:
Input: 
board = 
[["X","X","X","X"],
["X","O","O","X"],
["X","X","O","X"],
["X","O","X","X"]]

Output:
board = 
[["X","X","X","X"],
["X","X","X","X"],
["X","X","X","X"],
["X","O","X","X"]]

Explanation: Notice that an 'O' should not be flipped if:
- It is on the border, or
- It is adjacent to an 'O' that should not be flipped.
The bottom 'O' is on the border, so it is not flipped.
The other three 'O' form a surrounded region, so they are flipped.

Example 2:
Input: board = [["X"]]
Output: [["X"]]
 
Constraints:
m == board.length
n == board[i].length
1 <= m, n <= 200
board[i][j] is 'X' or 'O'
*/

/*
思路： Used the DFS Method：

1- 从四条边界的所有'O'开始向内部检查

2- 然后我们只需要将这些 'O' 变成其他符号，例如 '#'

3- 然后将按 dfs 顺序检查所有与边界'O'相邻的'O'，然后把变成其他符号，例如 '#' 

4- 最后for-loop 我们将找到剩余的'O'并将这些'O'变为'X'。

5- 然后将那些特殊符号'#'再次变为 'O'，因为它们不用修改为 'X'

*/
class Solution {
    public void solve(char[][] board) {
        //base case
        if(board == null || board.length == 0 || board[0].length == 0){
            return;
        }
        int row = board.length - 1;
        int column = board[0].length - 1;

        //search start at the first and last row
        for(int i = 0; i <= column; i++){
            if(board[0][i] == 'O'){
                dfs(board, 0, i);
            }

            if(board[row][i] == 'O'){
                dfs(board, row, i);
            }
        }

         //search start at the first and last column
        for(int j = 0; j <= row; j++){
            if(board[j][0] == 'O'){
                dfs(board, j, 0);
            }

            if(board[j][column] == 'O'){
                dfs(board, j, column);
            }
        }

        //after all the searched, check all the spaces from board matrix and change the value
        for(int x = 0; x <= row; x++){
            for(int y = 0; y <= column; y++){
                if(board[x][y] == 'O'){
                    board[x][y] = 'X';
                } else if(board[x][y] == '#'){
                    board[x][y] = 'O';
                } 
            }
        }
    }

    //dfs function
    public static void dfs(char[][] board, int x, int y){
        //exit condition
        if(x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] != 'O'){
            return;
        }

         // Mark the 0 should not need to flipped.
        board[x][y] = '#';
        //move to next space and check
        dfs(board, x + 1, y); //Down
        dfs(board, x - 1, y); //Up
        dfs(board, x, y + 1); //Right
        dfs(board, x, y - 1); //Left
    }
}
