/* 2909. Minimum Sum of Mountain Triplets II

You are given a 0-indexed array nums of integers.

A triplet of indices (i, j, k) is a mountain if:

    · i < j < k
    · nums[i] < nums[j] and nums[k] < nums[j]
Return the minimum possible sum of a mountain triplet of nums. If no such triplet exists, return -1.

Example 1:

Input: nums = [8,6,1,5,3]
Output: 9
Explanation: Triplet (2, 3, 4) is a mountain triplet of sum 9 since: 
- 2 < 3 < 4
- nums[2] < nums[3] and nums[4] < nums[3]
And the sum of this triplet is nums[2] + nums[3] + nums[4] = 9. It can be shown that there are no mountain triplets with a sum of less than 9.

Example 2:

Input: nums = [5,4,8,7,10,2]
Output: 13
Explanation: Triplet (1, 3, 5) is a mountain triplet of sum 13 since: 
- 1 < 3 < 5
- nums[1] < nums[3] and nums[5] < nums[3]
And the sum of this triplet is nums[1] + nums[3] + nums[5] = 13. It can be shown that there are no mountain triplets with a sum of less than 13.

Example 3:

Input: nums = [6,5,4,3,4,5]
Output: -1
Explanation: It can be shown that there are no mountain triplets in nums.
 

Constraints:

3 <= nums.length <= 10^5
1 <= nums[i] <= 10^8
*/
// Time: O(n)  因为这段代码中没有任何嵌套的for-loop, 三个for-loop的时间复杂度都是O(n）
// ==》 所以总的时间复杂度是 O(3n) ==> 常熟可以消除 也就是 O(n)
// Space: O(n) ==> 创建了两个n长度的数组储存 左右部分的最小值

class Solution {
    // 找到总和最小的三元数组，i < j < k && nums[i] < nums[j] and nums[k] < nums[j]
    // 所以我们找到每个index点 左边的最小值 和 右边的最小值
    // 然后再次遍历，计算出总和的最小值
    public int minimumSum(int[] nums) {
        int res = Integer.MAX_VALUE;
        int n = nums.length;
        int[] leftMin = new int[n];
        int[] rightMin = new int[n];

        // 计算每个index点左边的最小值
        int min = nums[0];
        for(int i = 0; i < n; i++){
            leftMin[i] = min; // 记录每个index 左边部分的最小值
            min = Math.min(min, nums[i]); // 每次到达新的index，更新最小值
        }

        // 计算每个index点右边的最小值
        min = nums[n - 1];
        for(int i = n - 1; i >= 0; i--){
            rightMin[i] = min;
            min = Math.min(min, nums[i]);
        }

        // 遍历整个数组，统计每个点 满足 nums[i] < nums[j] and nums[k] < nums[j] 要求的sum总和
        for(int i = 1; i < n - 1; i++){
            if(leftMin[i] < nums[i] && rightMin[i] < nums[i]){
                res = Math.min(res, leftMin[i] + nums[i] + rightMin[i]);
            }
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
