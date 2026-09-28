/*

You are given an integer array nums and an integer x. In one operation, you can either remove the leftmost or the rightmost element from the array nums and subtract its value from x. 

Note that this modifies the array for future operations.

Return the minimum number of operations to reduce x to exactly 0 if it is possible, otherwise, return -1.

Example 1:
Input: nums = [1,1,4,2,3], x = 5
Output: 2
Explanation: The optimal solution is to remove the last two elements to reduce x to zero.

Example 2:

Input: nums = [5,6,7,8,9], x = 4
Output: -1

Example 3:

Input: nums = [3,2,20,1,1,3], x = 10
Output: 5
Explanation: The optimal solution is to remove the last three elements and the first two elements (5 operations in total) to reduce x to zero.
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 104
1 <= x <= 109


*/

/**
 * @param {number[]} nums
 * @param {number} x
 * @return {number}
 */
// 因为是选择移除数组 nums 最左边或最右边的元素，所以不能从左边开始进行滑动窗口找到满足总和为x的最短子数组
// 所以逆向思维：从左边开始遍历双指针滑动窗口，找到nums中 “最长” 的子数组，并且总和 为 nums总和 - x 
// ==> 这样可以保证我们每次是删除最左边或者最右边的元素，得到一个总和为x的数组，并且是最短的步骤
var minOperations = function(nums, x) {
    let total = 0;

    for(let num of nums){
        total +=  num;
    }

    // 无法让x变成0
    if(total <  x){
        return -1;
    }

    // 从左边开始遍历双指针滑动窗口，找到nums中 “最长” 的子数组，并且总和 为 nums总和 - x 
    let target = total - x;

    let left =  0;
    let rangeSum = 0;
    let maxLength = -1;

    for(let right = 0; right < nums.length; right++){
        rangeSum += nums[right];  //统计窗口内的总和sum

        while(rangeSum > target){
            rangeSum -= nums[left];
            left++;
        }

        if(rangeSum === target){
            maxLength = Math.max(maxLength, right - left + 1);
        }
    }  

    return maxLength === -1 ? -1 : nums.length - maxLength;
};
