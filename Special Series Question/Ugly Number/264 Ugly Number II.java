/* Leetcode Ugly Number II

An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.

Given an integer n, return the nth ugly number.

Example 1:

Input: n = 10
Output: 12
Explanation: [1, 2, 3, 4, 5, 6, 8, 9, 10, 12] is the sequence of the first 10 ugly numbers.

Example 2:

Input: n = 1
Output: 1
Explanation: 1 has no prime factors, therefore all of its prime factors are limited to 2, 3, and 5.
 

Constraints:

1 <= n <= 16904
*/

//Time: O(n)  Space: O(n)
class Solution {
    public int nthUglyNumber(int n) {
        int[] res = new int[n];
        res[0] = 1;
        int factors2 = 0;
        int factors3 = 0;
        int factors5 = 0;

        for(int i = 1; i < n; i++){
            //因为prime factors只能是2, 3, 5, 所以每个指针乘以对应的数，然后每次加入最小值
            //重复n次，最后得到的数就是 第 nth 个 ugly number
            //同时也可以用来去除重复的数
            res[i] = Math.min(res[factors2] * 2, Math.min(res[factors3] * 3, res[factors5] * 5));
            
            //检查加入了哪个值，然后移动对应的指针
            if(res[i] == res[factors2] * 2){
                factors2 += 1;
            }
            if(res[i] == res[factors3] * 3){
                factors3 += 1;
            } 
            if(res[i] == res[factors5] * 5){
                factors5 += 1;
            }
        }

        return res[n - 1]; //输出res的array的最后一个数，也就是第 nth ugly number
    }
}
