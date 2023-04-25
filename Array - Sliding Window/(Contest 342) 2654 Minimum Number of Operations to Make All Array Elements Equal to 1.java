/* Leetcode 2654. Minimum Number of Operations to Make All Array Elements Equal to 1

You are given a 0-indexed array nums consisiting of positive integers. You can do the following operation on the array any number of times:

Select an index i such that 0 <= i < n - 1 and replace either of nums[i] or nums[i+1] with their gcd value.

Return the minimum number of operations to make all elements of nums equal to 1. If it is impossible, return -1.

The gcd of two integers is the greatest common divisor of the two integers.

Example 1:

Input: nums = [2,6,3,4]
Output: 4
Explanation: We can do the following operations:
- Choose index i = 2 and replace nums[2] with gcd(3,4) = 1. Now we have nums = [2,6,1,4].
- Choose index i = 1 and replace nums[1] with gcd(6,1) = 1. Now we have nums = [2,1,1,4].
- Choose index i = 0 and replace nums[0] with gcd(2,1) = 1. Now we have nums = [1,1,1,4].
- Choose index i = 2 and replace nums[3] with gcd(1,4) = 1. Now we have nums = [1,1,1,1].

Example 2:

Input: nums = [2,10,6,14]
Output: -1
Explanation: It can be shown that it is impossible to make all the elements equal to 1.
 

Constraints:

2 <= nums.length <= 50
1 <= nums[i] <= 10^6
*/
/* 思路： 此题思路是通过gcd最大公约数的最少次数

有三种情况；

1. 如果所有数的 GCD（最大公约数）大于 1，那么无论如何都无法操作出 1，我们返回 -1

2.  如果nums[]中有一个1 或者 多个1，则需要的操作次数 为 n - countOne（1的数量）

3. 如果 nums 中没有任何一个1，就要找可以操作gcd得到1的 最短长度连续子数组
==》 然后这个连续子数组的长度为 minSize, 需要操作 minsize - 1次得到为1的最大公约数
==》 最后让整个数组都为1的次数 是 (minSize−1) + (n−1) =  minSize + n − 2


时间复杂度： O(n(n + logU))  空间复杂度：O(1)
*/

class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int gcdAll = 0;
        int countOne = 0;
        for(int num : nums){
            gcdAll = gcd(gcdAll, num); //检查所有数的最大公约数
            if(num == 1){
                countOne += 1;
            }
        }

        //如果所有数的 GCD（最大公约数）大于 1，那么无论如何都无法操作出 1，我们返回 -1
        if(gcdAll > 1){
            return -1;
        }

        //如果nums[]中有一个1 或者 多个1，则需要的操作次数 为 n - countOne
        if(countOne > 0){
            return n - countOne;
        }

        //如果 nums 中没有任何一个1，就要找可以操作gcd得到1的 最短长度连续子数组
        //然后这个连续子数组的长度为 minSize, 需要操作 minsize - 1次得到为1的最大公约数
        //最后让整个数组都为1的次数 是 (minSize−1) + (n−1) =  minSize + n − 2
        int minSize = n;
        //子数组的区域[i, j], j从i开始 不停的往左推
        for(int i = 0; i < n; i++){
            int gcdOne = 0;
            for(int j = i; j < n; j++){
                gcdOne = gcd(gcdOne, nums[j]);
                if(gcdOne == 1){
                    //得到最短的连续子数组，==》最大公约数为1
                    minSize = Math.min(minSize, j - i + 1);
                    break;
                }
            }
        }

        return minSize + n - 2;
    }

    //用于计算两个整数 a 和 b 的最大公约数 (GCD)
    public static int gcd(int a, int b){
        while(a != 0){
            int temp = a;
            a = b % a;
            b = temp;
        }
        return b;
    }
}
