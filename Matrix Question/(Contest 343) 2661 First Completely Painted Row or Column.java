/* Leetcode 2661. First Completely Painted Row or Column

You are given a 0-indexed integer array arr, and an m x n integer matrix mat. arr and mat both contain all the integers in the range [1, m * n].

Go through each index i in arr starting from index 0 and paint the cell in mat containing the integer arr[i].

Return the smallest index i at which either a row or a column will be completely painted in mat.

Example 1:
Input: arr = [1,3,4,2], mat = [[1,4],[2,3]]
Output: 2
Explanation: The moves are shown in order, and both the first row and second column of the matrix become fully painted at arr[2].

Example 2:
Input: arr = [2,8,7,4,1,3,5,6,9], mat = [[3,2,5],[1,4,6],[8,7,9]]
Output: 3
Explanation: The second column becomes fully painted at arr[3].
 

Constraints:

m == mat.length
n = mat[i].length
arr.length == m * n
1 <= m, n <= 105
1 <= m * n <= 105
1 <= arr[i], mat[r][c] <= m * n
All the integers of arr are unique.
All the integers of mat are unique.
*/

/* 此题是依次完成array[]中每个值对应在mat[][]中的格子，然后返回第一个完成的列或者行的最后一个格子

如果每次查找和遍历每个格子，会超出时间。所以使用HashMap来记录array[]中每个值对应 mat[][]中的坐标

==》后面检查和记录是否完成一列或者一行时，可以节省运行的running time

遍历array[]里面的每一个值，然后拿到每个值对应在mat里面的坐标位置（row, column）

然后我们使用bucketSort来统计是否一列或者一行的所有格子都访问完成了

因为每个格子的值都不一样，

==》所以如果这个row值或者column值在bucketSort中有n个数量，表示这一行row或者一列column上的所有格子都访问完成了

*/
//Time: O(m * n)   Space: O(m * n)
class Solution {
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        //使用HashMap来记录array[]中每个值对应 mat[][]中的坐标
        //后面检查和记录是否完成一列或者一行时，可以节省运行的running time
        HashMap<Integer, int[]> map = new HashMap<>();
        int m = mat.length;
        int n = mat[0].length;
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                map.put(mat[i][j], new int[]{i, j});
            }
        }

        //我们使用bucketSort来统计是否一列或者一行的所有格子都访问完成了
        //因为每个格子的值都不一样，所以如果这个row值或者column值在bucketSort中有n个数量，
        //表示这一行row或者一列column上的所有格子都访问完成了
        int[] completeRow = new int[m];
        int[] completeColumn = new int[n];
        
        //遍历array[]里面的每一个值
        for(int i = 0; i < arr.length; i++){
            int curr = arr[i]; //当前遍历的格子值

            //然后拿到这个值对应在mat里面的坐标位置（row, column）
            //Row: currPosition[0], Column: currPosition[1]
            int[] currPosition = map.get(curr);

            //然后把坐标的row，column放入bucketSort中
            //记录这个row值和column值的数量
            completeRow[currPosition[0]]++;
            completeColumn[currPosition[1]]++;
            
            //当这个row值或者column值的数量等于 m 或者 n时，
            //表示这一行row或者一列column上的所有格子都访问完成了
            //mat[][]的row值为m  column值为n
            //==》但row中格子数量是column的长度，column中的格子数量是row的长度
            if(completeRow[currPosition[0]] == n || completeColumn[currPosition[1]] == m){
                return i;
            }  
        }
        
        return -1;
    }
}
