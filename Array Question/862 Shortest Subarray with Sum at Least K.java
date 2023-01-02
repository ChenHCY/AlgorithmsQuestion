/* Leetcode 862. Shortest Subarray with Sum at Least K

Given an integer array nums and an integer k, return the length of the shortest non-empty 
subarray of nums with a sum of at least k. If there is no such subarray, return -1.

A subarray is a contiguous part of an array.


Example 1:

Input: nums = [1], k = 1
Output: 1

Example 2:

Input: nums = [1,2], k = 4
Output: -1

Example 3:

Input: nums = [2,-1,2], k = 3
Output: 3
 

Constraints:

1 <= nums.length <= 105
-105 <= nums[i] <= 105
1 <= k <= 109
*/

//My Solution: Two Ponter ====> Wrong (Time Limit Exceeded)
class Solution {
    public int shortestSubarray(int[] nums, int k) {
        int start = -1;
        int end = -1;
        int minLength = Integer.MAX_VALUE;

        for(int i = 0; i < nums.length; i++){
            int currSum = 0;
            for(int j = i; j < nums.length && (j - i + 1) < minLength; j++){
                currSum += nums[j];
                if(currSum >= k){
                    start = i;
                    end = j;
                    minLength = end - start + 1;
                }
            }
        }

        if(start == -1 || end == -1){
            return -1;
        }

        return minLength;
    }
}
