/*Lintcode 44 · Minimum Subarray

Given an array of integers, find the continuous subarray with smallest sum.

Return the sum of the subarray.

Example 1:

Input:

array = [1,-1,-2,1]
Output:

-3
Explanation:

The sum of the sub-arrays [-1,-2] is the minimum value -3.

Example 2:

Input:

array = [1,-1,-2,1,-4]
Output:

-6
Explanation:

The sum of the sub-arrays [-1,-2,1,-4] is the minimum value -6.
*/

public class Solution {
    /**
     * @param nums: a list of integers
     * @return: A integer indicate the sum of minimum subarray
     */
    public int minSubArray(List<Integer> nums) {
        // write your code here

        //Subarray子数组: 必须是连续的，且必须有一个以上的数字组成

        //dp[index]: 当遍历到index时，[0,index]范围内的最小子数组和
        int[] dp = new int[nums.size()];
        int res = nums.get(0);

        dp[0] = nums.get(0); //dp初始化

        for(int i = 1; i < nums.size(); i++){
            //计算 比较，遍历到每个位置时，能得到的最小值
            dp[i] = Math.min(dp[i - 1] + nums.get(i), nums.get(i));
            //比较得到整个数组的子数组最小值
            res = Math.min(res, dp[i]);
        }

        return res;
    }
}
