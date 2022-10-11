/* Lintcode 14 · First Position of Target

Description
Given a sorted array (ascending order) and a target number, find the first index of this number in O(log n)O(logn) time complexity.
If the target number does not exist in the array, return -1.

Example 1:
Input:
tuple = [1,4,4,5,7,7,8,9,9,10]
target = 1
Output:
0
Explanation:
The first index of 1 is 0.

Example 2:
Input:
tuple = [1, 2, 3, 3, 4, 5, 10]
target = 3
Output:
2
Explanation:
The first index of 3 is 2.
*/

public class Solution {
    /**
     * @param nums: The integer array.
     * @param target: Target to find.
     * @return: The first position of target. Position starts from 0.
     */
    public int binarySearch(int[] nums, int target) {
        // write your code here
        int left = 0;
        int right = nums.length;

        while(left < right){ //[left, right)
            int mid = (right - left) / 2 + left;
            if(nums[mid] >= target){
                right = mid; //[left, right), so when nums[mid] >= target, right = mid means remove mid position out of area
            } else if(nums[mid] < target){
                left = mid + 1;
            }
        }

        if(nums[right] == target){
            return right;
        }

        return -1;
    }
}
