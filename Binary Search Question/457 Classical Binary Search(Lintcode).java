/* Lintcode 457 Classical Binary Search
Description
Find any position of a target number in a sorted array. Return -1 if target does not exist.

Example 1:
Input: nums = [1,2,2,4,5,5], target = 2
Output: 1 or 2

Example 2:
Input: nums = [1,2,2,4,5,5], target = 6
Output: -1

Challenge
O(logn) time
*/

//Thought: Basic Binary Serach Question
//Time: O(logn) Space: O(1)
public class Solution {
    /**
     * @param nums: An integer array sorted in ascending order
     * @param target: An integer
     * @return: An integer
     */
    public int findPosition(int[] nums, int target) {
        // write your code here
        int left = 0;
        int right = nums.length - 1;
        int res = 0;

        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] == target){
                return mid;
            } else if (nums[mid] < target){
                left = mid + 1;
            } else{
                right = mid - 1;
            }
        }

        return -1;
    }
}
