/*Lintcode 196 · Missing Number

Given an array contains N numbers of 0 .. N, find which number doesn't exist in the array.

Example 1:

Input:[0,1,3]
Output:2

Example 2:

Input:[1,2,3]
Output:0
挑战
Do it in-place with O(1) extra memory and O(n) time.
*/

public class Solution {
    /**
     * @param nums: An array of integers
     * @return: An integer
     */
    public int findMissing(int[] nums) {
        // write your code here
        Arrays.sort(nums);
        int res = 0;

        for(int i = 0; i <= nums.length; i++){
            //checked whether is missed the last number in nums list, 
            if(nums.length == i){
                return i;
            }
            
            if(nums[i] != i){
                return i;
            }
        }

        return -1;
    }
}
