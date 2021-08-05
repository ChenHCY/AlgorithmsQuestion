/** 1 · A + B Problem
Description
Write a function that add two numbers a and b, and return the answer as an integer(int).


Example 1:
Input:
a = 1
b = 2
Output: 3
Explanation: a + b = 1 + 2 = 3

Example 2:
Input:
a = -1
b = 1
Output: 0
Explanation:  a + b = -1 + 1 = 0
*//
  
public class Solution {
    /**
     * @param a: An integer
     * @param b: An integer
     * @return: The sum of a and b 
     */
    public int aplusb(int a, int b) {
        // write your code here
        // The Main idea is a + b = (a ^ b) + (a & b << 1)
        // a ^ b就是a和b相加之后，该进位的地方不进位的结果
        // 然后考虑哪些地方要进位(a和b里都是1的地方)
        // a & b就是a和b里都是1的那些位置，a & b << 1 就是进位
        while(b != 0){
            int A = a ^ b;
            int B = (a & b) << 1;
            a = A;
            b = B;
        }
        return a;
    }
}
