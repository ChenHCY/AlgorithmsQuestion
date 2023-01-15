/* Leetcode 2529. Maximum Count of Positive Integer and Negative Integer

Given an array nums sorted in non-decreasing order, return the maximum between the number of positive integers and the number of negative integers.

In other words, if the number of positive integers in nums is pos and the number of negative integers is neg, then return the maximum of pos and neg.
Note that 0 is neither positive nor negative.

Example 1:

Input: nums = [-2,-1,-1,1,2,3]
Output: 3
Explanation: There are 3 positive integers and 3 negative integers. The maximum count among them is 3.

Example 2:

Input: nums = [-3,-2,-1,0,0,1,2]
Output: 3
Explanation: There are 2 positive integers and 3 negative integers. The maximum count among them is 3.

Example 3:

Input: nums = [5,20,66,1314]
Output: 4
Explanation: There are 4 positive integers and 0 negative integers. The maximum count among them is 4.
 

Constraints:

1 <= nums.length <= 2000
-2000 <= nums[i] <= 2000
nums is sorted in a non-decreasing order.
 

Follow up: Can you solve the problem in O(log(n)) time complexity?
*/

//My Solution: Time: O(n)   Space: O(1)
class Solution {
    public int maximumCount(int[] nums) {
        int positiveNumber = 0;
        int negativeNumber = 0;
        
        for(int i = 0; i < nums.length; i++){
            if(nums[i] > 0){
                positiveNumber += 1;
            }
            
            if(nums[i] < 0){
                negativeNumber += 1;
            }
        }
        
        return negativeNumber > positiveNumber ? negativeNumber : positiveNumber;
    }
}

//The fast Solution ==> Used Binary Search Method Time:  O(log(n))   Space: O(1)
class Solution {
    public int maximumCount(int[] nums) {
        //base case
        if (nums[0] > 0 || nums[nums.length - 1] < 0) {
            return nums.length;
        }

        int positiveNumber = 0;
        int negativeNumber = 0;
        
        //Binary Search Method pointer
        int left = 0;
        int right = nums.length - 1;
        
        //find the first element is not negative
        while(left < right){
            int mid = left + (right - left) / 2;
            
            if(nums[mid] < 0){
                left = mid + 1;
            } else{
                right = mid;
            }
        }
        //use the position to count how may the negative numbers in the nums[] array
        negativeNumber = left;
        
        //find the first positive element position without value is 0
        while (left < nums.length && nums[left] == 0) {
             left++;
        }
        //use the position to count how may the positive numbers in the nums[] array
        positiveNumber = nums.length - left; 
        
        return negativeNumber > positiveNumber ? negativeNumber : positiveNumber;
    }
}
