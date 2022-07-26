/* Leetcode 209. Minimum Size Subarray Sum
Given an array of positive integers nums and a positive integer target, return the minimal length of a contiguous subarray [numsl, numsl+1, ..., numsr-1, numsr] 
of which the sum is greater than or equal to target. If there is no such subarray, return 0 instead.

Example 1:
Input: target = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: The subarray [4,3] has the minimal length under the problem constraint.

Example 2:
Input: target = 4, nums = [1,4,4]
Output: 1

Example 3:
Input: target = 11, nums = [1,1,1,1,1,1,1,1]
Output: 0
 
Constraints:

1 <= target <= 109
1 <= nums.length <= 105
1 <= nums[i] <= 104
 
Follow up: If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log(n)).
*/
//Run Time: O(n)
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        //Two pointer Method
        int left = 0;  //first pointer
        int right = 0; //second pointer
        int sum = 0;
        int min = Integer.MAX_VALUE; 
        
        if(nums.length == 0){ 
            return 0;
        }
        //Traverse all the elements, and find every range that sum value >= target value
        while(right <= nums.length - 1){
            sum += nums[right]; //left pointer stay at 0, And move right pointer
            right++; //And move right pointer
            
            while(sum >= target){ //if find the range sum is greater than the target
                min = Math.min(min, right - left); //save the length and compare with prev length
                sum -= nums[left]; //Minus left pointer value
                left++; //then move left pointer and check next value
            }
        }
        
        if(min == Integer.MAX_VALUE){ //if did not find any range value greater or equals target
            return 0; // return 0
        }
        
        return min; //finally return and output Minimum Size
    }
}
