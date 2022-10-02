/* Leetcode 41. First Missing Positive

Given an unsorted integer array nums, return the smallest missing positive integer.
You must implement an algorithm that runs in O(n) time and uses constant extra space.

Example 1:
Input: nums = [1,2,0]
Output: 3
Explanation: The numbers in the range [1,2] are all in the array.

Example 2:
Input: nums = [3,4,-1,1]
Output: 2
Explanation: 1 is in the array but 2 is missing.

Example 3:
Input: nums = [7,8,9,11,12]
Output: 1
Explanation: The smallest positive integer 1 is missing.
 

Constraints:
1 <= nums.length <= 105
-231 <= nums[i] <= 231 - 1
*/

class Solution {
    public int firstMissingPositive(int[] nums) {
        //Time: O(n)  Space: O(1)
        if(nums.length == 0 || nums == null){
            return 1;
        }
        // Bucket sort idea: Exchange and Put every number in correct position
        // then find which one is difference (nums[i] != i + 1)
        /* [0, 1, 2, 3]
           [1, 2, 3, 4] ==> nums[i] = i + 1
           The bucket sort process:
           [3, 1, 4, -1] ==> i = 0  nums[i] = 3  nums[nums[i] - 1] = 2 != 4 ==> so exchange 3 with 4
           [4, 1, 3, -1] ==> i = 0  nums[i] = 4  nums[nums[i] - 1] = 3 != -1 ==> so exchange 4 with -1
           [-1, 1, 3, 4] ==> nums[i] = -1 < 0 move to next step
           [-1, 1, 3, 4] ==> i = 1 nums[i] = 1  nums[nums[i] - 1] = nums[0] = -1 != 1 ==> so exchange -1 with 1
           Exchange and Put every number in correct position: [1, -1, 3, 4]
        */
        
        //Traverse the number list[] to exchange that put every number in correct position
        for(int i = 0; i < nums.length; i++){ 
            //nums[nums[i] - 1] means the nums[i]'s correct position
            while(nums[i] > 0 && nums[i] < nums.length && nums[nums[i] - 1] != nums[i]){
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        
        //After Exchange, Traverse the nums[] list from front to back
        for(int j = 0; j < nums.length; j++){
            if(nums[j] != j + 1){
                return j + 1; //if find which number is not equal i + 1, it's First Missing Positive
            }
        }
        
        //Example [1, 2, 3, 4], the First Missing Positive is next number after nums list
        return nums.length + 1; 
    }
}
