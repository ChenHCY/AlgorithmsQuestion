/* 10036. Minimum Moves to Capture The Queen

There is a 1-indexed 8 x 8 chessboard containing 3 pieces.

You are given 6 integers a, b, c, d, e, and f where:

· (a, b) denotes the position of the white rook.
· (c, d) denotes the position of the white bishop.
· (e, f) denotes the position of the black queen.

Given that you can only move the white pieces, return the minimum number of moves required to capture the black queen.

Note that:

· Rooks can move any number of squares either vertically or horizontally, but cannot jump over other pieces.
· Bishops can move any number of squares diagonally, but cannot jump over other pieces.
· A rook or a bishop can capture the queen if it is located in a square that they can move to.
· The queen does not move.
 

Example 1:
Input: a = 1, b = 1, c = 8, d = 8, e = 2, f = 3
Output: 2
Explanation: We can capture the black queen in two moves by moving the white rook to (1, 3) then to (2, 3).
It is impossible to capture the black queen in less than two moves since it is not being attacked by any of the pieces at the beginning.

Example 2:
Input: a = 5, b = 3, c = 3, d = 4, e = 5, f = 2
Output: 1
Explanation: We can capture the black queen in a single move by doing one of the following: 
- Move the white rook to (5, 2).
- Move the white bishop to (5, 2).
 
Constraints:
1 <= a, b, c, d, e, f <= 8
No two pieces are on the same square.
*/
//Time: O(1)  Space: O(1)
class Solution {
    // 白车rook 可以在水平和垂直方向，随意移动格子
    // 白主教bishop可以延对角线的方向随意移动
    // 所以一共三种情况：
    // 1. 白车rook可以直接抓到：检查bishop是否会阻挡，会：2次移动，不会：1次移动
    // 2. 白主教bishop可以直接抓到：黑皇后和白主教bishop在同一条对角线上，并且检查白车rook是否阻挡，会：2次移动，不会：1次移动
    // 3. 白车 rook 和 白主教bishop都不能直接抓到：因为车rook 可以在水平和垂直方向，随意移动格子，所以最多只需要两次就能抓到 
    public int minMovesToCaptureTheQueen(int a, int b, int c, int d, int e, int f) {
        //(a, b) 表示白车的位置。
        int[] rook = {a, b};
        
        //(c, d) 表示白主教的位置。
        int[] bishop = {c, d};
        
        //(e, f) 表示黑后的位置
        int[] queen = {e, f};

        // 1. 白车rook可以直接抓到：检查bishop是否会阻挡，会：2次移动，不会：1次移动
        if((rook[0] == queen[0]) || (rook[1] == queen[1])){
            // 检查bishop是否会阻挡, 如果阻挡，则移走bishop, 再使用rook直接抓到  ==> 一共两步
            if((rook[0] == queen[0]) && (rook[0] == bishop[0]) && ((bishop[1] - rook[1]) * (bishop[1] - queen[1]) < 0)){
                return 2;
            }
            // 检查bishop是否会阻挡, 如果阻挡，则移走bishop, 再使用rook直接抓到 ==> 一共两步
            if((rook[1] == queen[1]) && (rook[1] == bishop[1]) && ((bishop[0] - rook[0]) * (bishop[0] - queen[0]) < 0)){
                return 2;
            }

            return 1;
        }

        // 2. 白主教bishop可以直接抓到：黑皇后和白主教bishop在同一条对角线上，并且检查白车rook是否阻挡，会：2次移动，不会：1次移动
        if(Math.abs(bishop[0] - queen[0]) == Math.abs(bishop[1] - queen[1])){
            // 检查 rook 是否会阻挡, 如果阻挡，则移走rook, 再使用bishop直接抓到 ==> 一共两步
            if((Math.abs(bishop[0] - rook[0]) == Math.abs(bishop[1] - rook[1])) && ((rook[1] - bishop[1]) * (rook[1] - queen[1]) < 0)){
                return 2;
            }
            return 1;
        }

        
       //3.  白车 rook 和 白主教bishop都不能直接抓到：因为车rook 可以在水平和垂直方向，随意移动格子，所以最多只需要两次就能抓到 
       return 2;
    }
}
