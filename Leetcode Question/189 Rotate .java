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

//when need keep same array length, and array loop, maybe need use '%' to find data position 

class Solution {
    public void rotate(int[] nums, int k) {
        int[] res = new int[nums.length]; // create a new a array can save datas
        
        for(int i = 0; i <= nums.length - 1; i++){
            res[(i+k) % nums.length] = nums[i]; // use % to find K postion in the new array
        }
        
        for(int i = 0; i <= nums.length - 1; i++){
            nums[i] = res[i]; // save the new array data into old array data
        }
    }
}
