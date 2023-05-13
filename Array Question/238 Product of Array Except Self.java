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

//My thought:  ===> Wrong and out of the time
class Solution {
    public int[] productExceptSelf(int[] nums) {
        List<Integer> list = new ArrayList<>();
        boolean[] remove = new boolean[nums.length];
        
        for(int i = 0; i < nums.length; i++){
            remove[i] = true;
        }
        
        for(int i = 0; i < nums.length; i++){
            int temp = 1;
            remove[i] = false;
            for(int j = 0; j < nums.length; j++){
                if(remove[j]){
                    temp *= nums[j];
                }
            }
            remove[i] = true;
            list.add(temp);
        }
        
        int[] res = new int[list.size()];
        
        for(int i = 0; i < list.size(); i++){
            res[i] = list.get(i);
        }
        
        return res;
    }
}

//Correct solution: ===> Time: O(n)  Space: O(N)
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        res[0] = 1;
        int right = 1;

        //首先从左往右遍历一次，进行乘法
        //==》计算当前number，左边的部分的乘法
        for(int i = 1; i < nums.length; i++){
            res[i] = res[i - 1] * nums[i - 1];
        }

        //再从右往左遍历一次，进行乘法
        //==》计算当前number, 右边部分的乘法
        for(int j = n - 1; j >= 0; j--){
            res[j] *= right;
            right *= nums[j];
        }

        return res;
    }
}
