/* Leetcode 560. Subarray Sum Equals K
Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.

A subarray is a contiguous non-empty sequence of elements within an array.

Example 1:
Input: nums = [1,1,1], k = 2
Output: 2

Example 2:
Input: nums = [1,2,3], k = 3
Output: 2
 
Constraints:
1 <= nums.length <= 2 * 104
-1000 <= nums[i] <= 1000
-107 <= k <= 107
*/

class Solution {
    public int subarraySum(int[] nums, int k) {
        // We Define pre[i] means the sum value of [0..i]
        // So pre[i] = pre[i-1] + nums[i];
        // ==> in this solution we need find the sum value of area [j..i] is k
        // So pre[i] - pre[j-1] = k
        // So pre[j-1] = pre[i] - k
        // We just need to count there is how many pre[j-1] for every element in the nums[] list
      
        int res = 0;
        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
    
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            int temp = sum - k;
            
            if(map.containsKey(temp)){
                res += map.get(temp);
            }
            
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        
        return res;
    }
}
