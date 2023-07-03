/* Leetcode 2220. Minimum Bit Flips to Convert Number
  
A bit flip of a number x is choosing a bit in the binary representation of x and flipping it from either 0 to 1 or 1 to 0.

For example, for x = 7, the binary representation is 111 and we may choose any bit (including any leading zeros not shown) and 
flip it. We can flip the first bit from the right to get 110, flip the second bit from the right to get 101, flip the fifth bit from the right (a leading zero) to get 10111, etc.

  Given two integers start and goal, return the minimum number of bit flips to convert start to goal.

Example 1:

Input: start = 10, goal = 7
Output: 3
Explanation: The binary representation of 10 and 7 are 1010 and 0111 respectively. We can convert 10 to 7 in 3 steps:
- Flip the first bit from the right: 1010 -> 1011.
- Flip the third bit from the right: 1011 -> 1111.
- Flip the fourth bit from the right: 1111 -> 0111.
It can be shown we cannot convert 10 to 7 in less than 3 steps. Hence, we return 3.


Example 2:

Input: start = 3, goal = 4
Output: 3
Explanation: The binary representation of 3 and 4 are 011 and 100 respectively. We can convert 3 to 4 in 3 steps:
- Flip the first bit from the right: 011 -> 010.
- Flip the second bit from the right: 010 -> 000.
- Flip the third bit from the right: 000 -> 100.
It can be shown we cannot convert 3 to 4 in less than 3 steps. Hence, we return 3.
 

Constraints:

0 <= start, goal <= 10^9
*/

class Solution {
    public int minBitFlips(int start, int goal) {
        //检查这两个数字 start goal的二进制 存在多少位的不同

        int res = 0;
        //从左往右，每次判断start goal 二进制里 一位数值 &(AND) 1, 得到的值是否一样
        while(start != 0 || goal != 0){
            // &(AND)：二进制运算符， 返回输入值的逐位， 有0返回0， 都为1才返回1
            //所以如果 &(AND) 得到的值相同，则代表start goal在当前位数的值是一样的
            if((start & 1) != (goal & 1)){
                res++;
            }
            //除以2， 右移1位
            start >>= 1; //把 start的二进制 往右移动一次
            goal >>= 1; //把 goal的二进制 往右移一次
        }

        return res;
    }
}
