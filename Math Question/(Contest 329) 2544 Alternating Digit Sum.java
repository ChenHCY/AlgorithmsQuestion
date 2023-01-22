/* Leetcode (Contest 329) 2544 Alternating Digit Sum.java

You are given a positive integer n. Each digit of n has a sign according to the following rules:

The most significant digit is assigned a positive sign.
Each other digit has an opposite sign to its adjacent digits.

Return the sum of all digits with their corresponding sign.

Example 1:

Input: n = 521
Output: 4
Explanation: (+5) + (-2) + (+1) = 4.

Example 2:

Input: n = 111
Output: 1
Explanation: (+1) + (-1) + (+1) = 1.

Example 3:

Input: n = 886996
Output: 0
Explanation: (+8) + (-8) + (+6) + (-9) + (+9) + (-6) = 0.
 

Constraints:

1 <= n <= 109
*/

// My Solution:  Time: O(n^2)  Space:O(n)
class Solution {
    public int alternateDigitSum(int n) {
        int res = 0;
        int index = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        
        while(n > 0){
            int temp = n % 10;
            map.put(index, temp);
            index++;
            n /= 10;
        }
        
        if(map.size() % 2 == 0){
            for(int key : map.keySet()){
                if(key % 2 == 0){
                    res -= map.get(key);
                } else{
                    res += map.get(key);
                }
            }
        } else{
            for(int key : map.keySet()){
                if(key % 2 == 0){
                    res += map.get(key);
                } else{
                    res -= map.get(key);
                }
            }
        }
        
        return res;
    }
}

//The Best solution: Time: O(n)   Space:O(1)
class Solution {
    public int alternateDigitSum(int n) {
        int digit = countDig(n); 
        int res = 0; 
        int sign = ((digit % 2) == 0) ? -1 : 1;

        while(n>0){
            res = res + (n % 10) * sign;
            n /= 10;
            sign *= -1;
        }
        
        return res;
    }

    //small function to check the length of n digits is odd or even
    public static int countDig(int n) {  
        int count = 0;  

        while(n != 0){  
            n /= 10;  
            count += 1;  
        }  

        return count;  
    } 
}
