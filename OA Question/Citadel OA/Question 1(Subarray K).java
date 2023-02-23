/* Leetcode 974. Subarray Sums Divisible by K
Given an integer array nums and an integer k, return the number of non-empty 
subarrays that have a sum divisible by k.

A subarray is a contiguous part of an array.

Example 1:
Input: nums = [4,5,0,-2,-3,1], k = 5
Output: 7
Explanation: There are 7 subarrays with a sum divisible by k = 5:
[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]

Example 2:
Input: nums = [5], k = 9
Output: 0
 
Constraints:
1 <= nums.length <= 3 * 104
-104 <= nums[i] <= 104
2 <= k <= 104
*/
//Time: O(n^2) Space: O(n)
class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        //used for save the sum, and remove duplicate
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int count = 0;
        
        map.put(0, 1);
        
        //travser all the element from nums
        for(int num : nums){
            sum = (sum + num) % k; //calculate whether the sum for current range can divisible by k.
            //if the sum is negative
            if(sum < 0){
                sum += k; //change back to the positive
            }
            
            //the numbers of non-empty subarrays that have a sum divisible by k.
            count += map.getOrDefault(sum, 0); 
            map.put(sum, map.getOrDefault(sum, 0) + 1); //put into the hashmap and save with the sum times
        }
        
        return count;
    }
}
