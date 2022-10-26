/*189. Rotate Array
Given an array, rotate the array to the right by k steps, where k is non-negative.

Example 1:
Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]

Example 2:
Input: nums = [-1,-100,3,99], k = 2
Output: [3,99,-1,-100]

Explanation: 
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]
*/

//when need to keep the same array length, and array loop, So we need to use '%' to find the element position that needs to swap and reverse
//Time: O(n)   Space: O(1)
class Solution {
    public void rotate(int[] nums, int k) {
        int step = k % nums.length; //count how many step will rotates
        //for example: nums = [1,2,3,4,5,6,7] k = 3
        reverse(nums, 0, nums.length - 1); //1. nums = [7,6,5,4,3,2,1]
        reverse(nums, 0, step - 1); //2. nums = [5,6,7,4,3,2,1]
        reverse(nums, step, nums.length - 1); //3. nums = [5,6,7,1,2,3,4]
    }
    
    //swap function used to swap two number for nums[] array
    public static void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    //reverse function used to reverse the range of nums[] array
    public static void reverse(int[] nums, int left, int right){
        while(left < right){
            swap(nums, left, right);
            left++;
            right--;
        }
    }
}
