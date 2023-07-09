/* 201. Bitwise AND of Numbers Range

Given two integers left and right that represent the range [left, right], return the bitwise AND of all numbers in this range, inclusive.

Example 1:

Input: left = 5, right = 7
Output: 4

Example 2:

Input: left = 0, right = 0
Output: 0

Example 3:

Input: left = 1, right = 2147483647
Output: 0
 

Constraints:

0 <= left <= right <= 2^31 - 1

*/

/* 此题是对于[left, right]的范围内所有数字，逐个进行AND运算。输出最后得到的结果
  
  AND(&): 二进制运算符， 有0返回0，所有位都是1时，才输出返回1

  但按照题目的要求，如果在[left, right]的范围内逐个进行AND运算
  那么在[left, right]范围较大的情况下，将会出现超时。

  => 然后我们发现 5 和 7 二进制形式存在公共前缀 01,
  因此从 5 → 7 ，其前两位 01 是一直不变的，只有后两位一直在变化
  => 同时：AND(&): 二进制运算符， 有0返回0，只有有一位为0，AND的结果一定为0

   所以我们只有找到范围界限的公共前缀，后面剩余的部分都补上0 
   ==》则就是[left, right]的范围所有数字，逐个进行AND运算的结果
*/
class Solution {
    public int rangeBitwiseAnd(int left, int right) {
        if(left == 0){
            return 0;
        }
        int index = 0;
        //while-loop找到公共前缀
        while(left != right){
            left >>= 1; //left数字二进制向右移动一位
            right >>= 1; //right数字的二进制向右移动一位
            index++;         
        }
        return left << index; //给除了公共前缀以外，剩余部分都补上0 =》得到最后 AND运算的输出结果
    }
}
