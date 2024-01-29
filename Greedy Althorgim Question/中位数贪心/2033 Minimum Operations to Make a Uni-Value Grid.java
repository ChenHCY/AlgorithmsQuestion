/* 2033. Minimum Operations to Make a Uni-Value Grid

You are given a 2D integer grid of size m x n and an integer x. In one operation, you can add x to or subtract x from any element in the grid.

A uni-value grid is a grid where all the elements of it are equal.

Return the minimum number of operations to make the grid uni-value. If it is not possible, return -1.

Example 1:
Input: grid = [[2,4],[6,8]], x = 2
Output: 4
Explanation: We can make every element equal to 4 by doing the following: 
- Add x to 2 once.
- Subtract x from 6 once.
- Subtract x from 8 twice.
A total of 4 operations were used.

Example 2:
Input: grid = [[1,5],[2,3]], x = 1
Output: 5
Explanation: We can make every element equal to 3.

Example 3:
Input: grid = [[1,2],[3,4]], x = 2
Output: -1
Explanation: It is impossible to make every element equal.
 
Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 10^5
1 <= m * n <= 10^5
1 <= x, grid[i][j] <= 10^4
*/

class Solution {
    // 中位数贪心，我们找到grid里面的中位数值，然后对于每一个要修改的num进行模运算，就可以得到需要x的步骤
    public int minOperations(int[][] grid, int x) {
        int m = grid.length;
        int n = grid[0].length;

        int res = 0; //结果：需要使用x使得grid所有数字一样的步骤

        int i = 0;
        int[] arr = new int[m * n];
        for(int[] nums : grid){  //把grid里面所有的数字都加入到arr中
            for(int num : nums){
                arr[i] = num;
                i++;
            }
        }

        Arrays.sort(arr); //对于arr进行升序排列
        int mid = arr[arr.length / 2]; //然后取中位数值
        
        //开始计算需要多少步骤
        for(int a : arr){
            int diff = Math.abs(mid - a); //距离中位数的距离
        
            if(diff % x != 0){ //判断这个差距是不是 加减x 能够完成的
                return -1; //如果不能输出-1
            }
            res += (diff / x); //统计总共的步骤
        }

        return res;
    }
}
