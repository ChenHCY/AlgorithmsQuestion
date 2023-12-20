/*Leetcode 661. Image Smoother
An image smoother is a filter of the size 3 x 3 that can be applied to each cell of an image by rounding down the average of the cell and the eight surrounding 
cells (i.e., the average of the nine cells in the blue smoother). If one or more of the surrounding cells of a cell is not present, we do not consider it in the 
average (i.e., the average of the four cells in the red smoother).

Given an m x n integer matrix img representing the grayscale of an image, return the image after applying the smoother on each cell of it.

Example 1:
Input: img = [[1,1,1],[1,0,1],[1,1,1]]
Output: [[0,0,0],[0,0,0],[0,0,0]]
Explanation:
For the points (0,0), (0,2), (2,0), (2,2): floor(3/4) = floor(0.75) = 0
For the points (0,1), (1,0), (1,2), (2,1): floor(5/6) = floor(0.83333333) = 0
For the point (1,1): floor(8/9) = floor(0.88888889) = 0

Example 2:
Input: img = [[100,200,100],[200,50,200],[100,200,100]]
Output: [[137,141,137],[141,138,141],[137,141,137]]
Explanation:
For the points (0,0), (0,2), (2,0), (2,2): floor((100+200+200+50)/4) = floor(137.5) = 137
For the points (0,1), (1,0), (1,2), (2,1): floor((200+200+50+200+100+100)/6) = floor(141.666667) = 141
For the point (1,1): floor((50+200+200+200+200+100+100+100+100)/9) = floor(138.888889) = 138
 

Constraints:
m == img.length
n == img[i].length
1 <= m, n <= 200
0 <= img[i][j] <= 255
*/
// 因为数目不大只有200，可以蛮力接触，遍历每个格子，然后计算该格子周围3*3的总和和存在的格子数量，得到平均值
// 时间复杂度：O(mnC^2) ==> 其中 m 为给定矩阵的行数，n 为给定矩阵的列数，C=3 为过滤器的宽高。我们需要遍历整个矩阵以计算每个位置的值，计算单个位置的值的时间复杂度为 O(C^2)。
// 空间复杂度：O(1) ==> 注意返回值不计入空间复杂度。
class Solution {
    public int[][] imageSmoother(int[][] img) {
        int m = img.length;
        int n = img[0].length;

        int[][] res = new int[m][n];

        //遍历每个格子然后计算每个格子3x3的平均值
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                int num = 0; //格子数量
                int sum = 0; //这个3*3区域的总和
                for(int x = i - 1; x <= i + 1; x++){
                    for(int y = j - 1; y <= j + 1; y++){
                        if(x >= 0 && x < m && y >= 0 && y < n){
                            num++; //有符合要求的格子，数量+1
                            sum += img[x][y]; //计算到这段区域的sum
                        }
                    }
                }
                res[i][j] = sum / num;
            }
        }

        return res;
    }
}
