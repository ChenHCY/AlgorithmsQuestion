/* Leetcode 1004. Max Consecutive Ones III

Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.

Example 1:
Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
Output: 6
Explanation: [1,1,1,0,0,1,1,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.

Example 2:
Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
Output: 10
Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 

Constraints:
1 <= nums.length <= 10^5
nums[i] is either 0 or 1.
0 <= k <= nums.length
*/

//因为只能翻转k个0，找到最长1的长度
//所以我们直接使用滑动窗口保证 每个窗口区间内最多只能有k个0，然后更新比较，得到可能的最长的长度
class Solution {
    public int longestOnes(int[] nums, int k) {
        int left = 0;
        int n = nums.length;
        int maxLen = 0;
        int zeroCount = 0;

        for(int right = 0; right < n; right++){
            if(nums[right] == 0){
                zeroCount++;
            }

            while(zeroCount > k){
                if(nums[left] == 0){
                    zeroCount--;
                }
                left++;
            }

            //更新长度
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}
