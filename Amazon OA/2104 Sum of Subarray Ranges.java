/* Leetcode 2104. Sum of Subarray Ranges
You are given an integer array nums. The range of a subarray of nums is the difference between the largest and smallest element in the subarray.
Return the sum of all subarray ranges of nums.
A subarray is a contiguous non-empty sequence of elements within an array.

Example 1:
Input: nums = [1,2,3]
Output: 4
Explanation: The 6 subarrays of nums are the following:
[1], range = largest - smallest = 1 - 1 = 0 
[2], range = 2 - 2 = 0
[3], range = 3 - 3 = 0
[1,2], range = 2 - 1 = 1
[2,3], range = 3 - 2 = 1
[1,2,3], range = 3 - 1 = 2
So the sum of all ranges is 0 + 0 + 0 + 1 + 1 + 2 = 4.

Example 2:
Input: nums = [1,3,3]
Output: 4
Explanation: The 6 subarrays of nums are the following:
[1], range = largest - smallest = 1 - 1 = 0
[3], range = 3 - 3 = 0
[3], range = 3 - 3 = 0
[1,3], range = 3 - 1 = 2
[3,3], range = 3 - 3 = 0
[1,3,3], range = 3 - 1 = 2
So the sum of all ranges is 0 + 0 + 0 + 2 + 0 + 2 = 4.

Example 3:
Input: nums = [4,-2,-3,4,1]
Output: 59
Explanation: The sum of all subarray ranges of nums is 59.
 

Constraints:
1 <= nums.length <= 1000
-109 <= nums[i] <= 109
*/

//Solution 1: brute force solution
class Solution {
    public long subArrayRanges(int[] nums) {
        long res = 0;
        //Traverse all the element from nums
        for(int i = 0; i < nums.length - 1; i++){
            int maxValue = nums[i];
            int minValue = nums[i];
            ////Traverse all the element from nums and add the sum
            for(int j = i+1; j < nums.length; j++){
                maxValue = Math.max(maxValue, nums[j]);
                minValue = Math.min(minValue, nums[j]);
                res = res + (maxValue- minValue);
            }
        }   
        return res;
    }
}
