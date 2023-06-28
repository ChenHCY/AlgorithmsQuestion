/* 304. Range Sum Query 2D - Immutable

Given a 2D matrix matrix, handle multiple queries of the following type:

    · Calculate the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Implement the NumMatrix class:

    · NumMatrix(int[][] matrix) Initializes the object with the integer matrix matrix.
    · int sumRegion(int row1, int col1, int row2, int col2) Returns the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

You must design an algorithm where sumRegion works on O(1) time complexity.

 Example 1:
 Input
["NumMatrix", "sumRegion", "sumRegion", "sumRegion"]
[[[[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]], [2, 1, 4, 3], [1, 1, 2, 2], [1, 2, 2, 4]]
Output
[null, 8, 11, 12]

Explanation
NumMatrix numMatrix = new NumMatrix([[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]);
numMatrix.sumRegion(2, 1, 4, 3); // return 8 (i.e sum of the red rectangle)
numMatrix.sumRegion(1, 1, 2, 2); // return 11 (i.e sum of the green rectangle)
numMatrix.sumRegion(1, 2, 2, 4); // return 12 (i.e sum of the blue rectangle)
 

Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 200
-104 <= matrix[i][j] <= 10^4
0 <= row1 <= row2 < m
0 <= col1 <= col2 < n
At most 104 calls will be made to sumRegion.
*/

class NumMatrix {
    // 「二维前缀和」解决的是二维矩阵中的矩形区域求和问题。
    int[][] PrefixSum; //对于 martix 中的建立前缀和

    // 1. 二维前缀和数组 PrefixSum[][] 中的每一个格子记录的是「以当前格子为区域的右下角，区域左上角恒定为原数组的左上角的区域和」
    // ==> PrefixSum [i][j] 理解成是以 (i, j) 为右下角，(0, 0) 为左上角的 整个区域和
    // ==> 公式： PrefixSum [i][j] =  PrefixSum[i-1][j] + PrefixSum[i][j-1] - PrefixSum[i-1][j-1] + matrix[i][j];
    public NumMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        PrefixSum = new int[m + 1][n + 1];

        // > PrefixSum [i][j] 理解成是以 (i, j) 为右下角，(0, 0) 为左上角的 整个区域和
        // 因为前缀和求的之前数组的总和，所以我们创建的是一个长度为[m + 1][n + 1]的二维数组
        // 把储存的值都是从[1][1]开始的格子里，可以避免 i j = 0时, 需要的边界判断条件
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                PrefixSum[i][j] = PrefixSum[i-1][j] + PrefixSum[i][j-1] - PrefixSum[i-1][j-1] + matrix[i - 1][j - 1];
            }
        }    
    }

    // 2. 然后怎么计算一个区域的总和：比如： (x1, y1) 作为左上角 (x2, y2) 作为右下角 的区域和
    // ==> 我们可以快速使用已经得出的前缀和数组进行求和：
    // ==> 公式：areaSum = PrefixSum [x2][y2] - PrefixSum [x1 - 1][y2] - PrefixSum [x2][y1 - 1] + PrefixSum [x1 - 1][y1 - 1]
    public int sumRegion(int row1, int col1, int row2, int col2) {
        //因为我们在计算区域值 和 储存前缀和时，把每个格子对应的区域和位置 都加了 1，所以在查找时，也需要给每个位置index + 1
        int s1 = row1 + 1;
        int e1 = col1 + 1;
        int s2 = row2 + 1;
        int e2 = col2 + 1;
        int areaSum = PrefixSum[s2][e2] - PrefixSum[s1 - 1][e2] - PrefixSum[s2][e1 - 1] + PrefixSum[s1 - 1][e1 - 1];
        return areaSum;
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
