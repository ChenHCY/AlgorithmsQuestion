/* 2962. Count Subarrays Where Max Element Appears at Least K Times

You are given an integer array nums and a positive integer k.

Return the number of subarrays where the maximum element of nums appears at least k times in that subarray.

A subarray is a contiguous sequence of elements within an array.

Example 1:
Input: nums = [1,3,2,3,3], k = 2
Output: 6
Explanation: The subarrays that contain the element 3 at least 2 times are: [1,3,2,3], [1,3,2,3,3], [3,2,3], [3,2,3,3], [2,3,3] and [3,3].

Example 2:
Input: nums = [1,4,2,1], k = 3
Output: 0
Explanation: No subarray contains the element 4 at least 3 times.
 

Constraints:
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^6
1 <= k <= 10^5
*/

class Solution {
    // 滑动窗口，找到第一个里面有k个最大值的范围，然后移动左指针和右指针，找到所有可能的子数组
    public long countSubarrays(int[] nums, int k) {
        long res = 0;
        int max = 0; //最大值
        for(int x : nums){
            max = Math.max(max, x);
        }

        int maxCount = 0; // 最大值的数量
        int left = 0; //滑动窗口左区间

        //从左往右遍历，找到第一个里面有k个最大值的范围，
        // 然后移动左指针，直到最大值的出现次数小于k, 把left值加入res, left表示有多少种子数组包含有K个最大值
        // 再移动右指针，加入新的最大值, 继续找k个最大值子数组
        for(int right = 0; right < nums.length; right++){
            if(nums[right] == max){
                maxCount++;
            }

            // 移动左指针，减少当前滑动窗口内最大值的数量
            // 来找到有多少个包括k个最大值的子数组
            while(maxCount == k){
                if(nums[left] == max){
                    maxCount--;
                }     
                left++;
            }

            res += left;
        }   

        return res;
    }
}
