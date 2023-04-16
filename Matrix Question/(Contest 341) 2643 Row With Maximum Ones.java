/* Leetcode (Contest 341) 2643 Row With Maximum Ones

Given a m x n binary matrix mat, find the 0-indexed position of the row that contains the maximum count of ones, and the number of ones in that row.

In case there are multiple rows that have the maximum count of ones, the row with the smallest row number should be selected.

Return an array containing the index of the row, and the number of ones in it.

Example 1:

Input: mat = [[0,1],[1,0]]
Output: [0,1]
Explanation: Both rows have the same number of 1's. So we return the index of the smaller row, 0, and the maximum count of ones (1). So, the answer is [0,1]. 

Example 2:

Input: mat = [[0,0,0],[0,1,1]]
Output: [1,2]
Explanation: The row indexed 1 has the maximum count of ones (2). So we return its index, 1, and the count. So, the answer is [1,2].

Example 3:

Input: mat = [[0,0],[1,1],[0,0]]
Output: [1,2]
Explanation: The row indexed 1 has the maximum count of ones (2). So the answer is [1,2].
 

Constraints:

m == mat.length 
n == mat[i].length 
1 <= m, n <= 100 
mat[i][j] is either 0 or 1.
*/
//思路： 两个for-loop遍历, 统计每一行 1的数量 ==》然后进行比较 找到拥有1最多的 行数（第几行）和 其中1的数量
//Time: O(m*n)  Space: O(1)
class Solution {
    public int[] rowAndMaximumOnes(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        
        int maxOnes = 0;
        int maxIndex = 0;
        
        for(int i = 0; i < m; i++){
            int oneTimes = 0;
            for(int j = 0; j < n; j++){
                oneTimes += mat[i][j];
            }
            if(oneTimes > maxOnes){
                maxOnes = oneTimes;
                maxIndex = i;
            }
        }
        
        return new int[]{maxIndex, maxOnes};
    }
}
