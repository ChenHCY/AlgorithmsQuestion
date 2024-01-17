/* 487. Max Consecutive Ones II

Given a binary array nums, return the maximum number of consecutive 1's in the array if you can flip at most one 0.

Example 1:
Input: nums = [1,0,1,1,0]
Output: 4
Explanation: 
- If we flip the first zero, nums becomes [1,1,1,1,0] and we have 4 consecutive ones.
- If we flip the second zero, nums becomes [1,0,1,1,1] and we have 3 consecutive ones.
The max number of consecutive ones is 4.

Example 2:
Input: nums = [1,0,1,1,0,1]
Output: 4
Explanation: 
- If we flip the first zero, nums becomes [1,1,1,1,0,1] and we have 4 consecutive ones.
- If we flip the second zero, nums becomes [1,0,1,1,1,1] and we have 4 consecutive ones.
The max number of consecutive ones is 4.
 

Constraints:
1 <= nums.length <= 10^5
nums[i] is either 0 or 1.
*/

//Time: O(logn)  Space: O(1)
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int maxLen = 0;
        int left = 0;
        int countZero = 0;
        int n = nums.length;

        for(int right = 0; right < n; right++){
            if(nums[right] == 0){
                countZero++;
            }

            while(countZero > 1){
                if(nums[left] == 0){
                    countZero--;
                }
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}
