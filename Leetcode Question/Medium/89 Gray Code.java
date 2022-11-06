/* Leetcode 89. Gray Code

An n-bit gray code sequence is a sequence of 2n integers where:
Every integer is in the inclusive range [0, 2n - 1],
The first integer is 0,
An integer appears no more than once in the sequence,
The binary representation of every pair of adjacent integers differs by exactly one bit, and
The binary representation of the first and last integers differs by exactly one bit.
Given an integer n, return any valid n-bit gray code sequence.

Example 1:
Input: n = 2
Output: [0,1,3,2]
Explanation:
The binary representation of [0,1,3,2] is [00,01,11,10].
- 00 and 01 differ by one bit
- 01 and 11 differ by one bit
- 11 and 10 differ by one bit
- 10 and 00 differ by one bit
[0,2,3,1] is also a valid gray code sequence, whose binary representation is [00,10,11,01].
- 00 and 10 differ by one bit
- 10 and 11 differ by one bit
- 11 and 01 differ by one bit
- 01 and 00 differ by one bit

Example 2:
Input: n = 1
Output: [0,1]
 
Constraints:
1 <= n <= 16
*/

//题目意思：生成 n 位的格雷码。n位格雷码是个int的数组，其每相邻的两个数的二进制表示只有一位不同，并且首尾也满足这个条件。

//思路：如果我们已经得到了k位格雷码，那么下一位（k + 1）位格雷码可以这样生成：

//先将得到的k位格雷码反序，然后每个数字都加上 (1 << k) => 2^k ==> 得到了下一位格雷码


//Time: O(2^n)  
class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        res.add(0);
        
        for(int i = 0; i < n; i++){
            int size = res.size();
            for(int j = size - 1; j >= 0; j--){
                res.add(res.get(j) + (1 << i));
            }
        }
        
        return res;
    }
}
