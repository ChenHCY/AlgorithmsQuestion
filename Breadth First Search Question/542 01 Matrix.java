/* Leetcode 542. 01 Matrix

Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.

Example 1:
Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
Output: [[0,0,0],[0,1,0],[0,0,0]]

Example 2:
Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
Output: [[0,0,0],[0,1,0],[1,2,1]]
 

Constraints:
m == mat.length
n == mat[i].length
1 <= m, n <= 104
1 <= m * n <= 104
mat[i][j] is either 0 or 1.
There is at least one 0 in mat.
*/

class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length; // m == mat.length
        int n = mat[0].length; // n == mat[i].length

        int step = 1; //step used to count the distance of the nearest 0 for each cell.

        //Used for dirsearch in the 4 directions
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; 

        //BFS Method
        Queue<int[]> queue = new LinkedList<>();
        //Used a boolean[][] list to save whether visted 
        boolean[][] visited = new boolean[m][n];

        //travaser all the cells 
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(mat[i][j] == 0){ //when find the curr cell value is 0
                    visited[i][j] = true; //save visisted it
                    queue.offer(new int[]{i, j}); //save the cells into queue list
                } else{
                    mat[i][j] = Integer.MAX_VALUE; //change the 1-cells value to be the largest value
                }
            }
        }

        //BFS Method format while-loop to travser all the elemnt in the queue list
        while(!queue.isEmpty()){
            int size = queue.size(); //the cells in the current level

            for(int k = 0; k < size; k++){
                int[] curr = queue.poll(); //get the one cell from the current level

                for(int[] dir : dirs){
                    //dirsearch in the 4 directions from the current cell
                    int row = curr[0] + dir[0];
                    int colum = curr[1] + dir[1];

                    //out the line
                    if(row < 0 || row >= m || colum < 0 || colum >= n){
                        continue;
                    }

                    //if cell is visited before
                    if(visited[row][colum]){
                        continue;
                    }

                    //if cell is not visited ===> it means the current cell is 1-cells
                    if(!visited[row][colum]){
                        // change the 1-cells to be the correct distance of the nearest 0 for each cell.
                        mat[row][colum] = step; 
                        visited[row][colum] = true; 
                        queue.offer(new int[]{row, colum}); //add into queue again ==> move to next level
                    }
                }
            }

            step++;
        }

        return mat;
    }
}
