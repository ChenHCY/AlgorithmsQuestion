/* Leetcode 48. Rotate Image
You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).

You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. 
DO NOT allocate another 2D matrix and do the rotation.

Example 1:
Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [[7,4,1],[8,5,2],[9,6,3]]

Example 2:
Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 
Constraints:
n == matrix.length == matrix[i].length
1 <= n <= 20
-1000 <= matrix[i][j] <= 1000
*/
//Time: O(m * n)  Space: O(1)
//Because this is an exchange question, so only need to care about the half-part point [][] position
class Solution {
    public void rotate(int[][] matrix) {
        /* 1 2 3       1 4 7        7 4 1
           4 5 6  ==>  2 5 8   ==>  8 5 2
           7 8 9       3 6 9        9 6 3
        */
        // The first exchange takes 1-5-9 as the axis change ==> [row][column] = [column][row]
        // The Second echange takes 4-5-6 as the axis  ==> [row][column] = [row][nums[0].length - column - 1]
        
        int n = matrix.length; // the row
        int m = matrix[0].length; //the column
        int temp = 0;
        
        //the first exchange: row and column should less than length
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < m; j++){
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        
        //the second exchange: only need to exchange first column and thrid column
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m / 2; j++){
                temp = matrix[i][j];
                matrix[i][j] = matrix[i][m - 1 - j];
                matrix[i][m - 1 - j] = temp;
            }
        }
    }
}
 
