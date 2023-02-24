/*Samara OA Question 4
There is a grid of black and white cells with rows rows and cols columns, 
you're given an array black that contains the [row, column] coordinates of 
all the black cells in the grid Your task is to compute how many 2 x 2 submatrices 
of the grid contain exactly blackCount black cells, for each 0 ≤ blackCount ≤ 4. 

As a result, you will return an array of 5 integers, where the ith element is the 
number of 2 x2 submatrices with exactly i black cells. It is guaranteed that black 
cell coordinates in the black array are pairwise unique, so the same cell is not 
colored twice. are pairwise unique, so the same cell is not colored twice.

Example:
rows = 3, cols = 3, and black =[[0, 0], [0, 1], [1, 0]],

the output should be solution (rows, cols, black) [1, 2, 0, 1, 0]
*/

import java.util.*;
import java.util.ArrayList; 
import java.util.HashMap;

class Main {
    public static void main(String[] args) {
        int rows = 3;
        int cols = 3;
        int[][] black = new int[][]{{0, 0}, {0, 1}, {1, 0}};
        
        int[] res = findblock(rows, cols, black);
        
        for(int num : res){
            System.out.println(num);
        }
    }
    
    public static int[] findblock(int rows, int cols, int black[][]) {
        int ans[] = new int[5];
        if(rows < 2 || cols < 2) // if no 2*2 sub-matrices are possible
         return ans;
         
         int grid[][] = new int[rows][cols]; // 0 -> white, 1 -> black
         
         for(int i[] : black){
             grid[i[0]][i[1]] = 1;
         }
         
         for(int i=0;i<rows-1;i++) {
             for(int j=0;j<cols-1;j++) {
                 int number_of_blacks = grid[i][j] + grid[i][j+1] + grid[i+1][j] + grid[i+1][j+1];
                 ans[number_of_blacks]++;
            }
         }
         
         return ans;
    }
}
