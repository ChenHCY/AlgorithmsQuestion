/* Leetcode 74. Search a 2D Matrix

You are given an m x n integer matrix matrix with the following two properties:

Each row is sorted in non-decreasing order.
The first integer of each row is greater than the last integer of the previous row.
Given an integer target, return true if target is in matrix or false otherwise.

You must write a solution in O(log(m * n)) time complexity.

Example 1:

Input: 
matrix = [[1,3,5,7],
          [10,11,16,20],
          [23,30,34,60]], 
target = 3

Output: true

Example 2:
Input: 
matrix = [[1,3,5,7],
          [10,11,16,20],
          [23,30,34,60]], 
target = 13

Output: false
 

Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 100
-104 <= matrix[i][j], target <= 104
*/

/* 思路：

因为这个二维数组的一定是从左上到右下的递增队列

所以我们可以把这个想象成一个一维的队列，然后进行二分法查找 Binary Search

==》左指针从左上开始，右指针从右下开始 ==》这个是来确定mid的位置（是第几个格子）
        int left = 0; //left pointer
        int right = row * colum - 1; //right pointer ==》 
        
这题的重点是如何确定二分法中mid的位置和 对应matrix[][]的值

==》 首先还是正常的二分，来找到martix中格子数量的中间值 ==> left + (right - left) / 2; 

比如： left = 0; row = 3 column = 4 ==> right = 11 ==> martix总长度是11个格子
==》 mid = left + (right - left) / 2 = 5; ==> mid此时在第5个格子上

==》 然后通过mid的值，来找到对应在matrix[][]的位置: 

数学公式：row = mid / n  &&  column = mid % n （n 代表列数，和一行有多少个格子）

==> midValue = matrix[mid / n][mid % n];

然后把mid对应在matrix[][]的值和target进行比较

正常的二分思路，小于target值，移动left指针到mid+1, 大于target移动right指针到mid-1， 等于targt 返回true 
*/
//Time: O(log(m * n))  Space: O(1)
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
            int midValue = matrix[mid / n][mid % n];
            
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
