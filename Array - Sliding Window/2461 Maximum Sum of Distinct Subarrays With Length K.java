/* 2461. Maximum Sum of Distinct Subarrays With Length K

You are given an integer array nums and an integer k. Find the maximum subarray sum of all the subarrays of nums that meet the following conditions:

  · The length of the subarray is k, and
  · All the elements of the subarray are distinct.

Return the maximum subarray sum of all the subarrays that meet the conditions. If no subarray meets the conditions, return 0.

A subarray is a contiguous non-empty sequence of elements within an array.

Example 1:
Input: nums = [1,5,4,2,9,9,9], k = 3
Output: 15
Explanation: The subarrays of nums with length 3 are:
- [1,5,4] which meets the requirements and has a sum of 10.
- [5,4,2] which meets the requirements and has a sum of 11.
- [4,2,9] which meets the requirements and has a sum of 15.
- [2,9,9] which does not meet the requirements because the element 9 is repeated.
- [9,9,9] which does not meet the requirements because the element 9 is repeated.
We return 15 because it is the maximum subarray sum of all the subarrays that meet the conditions

Example 2:
Input: nums = [4,4,4], k = 3
Output: 0
Explanation: The subarrays of nums with length 3 are:
- [4,4,4] which does not meet the requirements because the element 4 is repeated.
We return 0 because no subarrays meet the conditions.
 
Constraints:
1 <= k <= nums.length <= 10^5
1 <= nums[i] <= 10^5
*/

//Time: O(N)  Space: O(N)
class Solution {
    // HashMap + 滑动窗口
    // 使用Hashmap记录窗口内有多少不同的数字，如果是多于k的，则比较更新最大子数组和
    public long maximumSubarraySum(int[] nums, int k) {
        long maxSum = 0;
        long sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        
        // 记录第一个窗口，检查有多少个不同的数字
        for(int i = 0; i < k; i++){
            sum += nums[i];
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        //检查是不是有k个不同的数字
        if(map.size() == k){
            maxSum = sum;
        }

        //开始移动滑动窗口
        for(int i = k; i < nums.length; i++){
            //移动右指针
            sum += nums[i];
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);

            //移动左指针
            sum -= nums[i - k];
            int freqLeft = map.get(nums[i - k]); //左指针对应数字在滑动窗口内的数量
          
            if(freqLeft == 1){ //如果数量删除一个之后，为0，则删除在hashmap里删除这个数字
                map.remove(nums[i - k]);
            } else{
                map.put(nums[i - k], map.get(nums[i - k]) - 1);
            }

            //检查新的滑动窗口是否还是k个不同的数字，也就是没有重复元素
            if(map.size() == k){
                maxSum = Math.max(maxSum, sum); //如果符合 比较更新最大值
            }
        }

        return maxSum;
    }
}
