/* Leetcode (Biweekly Contest 98) 2568. Minimum Impossible OR

You are given a 0-indexed integer array nums.

We say that an integer x is expressible from nums if there exist some integers 0 <= index1 < index2 < ... < indexk < nums.length for 
which nums[index1] | nums[index2] | ... | nums[indexk] = x. In other words, an integer is expressible if it can be written as the bitwise OR 
of some subsequence of nums.

Return the minimum positive non-zero integer that is not expressible from nums.

Example 1:

Input: nums = [2,1]
Output: 4
Explanation: 1 and 2 are already present in the array. We know that 3 is expressible, since nums[0] | nums[1] = 2 | 1 = 3. Since 4 is not expressible, we return 4.

Example 2:

Input: nums = [5,3,2]
Output: 1
Explanation: We can show that 1 is the smallest number that is not expressible.
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 109
*/

/* 思路：
        题意是找到nums[]中最小一个无法通过Bitwise OR(|)组合出来的数字

        思路就是用Array.sort()排序之后，得到一个递增的数列，然后从小到大 对 每一个数字进行检查

        每个数字都进行Bitwise OR(|)处理

        如果找到一个处理之后的 结果 + 1 是小于nums[]的下一个数字，

        ==》 那么这个结果+1是肯定无法通过nunms[]中的数字Bitwise OR(|)组合出来的
*/


//Time: O(n)  Space: O(1)
class Solution {
    public int minImpossibleOR(int[] nums) {
        Arrays.sort(nums);
        //首先检查第一个数字是否为1，如果不是，则代表1是无法组合出来的
        if(nums[0] > 1){
            return 1;
        }

        int temp = 0;
        for(int i = 0; i < nums.length; i++){
            temp |= nums[i]; // 每个数字都进行Bitwise OR(|)处理后

            //如果找到一个处理之后的 结果 + 1 是小于nums[]的下一个数字，
            if(i + 1 < nums.length && temp + 1 < nums[i + 1]){
                return temp + 1; // 那么这个结果+1是肯定无法通过nunms[]中的数字Bitwise OR(|)组合出来的
            }
        }

        return temp + 1;
    }
}
