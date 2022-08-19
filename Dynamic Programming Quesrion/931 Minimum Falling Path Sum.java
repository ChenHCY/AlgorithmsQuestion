/* Leetcode 931. Minimum Falling Path Sum
Given an n x n array of integers matrix, return the minimum sum of any falling path through matrix.
A falling path starts at any element in the first row and chooses the element in the next row that is either directly below or diagonally left/right. 
Specifically, the next element from position (row, col) will be (row + 1, col - 1), (row + 1, col), or (row + 1, col + 1).

Example 1:
Input: matrix = [[2,1,3],[6,5,4],[7,8,9]]
Output: 13
Explanation: There are two falling paths with a minimum sum as shown.

Example 2:
Input: matrix = [[-19,57],[-40,-5]]
Output: -59
Explanation: The falling path with a minimum sum is shown.
 

Constraints:
n == matrix.length == matrix[i].length
1 <= n <= 100
-100 <= matrix[i][j] <= 100
*/

class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        //Dp Method
        int[][] dp = new int[n][n];
        
        //i is means rows && j is means column
        //Dp state: dp[i][j] = matrix[row][colu]
        //Dp function: dp[i][j] = Math.min(dp[i-1][j-1], dp[i-1][j], dp[i-1][j+1])
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i == 0){
                    dp[i][j] = matrix[i][j]; //the value of every element in first row 
                } else{
                    int prevRow = dp[i-1][j]; //the Numbers in the middle vertical direction (every element have it)
                    //Traverse and count every element sum path start at second row
                    //"j-1 >= 0 and j+1< n" can contain all position cases of each row element
                    if(j - 1 >= 0){
                        prevRow = Math.min(dp[i-1][j-1], prevRow);
                    }
                    if(j + 1 < n){
                        prevRow = Math.min(dp[i-1][j+1], prevRow);
                    }
                    //add the current row value into every path
                    dp[i][j] = prevRow + matrix[i][j];
                }
            }
        }
        
        //compare and count the minimum sum of any falling path through matrix
        int minValue = Integer.MAX_VALUE;
        for(int k = 0; k < n; k++){
            minValue = Math.min(minValue, dp[n-1][k]); //through compare the last row dp value
        }
        
        return minValue;
    }
}
