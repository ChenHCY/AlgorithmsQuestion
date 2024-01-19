/* Leetcode 238. Product of Array Except Self

Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].

The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

You must write an algorithm that runs in O(n) time and without using the division operation.

Example 1:
Input: nums = [1,2,3,4]
Output: [24,12,8,6]

Example 2:
Input: nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]
 
Constraints:
2 <= nums.length <= 105
-30 <= nums[i] <= 30
The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
*/


//Correct solution: ===> Time: O(n)  Space: O(1)
class Solution {
    // 一个数字的左边部分 * 右边部分 = 除这个数字之外所有数字的乘积
    // 所以两个for-loop 一个计算每个数字 左边的乘积，一个计算 右边的乘积
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];

        res[0] = 1; //因为是计算每个数 左边所有数字的乘积，第一个数字没有左边数字，所以直接设定为1
        for(int i = 1; i < n; i++){
            res[i] = res[i - 1] * nums[i - 1];
        }

        //再从右边开始计算乘积
        int R = 1; //因为是计算每个数 右边所有数字的乘积，然后再和之前得到左边乘积 进行乘积总和
        //因为是最右边的数字，所以直接设定为1
        for(int j = n - 1; j >= 0; j--){
            res[j] *= R;
            R *= nums[j]; //每次更新，因为指针往右移动，会增加右边的数字
        }

        return res;
    }
}
