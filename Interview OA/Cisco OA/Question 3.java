/* 矩阵中行的最大值 和 列的最小值*/

class Solution
{
	public static void findElement(int[][] matrix)
	{
		// Write your code here
		int res = -1; // if no element is found, print '-1'
		int m = matrix.length; // the length of matrix row
		int n = matrix[0].length; // the lenegth of matrix column

		//1.we can find the largets elment in the martrix every rows and save into int[] array
		int[] largestElement = new int[m];
		for(int i = 0; i < m; i++){
			largestElement[i] = matrix[i][0]; //the first element value of matrix every rows
			for(int j = 1; j < n; j++){
				if(matrix[i][j] > largestElement[i]){
					largestElement[i] = matrix[i][j]; //update the largest value of curr row
				}
			}
		}

		//2. we can find the smallest element in the matrix every columns and save into int[] array
		int[] smallestElement = new int[n];
		for(int j = 0; j < n; j++){
			smallestElement[j] = matrix[0][j];//the first element value of matrix every columns
			for(int i = 1; i < m; i++){
				if(matrix[i][j] < smallestElement[j]){
					smallestElement[j] = matrix[i][j]; //update the smallest value of curr row
				}
			}
		}

		//3. find the largest element form matrix row that also is the smallest element from martix column
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				//compare and get the value is largest in row and smallest value in column
				if(matrix[i][j] == largestElement[i] && matrix[i][j] == smallestElement[j]){
					res = matrix[i][j];
					break;
				}
			}
		}
		
		System.out.println(res);
	}
