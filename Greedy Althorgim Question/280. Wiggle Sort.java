/* 280. Wiggle Sort

Given an integer array nums, reorder it such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

You may assume the input array always has a valid answer.


Example 1:

Input: nums = [3,5,2,1,6,4]
Output: [3,5,1,6,2,4]
Explanation: [1,6,2,5,3,4] is also accepted.

Example 2:

Input: nums = [6,6,5,6,3,8]
Output: [6,6,5,6,3,8]


Constraints:
1 <= nums.length <= 5 * 10^4
0 <= nums[i] <= 10^4
It is guaranteed that there will be an answer for the given input nums.
*/

// 首先使用arrays.sort() 升序排列
// 然后在nums[] index位置 单数位置的number 进行交换，得到满足条件的数组
// nums[0] <= nums[1] >= nums[2] <= nums[3]...
class Solution {
    //Time: O(n*logn)  Space: O(1)
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);

        //对于单数位置的number，和其下一位进行swap
        for(int i = 0; i < nums.length - 1; i++){
            if(i % 2 == 1){
                swap(nums, i, i + 1);
            }
        }
    }

    //swap the number
    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
