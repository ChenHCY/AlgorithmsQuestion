/*Leetcode 74. Search a 2D Matrix
Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.

Example 1:
Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
Output: true

Example 2:
Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
Output: false
 
Constraints:
m == matrix.length
n == matrix[i].length
1 <= m, n <= 100
-104 <= matrix[i][j], target <= 104
*/

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        //Binary Serach Method
        int m = matrix.length;
        int n = matrix[0].length;
        
        int left = 0; //left pointer
        int right = m * n - 1; //right pointer
        
        while(left <= right){
            //Find the mid position and mid Value ==> Used Binary Serach Method
            int mid = left + (right - left) / 2;
            int midValue = matrix[mid / n][mid % n]; //**Important
            
            if(midValue == target){
                return true;
            } else if (midValue < target){
                left = mid + 1;
            } else{
                right = mid - 1;
            }
        }
        
        return false;
    }
}
