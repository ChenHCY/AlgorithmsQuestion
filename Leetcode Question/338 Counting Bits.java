/*Leetcode 338. Counting Bits

Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n), ans[i] is the number of 1's in the binary representation of i.

Example 1:
Input: n = 2
Output: [0,1,1]
Explanation:
0 --> 0
1 --> 1
2 --> 10

Example 2:
Input: n = 5
Output: [0,1,1,2,1,2]
Explanation:
0 --> 0
1 --> 1
2 --> 10
3 --> 11
4 --> 100
5 --> 101
*/

//Solution 1: Runtime: O(n) 3 ms, Memory Usage: 48.3 MB,
class Solution {
    public int[] countBits(int n) {
        int[] res = new int[n+1];
        
        for(int i = 1; i < res.length; i++){
            
            res[i] = res[i / 2] + i % 2;
        }      
        return res;
    }
}


//Solution 2： Runtime: O(n^2) 21 ms, Memory Usage: 47.9 MB,
class Solution {
  
    public int[] countBits(int n) {
        int[] res = new int[n+1];
        
        for(int i = 1; i < res.length; i++){
            
            res[i] = numberOne(i);
        }
        
        return res;
    }
    
    public int numberOne(int n){
        int temp = 0;
        
        while(n != 0){
            if(n % 2 == 1){
                temp += 1;
            }
            n /= 2;
        }
        
        return temp;
    }  
}
