/* Leetcode 16. 3Sum Closest
Given an integer array nums of length n and an integer target, find three integers in nums such that the sum is closest to target.
Return the sum of the three integers.
You may assume that each input would have exactly one solution.


Example 1:
Input: nums = [-1,2,1,-4], target = 1
Output: 2
Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

Example 2:
Input: nums = [0,0,0], target = 1
Output: 0
 

Constraints:
3 <= nums.length <= 1000
-1000 <= nums[i] <= 1000
-104 <= target <= 104
*/

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int len = nums.length;
        int res = 0;
        int minDiff = Integer.MAX_VALUE; // use for find the closest difference
        
        for(int i = 0; i < len; i++){
            int j = i + 1, k = len - 1;
            //Two-pointer Method
            while(j < k){
                int sum = nums[i] + nums[j] + nums[k]; // the 3 sum
                int diff = Math.abs(sum - target); // find the differnce in 3 sum with target
                
                if(diff == 0){ // if diff is 0, so the 100% is target closest 
                    return sum;
                } else if (diff < minDiff){ // find the closest to target sum
                    minDiff = diff;
                    res = sum;
                }
                
                //use this condition judge whether to move k-pointer or j-pointer
                if(sum > target){
                    k--;
                } else{
                    j++;
                }
            }
        }
        
        return res;  
    }
}
