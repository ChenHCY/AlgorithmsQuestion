/*Leetcode 303. Range Sum Query - Immutable

Given an integer array nums, handle multiple queries of the following type:

Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.

Implement the NumArray class:
  · NumArray(int[] nums) Initializes the object with the integer array nums.
  · int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).
 

Example 1:

Input
["NumArray", "sumRange", "sumRange", "sumRange"]
[[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
Output
[null, 1, -1, -3]

Explanation
NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
numArray.sumRange(0, 2); // return (-2) + 0 + 3 = 1
numArray.sumRange(2, 5); // return 3 + (-5) + 2 + (-1) = -1
numArray.sumRange(0, 5); // return (-2) + 0 + 3 + (-5) + 2 + (-1) = -3
 

Constraints:

1 <= nums.length <= 10^4
-105 <= nums[i] <= 10^5
0 <= left <= right < nums.length
At most 104 calls will be made to sumRange.

*/
//Time: O(n)  Space: O(1) => 计算数组的前缀和 prefixsum
class NumArray {
    private int[] preSum;
    
    public NumArray(int[] nums) {
        preSum = new int[nums.length + 1];
        preSum[0] = nums[0];
        for(int i = 1; i < nums.length; i++){
           preSum[i] = preSum[i - 1] + nums[i];
        }
    }
    
    public int sumRange(int left, int right) {
        if(left == 0){ //左边界不能越界(小于0)
            return preSum[right];
        }
        return preSum[right] - preSum[left - 1]; //因为left位置的值也要统计进这个区间的总和值
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */
