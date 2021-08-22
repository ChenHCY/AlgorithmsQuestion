/* 366 · Fibonacci
Description
Find the Nth number in Fibonacci sequence.
A Fibonacci sequence is defined as follow:
The first two numbers are 0 and 1.
The i th number is the sum of i-1 th number and i-2 th number.

The first ten numbers in Fibonacci sequence is:
0, 1, 1, 2, 3, 5, 8, 13, 21, 34 ...

Example 1:
	Input:  1
	Output: 0
	
	Explanation: 
	return the first number in  Fibonacci sequence .

Example 2:
	Input:  2
	Output: 1
	
	Explanation: 
	return the second number in  Fibonacci sequence .
*/

public class Solution {
    /**
     * @param n: an integer
     * @return: an ineger f(n)
     */
  
  // If just use simple recursive method, it will over the Time complexity.
  // So we should used a list(result[]) to save the data value. It can use less time. 
  
    int dfs(int n, int[] result){
        if(result[n] != -1){
            return result[n];
        }
        if(n <= 2){
            result[n] = n - 1;
            return result[n];
        } 
        
        result[n] = dfs(n - 1, result) + dfs(n - 2, result);
    
        return result[n];
    }
    
    public int fibonacci(int n) {
        // write your code here
       int[] result = new int[n+1];

       for(int i = 0; i <= n; i++){
           result[i] = -1;
       }
       dfs(n, result);

       return result[n];
    }
}
