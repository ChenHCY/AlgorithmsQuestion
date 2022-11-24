/* Leetcode 733. Flood Fill
An image is represented by an m x n integer grid image where image[i][j] 
represents the pixel value of the image.

You are also given three integers sr, sc, and color. 
You should perform a flood fill on the image starting from the pixel image[sr][sc].

To perform a flood fill, consider the starting pixel, plus any pixels connected 4-directionally 
to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally 
to those pixels (also with the same color), and so on. Replace the color of all of the aforementioned pixels with color.
Return the modified image after performing the flood fill.

Example 1:
Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, color = 2
Output: [[2,2,2],[2,2,0],[2,0,1]]
Explanation: From the center of the image with position (sr, sc) = (1, 1) (i.e., the red pixel), all pixels connected by a path of the same color as the starting pixel (i.e., the blue pixels) are colored with the new color.
Note the bottom corner is not colored 2, because it is not 4-directionally connected to the starting pixel.

Example 2:
Input: image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, color = 0
Output: [[0,0,0],[0,0,0]]
Explanation: The starting pixel is already colored 0, so no changes are made to the image.
 
Constraints:
m == image.length
n == image[i].length
1 <= m, n <= 50
0 <= image[i][j], color < 216
0 <= sr < m
0 <= sc < n
*/

/*
Thought:

Used DFS method:

1. Find the the position with the startcolor value

2. used dfs method to change the same color value with startColor in the start position's 4-directionally

*/
//Time: O(N)  Space: O(N)
class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int startColor = image[sr][sc];
        //judge whether the startColor is same with newColor
        if(startColor != color){
            dfs(image, sr, sc, startColor, color); //call dfs function
        }
        
        return image;
    }
    
    public void dfs(int[][] image, int row, int colum, int startColor, int newColor){
        //the exit condition of dfs ==> if not same with startColor, do not need to change and backto perivous level
        if(row < 0 || row >= image.length || colum < 0 || colum >= image[0].length || image[row][colum] != startColor){
            return;
        }
        
        //change the color to become the newColor
        //used dfs method to change the same color value with startColor in the start position's 4-directionally
        image[row][colum] = newColor;
        //4-directionally
        dfs(image, row + 1, colum, startColor, newColor); //top
        dfs(image, row - 1, colum, startColor, newColor);//down
        dfs(image, row, colum + 1, startColor, newColor);//right
        dfs(image, row, colum - 1, startColor, newColor);//left
    }
}
