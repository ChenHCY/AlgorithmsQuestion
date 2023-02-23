/*Leetcode OA 6 Question 4 ==> Smillar with Leetcode 931. Minimum Falling Path Sum(row)

And Start at first Column ==> to find the minmum path
*/

class Result {

    /*
     * Complete the 'minimumResistence' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY matrix as parameter.
     */

    public static int minimumResistence(List<List<Integer>> matrix) {
    // Write your code here
        int n = matrix.size();
        int[][] mat = new int[n][n];
        int[][] dp = new int[n][n];
        int res = Integer.MAX_VALUE;
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                mat[i][j] = matrix.get(i).get(j);
            }
        }
        
        //travser the all the element from matrix(mat[][])
        for(int j = 0; j < n; j++){
            for(int i = 0; i < n; i++){
                if(j == 0){
                    dp[i][j] = mat[i][j]; //the value of every element in first column 
                } else{ //the Numbers in the middle vertical direction (every element have it)
                 //Traverse and count every element sum path start at second row
                    int prevRow = dp[i][j-1]; 
                    if(i - 1 >= 0){
                        prevRow = Math.min(dp[i-1][j-1], prevRow);
                    }
                    if(i + 1 < n){
                        prevRow = Math.min(dp[i+1][j-1], prevRow);
                    }
                    //add the current row value into every path
                    dp[i][j] = prevRow + mat[i][j];
                }
            }
        }
        
        //compare and count the minimum sum of any falling path through matrix
        for(int i = 0; i < n; i++){
            res = Math.min(res, dp[i][n-1]); //through compare the last row dp value
        }
        
        return res;
    }
    
}
