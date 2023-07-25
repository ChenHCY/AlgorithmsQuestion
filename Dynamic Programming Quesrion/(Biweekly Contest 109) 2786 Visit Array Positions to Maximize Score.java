/*Leetcode (Biweekly Contest 109) 2786 Visit Array Positions to Maximize Score

You are given a 0-indexed integer array nums and a positive integer x.

You are initially at position 0 in the array and you can visit other positions according to the following rules:

    · If you are currently in position i, then you can move to any position j such that i < j.
    · For each position i that you visit, you get a score of nums[i].
    · If you move from a position i to a position j and the parities of nums[i] and nums[j] differ, then you lose a score of x.

Return the maximum total score you can get.

Note that initially you have nums[0] points.

Example 1:

Input: nums = [2,3,6,1,9,2], x = 5
Output: 13
Explanation: We can visit the following positions in the array: 0 -> 2 -> 3 -> 4.
The corresponding values are 2, 6, 1 and 9. Since the integers 6 and 1 have different parities, the move 2 -> 3 will make you lose a score of x = 5.
The total score will be: 2 + 6 + 1 + 9 - 5 = 13.

Example 2:

Input: nums = [2,4,6,8], x = 3
Output: 20
Explanation: All the integers in the array have the same parities, so we can visit all of them without losing any score.
The total score is: 2 + 4 + 6 + 8 = 20.
 

Constraints:

2 <= nums.length <= 10^5
1 <= nums[i], x <= 10^6
*/
 /*动态规划： 枚举选哪个  选或不选 
    枚举选哪个：适用于需要完全知道子序列相邻两数的信息 =》比如：找到数组的最长的递增子序列 O(n^2)
    选或不选：适用于不需要完全知道子序列相邻数字的信息： ==》1. 需要得到的子序列和相邻数字无关 ==》2. 子序列相邻数字的弱关联 （比如：奇偶性）
*/
//Time: O(n)   Space: O(1)
class Solution {

    //此题中特殊性是要考虑奇偶性，第n个位置的分数要么由前一个最大的奇数得到，要么由前一个最大的偶数得到。
    // 前面最大的奇数结果用odd表示，最大的偶数结果用even表示
    // ==> 首先根据nums[0]的奇偶性，对于 odd 和 even 进行初始化
    // 对nums从1开始遍历所有数字，根据奇偶性检查, 计算可能得到的每个分数，并逐步更新odd与even，从而得到最大值
    public long maxScore(int[] nums, int x) {
        int n = nums.length;

        /*根据nums[0]的奇偶性，对于 odd 和 even 进行初始化*/
        //如果nums[0]是奇数，则odd的值就是nums[0]
        //如果nums[0]是偶数，则odd的值初始化就是 -x
        long odd = nums[0] % 2 == 1 ? nums[0] : -x;

        //如果nums[0]是偶数，则even的值就是nums[0]
        //如果nums[0]是奇数，则even的值初始化就是 -x
        long even = nums[0] % 2 == 0 ? nums[0] : -x;

        long res = Math.max(odd, even); //初始化得到结果值

        //主要for-loop遍历，每当遍历到一个数时，进行检查奇偶性
        for(int i = 1; i < n; i++){
            long currScore = 0L;

            //如果当前数是奇数，
            //那么值可能是从之前的奇数来的，currScore = odd + nums[i]
            //也可能是从之前的偶数来的, currScore = even + nums[i] - x (奇偶性不一样 要减去x)
            // 两者取大的值
            if(nums[i] % 2 == 1){
                currScore = Math.max(odd + nums[i], even + nums[i] - x);
                //然后更新对应的奇偶性值
                odd = Math.max(odd, currScore);
            } else{
                currScore = Math.max(even + nums[i], odd + nums[i] - x);
                //然后更新对应的奇偶性值
                even = Math.max(even, currScore);
            }
            res = Math.max(res, currScore); //每次更新得到最大值
        }

        return res;
    }
}
