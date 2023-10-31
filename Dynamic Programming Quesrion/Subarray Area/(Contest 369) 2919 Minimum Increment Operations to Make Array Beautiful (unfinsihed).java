/* Leetcode 2919. Minimum Increment Operations to Make Array Beautiful

You are given a 0-indexed integer array nums having length n, and an integer k.

You can perform the following increment operation any number of times (including zero):

    · Choose an index i in the range [0, n - 1], and increase nums[i] by 1.

An array is considered beautiful if, for any subarray with a size of 3 or more, its maximum element is greater than or equal to k.

Return an integer denoting the minimum number of increment operations needed to make nums beautiful.

A subarray is a contiguous non-empty sequence of elements within an array.

Example 1:

Input: nums = [2,3,0,0,2], k = 4
Output: 3
Explanation: We can perform the following increment operations to make nums beautiful:
Choose index i = 1 and increase nums[1] by 1 -> [2,4,0,0,2].
Choose index i = 4 and increase nums[4] by 1 -> [2,4,0,0,3].
Choose index i = 4 and increase nums[4] by 1 -> [2,4,0,0,4].
The subarrays with a size of 3 or more are: [2,4,0], [4,0,0], [0,0,4], [2,4,0,0], [4,0,0,4], [2,4,0,0,4].
In all the subarrays, the maximum element is equal to k = 4, so nums is now beautiful.
It can be shown that nums cannot be made beautiful with fewer than 3 increment operations.
Hence, the answer is 3.

Example 2:

Input: nums = [0,1,3,3], k = 5
Output: 2
Explanation: We can perform the following increment operations to make nums beautiful:
Choose index i = 2 and increase nums[2] by 1 -> [0,1,4,3].
Choose index i = 2 and increase nums[2] by 1 -> [0,1,5,3].
The subarrays with a size of 3 or more are: [0,1,5], [1,5,3], [0,1,5,3].
In all the subarrays, the maximum element is equal to k = 5, so nums is now beautiful.
It can be shown that nums cannot be made beautiful with fewer than 2 increment operations.
Hence, the answer is 2.

Example 3:

Input: nums = [1,1,2], k = 1
Output: 0
Explanation: The only subarray with a size of 3 or more in this example is [1,1,2].
The maximum element, 2, is already greater than k = 1, so we don't need any increment operation.
Hence, the answer is 0.
 

Constraints:
3 <= n == nums.length <= 10^5
0 <= nums[i] <= 10^9
0 <= k <= 10^9
*/

//Time: O(N)  Space: O(N)
class Solution {
    // 题意：每次只能改变一个数，找到能使得nums[]成为美丽数组的最小次数
    // 美丽数组的定义：nums[]中所有大于等于3的子数组有一个大于等于k的最大值
    public long minIncrementOperations(int[] nums, int k) {
        //dp[i] 表示 修改第 i 项 并使前 i 项变为美丽数组的最小修改次数
        long[] dp = new long[3];

        //状态转移方程：
        /* min{dp[i-3], dp[i-2], dp[i-1]}：
            1. dp[i-3]就暗示了假设nums[i-1]和nums[i-2]不变，nums[i]和 nums[i-3]变
            2. dp[i-2]就暗示了假设nums[i-1]不变，nums[i]和 nums[i-2]变
            3. dp[i-1]就暗示了假设nums[i]和 nums[i-1]变
        ==》 k - nums[i]： 表示修改i项到达k值需要的次数
        ==》 dp[i] = min{dp[i-3], dp[i-2], dp[i-1]} + max{0, k - nums[i]}
        */


        // 边界处理：当 i<3 时，dp[i]=max{0,  k−nums[i]}
        // 从左往右遍历，首先计算出前3项组成的子树组 变成 美丽数组 需要的最少改变次数
        for(int i = 0; i < 3; i++){
            dp[i] = Math.max(0, k - nums[i]);
        }

        // 从i=3开始遍历, 每次加入一个新值，与当前i的前3项比较，找到最小的改变次数
        // 然后统计到dp中
        for(int i = 3; i < nums.length; i++){
            long curr = Math.min(Math.min(dp[0], dp[1]), dp[2]) + Math.max(0, k - nums[i]);
            dp[0] = dp[1];
            dp[1] = dp[2];
            dp[2] = curr;
        }

        return Math.min(Math.min(dp[0], dp[1]), dp[2]);
    }
}
