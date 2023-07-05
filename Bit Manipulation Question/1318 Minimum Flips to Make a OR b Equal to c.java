/*Leetcode 1318. Minimum Flips to Make a OR b Equal to c

Given 3 positives numbers a, b and c. Return the minimum flips required in some bits of a and b to make ( a OR b == c ). (bitwise OR operation).
Flip operation consists of change any single bit 1 to 0 or change the bit 0 to 1 in their binary representation.

Example 1:
Input: a = 2, b = 6, c = 5
Output: 3
Explanation: After flips a = 1 , b = 4 , c = 5 such that (a OR b == c)

Example 2:

Input: a = 4, b = 2, c = 7
Output: 1

Example 3:

Input: a = 1, b = 2, c = 3
Output: 0
 

Constraints:

1 <= a <= 10^9
1 <= b <= 10^9
1 <= c <= 10^9

*/

/*此题的意思是Bitwise位运算，改变几个位，可以使得 a |(OR) b = c

    // |(OR): 二进制运算符，有1返回1，所有都为0，才返回0
    // &(AND): 二进制运算符，有0返回0，所有都为1，才返回1

所以我们可以根据c的二进制情况，进行反向推算
*/
//Time: O(n)   Space: O(1)
class Solution {
    public int minFlips(int a, int b, int c) {
        int res = 0; //翻转次数

        while(a != 0 || b != 0 || c != 0){
            //1. c == 1
            if((c & 1) == 1){//如果当前c的二进制位为1时，需要有一个1
                //如果当前二进制位的 a 和 b都为 0 => 0 & 1 = 0;
                if((a & 1) == 0 && (b & 1) == 0){
                    res += 1; //改变a b 任意一个二进制位为1
                }
            }else{ //如果当前c的二进制位为0时，需要a b的二进制位数都为 0
                // 1 & 1 = 1;
                // 0 & 1 = 0;
                // 所以如果a b有不为0的情况，则需要翻转，加入到翻转次数
                res += (a & 1) + (b & 1);
            }

            //移动a b c的二进制位数
            a >>= 1;
            b >>= 1;
            c >>= 1;
        }

        return res;
    }
}
