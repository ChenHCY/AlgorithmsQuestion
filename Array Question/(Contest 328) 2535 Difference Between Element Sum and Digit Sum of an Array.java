/* (Contest 328) Leetcode 2535. Difference Between Element Sum and Digit Sum of an Array

You are given a positive integer array nums.

The element sum is the sum of all the elements in nums.
The digit sum is the sum of all the digits (not necessarily distinct) that appear in nums.
Return the absolute difference between the element sum and digit sum of nums.

Note that the absolute difference between two integers x and y is defined as |x - y|.

Example 1:

Input: nums = [1,15,6,3]
Output: 9
Explanation: 
The element sum of nums is 1 + 15 + 6 + 3 = 25.
The digit sum of nums is 1 + 1 + 5 + 6 + 3 = 16.
The absolute difference between the element sum and digit sum is |25 - 16| = 9.

Example 2:

Input: nums = [1,2,3,4]
Output: 0
Explanation:
The element sum of nums is 1 + 2 + 3 + 4 = 10.
The digit sum of nums is 1 + 2 + 3 + 4 = 10.
The absolute difference between the element sum and digit sum is |10 - 10| = 0.
 

Constraints:

1 <= nums.length <= 2000
1 <= nums[i] <= 2000
*/

class Solution {
    public int differenceOfSum(int[] nums) {
        int res = 0;
        int eleSum = 0;
        int digSum = 0;
        
        for(int i = 0; i < nums.length; i++){
            eleSum += nums[i]; //get the element Sum
            digSum += digitSum(nums[i]); // get the digits sum
        }
        
        res = Math.abs(eleSum - digSum); // The absolute difference
        return res;
    }
    
    //Small function to count the digit Sum of every number
    public static int digitSum(int num){
        int digitS = 0;
        int sum = 0;
        
        while(num > 0){
            digitS = num % 10;
            sum += digitS;
            num = num / 10;
        }
        
        return sum;
    }
}
