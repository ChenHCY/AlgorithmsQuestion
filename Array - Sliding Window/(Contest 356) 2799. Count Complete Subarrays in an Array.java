/*Leetcode (Contest 356) 2799. Count Complete Subarrays in an Array

You are given an array nums consisting of positive integers.

We call a subarray of an array complete if the following condition is satisfied:

The number of distinct elements in the subarray is equal to the number of distinct elements in the whole array.
Return the number of complete subarrays.

A subarray is a contiguous non-empty part of an array.

Example 1:

Input: nums = [1,3,1,2,2]
Output: 4
Explanation: The complete subarrays are the following: [1,3,1,2], [1,3,1,2,2], [3,1,2] and [3,1,2,2].

Example 2:

Input: nums = [5,5,5,5]
Output: 10
Explanation: The array consists only of the integer 5, so any subarray is complete. The number of subarrays that we can choose is 10.
 

Constraints:

1 <= nums.length <= 1000
1 <= nums[i] <= 2000

*/
//子数组是不能截断，必须连续的数组
class Solution {
    public int countCompleteSubarrays(int[] nums) {
        int n = nums.length;
        int res = 0;
        HashMap<Integer, Integer> totalDistinctMap = new HashMap<>();
        
        //count the number of distinct elements in the whole array
        for(int num : nums){
            totalDistinctMap.put(num, totalDistinctMap.getOrDefault(num, 0) + 1);
        }
        
        int distinctNum = totalDistinctMap.size(); //the total distinct elements in the whole array
        
        // Sliding window using two pointers
        HashMap<Integer, Integer> windowDistinctMap = new HashMap<>(); //used for count the distinct elements of every Sliding window
        int left = 0;
        int right = 0;
        
        while(right < n){
            //count the distinct elements of every Sliding window
            windowDistinctMap.put(nums[right], windowDistinctMap.getOrDefault(nums[right], 0) + 1);
            
            //if find the The number of distinct elements in the subarray is equal to the number of distinct elements in the whole array
            while(windowDistinctMap.size() == distinctNum){
                res += n - right;
                
                int leftNum = nums[left];
                
                //update the left pointer number
                windowDistinctMap.put(leftNum, windowDistinctMap.get(leftNum) - 1);
                if(windowDistinctMap.get(leftNum) == 0){
                    windowDistinctMap.remove(leftNum);
                }
                left++;
            }
            
            right++;
        }
        
        return res;
    }
}
