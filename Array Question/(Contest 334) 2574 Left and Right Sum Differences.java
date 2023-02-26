/* Leetcode 2574. Left and Right Sum Differences

Given a 0-indexed integer array nums, find a 0-indexed integer array answer where:
answer.length == nums.length.
answer[i] = |leftSum[i] - rightSum[i]|. Where:

  leftSum[i] is the sum of elements to the left of the index i in the array nums. If there is no such element, leftSum[i] = 0.
  
  rightSum[i] is the sum of elements to the right of the index i in the array nums. If there is no such element, rightSum[i] = 0.
  
Return the array answer.

 
Example 1:

Input: nums = [10,4,8,3]
Output: [15,1,11,22]
Explanation: The array leftSum is [0,10,14,22] and the array rightSum is [15,11,3,0].
The array answer is [|0 - 15|,|10 - 11|,|14 - 3|,|22 - 0|] = [15,1,11,22].

Example 2:

Input: nums = [1]
Output: [0]
Explanation: The array leftSum is [0] and the array rightSum is [0].
The array answer is [|0 - 0|] = [0].
 

Constraints:

1 <= nums.length <= 1000
1 <= nums[i] <= 105
*/
//My Solution:
class Solution {
    public int[] leftRigthDifference(int[] nums) {
        //base case
        if(nums.length == 1){
            return new int[]{0};
        }
        
        //count the leftSum sum of every element
        int[] leftSum =  new int[nums.length];
        leftSum[0] = 0;
        leftSum[1] = nums[0];
        for(int i = 2; i < nums.length; i++){
            leftSum[i] = leftSum[i - 1] + nums[i - 1]; 
        }
        
        //count the righSum sum of every element
        int[] rightSum = new int[nums.length];
        rightSum[nums.length - 1] = 0;
        rightSum[nums.length - 2] = nums[nums.length - 1];
        for(int i = nums.length - 3; i >= 0; i--){
            rightSum[i] = rightSum[i + 1] + nums[i + 1];
        }
        
        //count the final result of every element
        for(int i = 0; i < nums.length; i++){
            nums[i] = Math.abs(leftSum[i] - rightSum[i]);
        }
        
        return nums;
    }
}

//Solution 2 (Times faster):
class Solution {
    public int[] leftRigthDifference(int[] nums) {
        //count the leftSum sum of every element
        int[] leftSum =  new int[nums.length];
        for(int i = 1; i < nums.length; i++){
            leftSum[i] = leftSum[i - 1] + nums[i - 1]; 
        }
        
        //count the righSum sum of every element
        int[] rightSum = new int[nums.length];
        for(int i = nums.length - 2; i >= 0; i--){
            rightSum[i] = rightSum[i + 1] + nums[i + 1];
        }
        
        //count the final result of every element
        int[] res = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            res[i] = Math.abs(leftSum[i] - rightSum[i]);
        }
        
        return res;
    }
}
