/* Leetcode 240. Search a 2D Matrix II

Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.

Example 1:
Input: matrix = 
[[1,4,7,11,15],
  [2,5,8,12,19],
  [3,6,9,16,22],
  [10,13,14,17,24],
  [18,21,23,26,30]], 
target = 5

Output: true


Example 2:
Input: matrix = 
[[1,4,7,11,15],
[2,5,8,12,19],
[3,6,9,16,22],
[10,13,14,17,24],
[18,21,23,26,30]], 
target = 20

Output: false
 

Constraints:

m == matrix.length
n == matrix[i].length
1 <= n, m <= 300
-109 <= matrix[i][j] <= 109
All the integers in each row are sorted in ascending order.
All the integers in each column are sorted in ascending order.
-109 <= target <= 109
*/

/* 思路此题区别于74. Search a 2D Matrix I的点就是

这个题目的矩阵中是无法保证从左上到右下一定是递增的

所以我们无法使用Binary Search

在这题中应该使用Two pointer方法，left = 0; right = matrix[0].lnegth-1

从右上的matrix[0][matrix[0].lnegth-1]开始查找，

因为这个点的规律是统一的，同行的数都比它小，同列的数都比他大

所以：
如果target > matrix[left][right], 移动left(行数指针）
如果target < matrix[left][right], 移动right (列数指针）

最后看是否能在martix中找到Target的值
*/

//时间复杂度：O(m + n) 空间复杂度： O(1)
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        int left = 0; //row
        int right = matrix[0].length - 1; //column

        while(left < m && right >= 0){
            if(matrix[left][right] == target){
                return true;
            } 
            
            if(target < matrix[left][right]){
                right--;
            } else if(target > matrix[left][right]){
                left++;
            }
        }

        return false;
    }
}
