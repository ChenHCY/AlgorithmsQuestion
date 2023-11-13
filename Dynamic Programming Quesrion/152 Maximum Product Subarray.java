/* Leetcode 152. Maximum Product Subarray

Given an integer array nums, find a subarray that has the largest product, and return the product.

The test cases are generated so that the answer will fit in a 32-bit integer.

Example 1:

Input: nums = [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.

Example 2:

Input: nums = [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 

Constraints:

1 <= nums.length <= 2 * 104
-10 <= nums[i] <= 10
The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
*/
//Time: O(n)  Space: O(1)
class Solution {
    // 找到nums[]数组中 子数组的最大乘积，子数组表示必须是连续的部分
    // Dynamic Programming: 当前值只依赖于前面的部分
    public int maxProduct(int[] nums) {
        //base case
        if(nums.length == 0 || nums == null){
            return 0;
        }

        // max: 记录以 nums[i] 为结尾的「最大子数组乘积和」
        // min: 记录以 nums[i] 为结尾的「最小子数组乘积和」
        // nums[i]: 遍历到当前index的数值

        int max = nums[0];
        int min = nums[0];
        int res = nums[0];
        
        for(int i = 1; i < nums.length; i++){
            int temp = max; //记录max的值，避免后续发生改变
            max = Math.max(Math.max(max * nums[i], min * nums[i]), nums[i]);
            min = Math.min(Math.min(temp * nums[i], min * nums[i]), nums[i]);

            res = Math.max(res, max); //比较得到最大的子数组乘积
        }

        return res;
    }
}
