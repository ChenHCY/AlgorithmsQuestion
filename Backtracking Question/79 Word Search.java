/* Leetcode 79. Word Search
Given an m x n grid of characters board and a string word, return true if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where adjacent cells 
are horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example 1:
Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
Output: true

Example 2:
Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
Output: true

Example 3:
Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
Output: false
 

Constraints:
m == board.length
n = board[i].length
1 <= m, n <= 6
1 <= word.length <= 15
board and word consists of only lowercase and uppercase English letters.
*/
//Time: Time Complexity: O(N* 3^L) where N is the number of cells in the board and L is the length of the word to be matched.
//Space: O(L) where L is the length of the word to be matched.
class Solution {
    public boolean exist(char[][] board, String word) {
        boolean res; // the res
        char[] c = word.toCharArray(); //Change the String word to be Char[] Array
        boolean[][] visited = new boolean[board.length][board[0].length];
      
        //travser all the element from board
        for(int i = 0; i < board.length; i++){ // i is row
            for(int j = 0; j < board[0].length; j++){ // j is column
                //call backtracking method
                res = helper(board, i, j, 0, c, visited);
                if(res){ 
                    return true;
                }
            }
        }
        return false;
    }
    
    //backtracking method
    public boolean helper(char[][] board, int i, int j, int offset, char[] c, boolean[][] visited){
        //exit backtracking condition
        if(offset == c.length){
            return true;
        }
        
        //if out the board line
        if(i < 0 || j < 0 || i >= board.length || j >= board[0].length){
            return false;
        }
        
        //if this element was visted before
        if(visited[i][j]){
            return false;
        }
        
         boolean answer;
        
        //basic solution: board[i][j] = c[offset]
        if(board[i][j] == c[offset]){
            visited[i][j] = true; // store visited this board[][] element
            //move to next board space and check up, down, left, right 4 ways ==>
            answer = helper(board, i, j+1, offset+1, c, visited) 
                          || helper(board, i, j-1, offset+1, c, visited)
                          || helper(board, i+1, j, offset+1, c, visited) 
                          || helper(board, i-1, j, offset+1, c, visited);
            visited[i][j] = false; //back to previous level
        } else{ //can not find the character from String words
            return false;
        }
        
        return answer;
    }
}
