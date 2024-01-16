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
// Time: O(1): 因为长度 宽度 格子都是确定为 9 ==> 所以时间复杂度为 O(9 * 9) = O(81) => O(1)
// Space: O(1): 因为长度 宽度 格子都是确定为 9 ==> 所以空间复杂度为 O(9 * 9) = O(81) => O(1)
class Solution {
    // 思路：类似于bucketSort[][] 统计每一行 每一列 每一个3*3区域内有没有重复数字
    public boolean isValidSudoku(char[][] board) {
        int[][] row = new int[9][9];
        int[][] column = new int[9][9];
        int[][][] box = new int[9][9][9];

        //开始遍历整个matrix
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                char c = board[i][j];
                
                if(c != '.'){ //如果当前格子不为空，则需要判断是不是重复的
                  
                    int val = c - '0' - 1; //board[i][j] is a digit 1-9 or '.', 但我们申明的空间是0-8, 所以储存时需要-1对应
                  
                    //分别储存进row column 和 box对应的bucketSort，统计有没有重复的数字
                    row[i][val]++; 
                    column[j][val]++;
                    box[i / 3][j / 3][val]++;

                    //统计有没有重复的数字
                    if(row[i][val] > 1 || column[j][val] > 1 || box[i / 3][j / 3][val] > 1){
                        return false; //如果存在重复，表示不符合数独要求
                    }
                }
            }
        }

        return true;
    }
}
