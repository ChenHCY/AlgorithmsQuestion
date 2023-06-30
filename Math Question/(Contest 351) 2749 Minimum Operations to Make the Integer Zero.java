/*Leetcode (Contest 351) 2749 Minimum Operations to Make the Integer Zero

You are given two integers num1 and num2.

In one operation, you can choose integer i in the range [0, 60] and subtract 2i + num2 from num1.

Return the integer denoting the minimum number of operations needed to make num1 equal to 0.

If it is impossible to make num1 equal to 0, return -1.

Example 1:

Input: num1 = 3, num2 = -2
Output: 3
Explanation: We can make 3 equal to 0 with the following operations:
- We choose i = 2 and substract 22 + (-2) from 3, 3 - (4 + (-2)) = 1.
- We choose i = 2 and substract 22 + (-2) from 1, 1 - (4 + (-2)) = -1.
- We choose i = 0 and substract 20 + (-2) from -1, (-1) - (1 + (-2)) = 0.
It can be proven, that 3 is the minimum number of operations that we need to perform.

Example 2:

Input: num1 = 5, num2 = 7
Output: -1
Explanation: It can be proven, that it is impossible to make 5 equal to 0 with the given operation.
 

Constraints:

1 <= num1 <= 10^9
-10^9 <= num2 <= 10^9
*/

class Solution {
    public int makeTheIntegerZero(int num1, int num2) {
        /*暴力枚举：枚举 操作 K 次， 能通过 nums1 - (2^i + nums2)， 把nums1 变成 0
           根据数学推导：nums1 - (2^i + nums2) * k = 0 => nums1 = (2^i + nums2) * k
           最后可以得到 ==>  nums1 - nums2 * k = 2^i * k
           
           因为nums1 和 nums2都是已知的，k是每次枚举的，
           ==>  所以我们假设： x = nums1 - nums2 * k
           ==>  只需要计算 x 可以分成  多少个  2^i，也就是k的值

           ==> 同时 k也属于一个范围 k = [x.bitcount, x], 比如x=9, k的范围就是[2(1000 + 1), 9(1 + 1 + ... + 1)], 
        */ 
        
        for(long k = 1; k <= num1 - num2 * k; k++){
            //Long.bitCount(value)时计算value二进制里面存在的1的个数 
            if(k >= Long.bitCount(num1 - num2 * k)){
                return (int) k;
            }
        }
        return -1;
    }
}
