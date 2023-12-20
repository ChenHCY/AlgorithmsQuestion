/* 1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit

Given an array of integers nums and an integer limit, return the size of the longest non-empty subarray such that the 
absolute difference between any two elements of this subarray is less than or equal to limit.

Example 1:
Input: nums = [8,2,4,7], limit = 4
Output: 2 
Explanation: All subarrays are: 
[8] with maximum absolute diff |8-8| = 0 <= 4.
[8,2] with maximum absolute diff |8-2| = 6 > 4. 
[8,2,4] with maximum absolute diff |8-2| = 6 > 4.
[8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
[2] with maximum absolute diff |2-2| = 0 <= 4.
[2,4] with maximum absolute diff |2-4| = 2 <= 4.
[2,4,7] with maximum absolute diff |2-7| = 5 > 4.
[4] with maximum absolute diff |4-4| = 0 <= 4.
[4,7] with maximum absolute diff |4-7| = 3 <= 4.
[7] with maximum absolute diff |7-7| = 0 <= 4. 
Therefore, the size of the longest subarray is 2.

Example 2:
Input: nums = [10,1,2,4,7,2], limit = 5
Output: 4 
Explanation: The subarray [2,4,7,2] is the longest since the maximum absolute diff is |2-7| = 5 <= 5.

Example 3:
Input: nums = [4,2,2,2,4,4,2,2], limit = 0
Output: 3
 

Constraints:
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
0 <= limit <= 10^9
*/
// 时间复杂度：O(nlog⁡n)，其中 n 是数组长度。向有序集合中添加或删除元素都是 O(log⁡n) 的时间复杂度。每个元素最多被添加与删除一次。
// 空间复杂度：O(n) 其中 n 是数组长度。最坏情况下有序集合将和原数组等大。
class Solution {
    // 因为要找到最长的子数组，且子数组内任意两个数的差不超过limit
    // ==>也就是检查最大值-最小值 是否超过 limit
    public int longestSubarray(int[] nums, int limit) {
        int n = nums.length;
        int res = 0;
        int left = 0;

        //使用TreeMap记录滑动窗口里面的num组成，可以更容易的找到最大值 和 最小值
        TreeMap<Integer, Integer> map = new TreeMap<>();

        for(int right = 0; right < n; right++){
            // 记录窗口内 每个num的出现次数
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);
            // 检查窗口内最大的查(最大值 - 最小值)是否大于limit
            while(map.lastKey() - map.firstKey() > limit){
               map.put(nums[left], map.getOrDefault(nums[left], 0) - 1);
               //如果出现次数为0，删除left对应的值
               if (map.get(nums[left]) == 0) {
                    map.remove(nums[left]);
                }
               left++;
            }
            res = Math.max(res, right - left + 1);
        }

        return res;
    }
}
