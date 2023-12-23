/* 1658. Minimum Operations to Reduce X to Zero

You are given an integer array nums and an integer x. In one operation, you can either remove the leftmost or the rightmost 
element from the array nums and subtract its value from x. Note that this modifies the array for future operations.

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
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^4
1 <= x <= 10^9
*/

// 因为是选择移除数组 nums 最左边或最右边的元素，所以不能从左边开始进行滑动窗口找到满足总和为x的最短子数组
// 所以逆向思维：从左边开始遍历双指针滑动窗口，找到nums中 “最长” 的子数组，并且总和 为 nums总和 - x 
// ==> 这样可以保证我们每次是删除最左边或者最右边的元素，得到一个总和为x的数组，并且是最短的步骤

//Time: O(n): ，其中 n 为 nums 的长度。虽然写了个二重循环，但是 left++ 的执行次数不会超过  n 次，所以总的时间复杂度为 O(n)
//Space: O(1)
class Solution {
    public int minOperations(int[] nums, int x) {
        int res = Integer.MIN_VALUE;
        int n = nums.length;
        int nSum = 0;

        for(int num : nums){
            nSum += num;
        }

        int target = nSum - x;

        if(target < 0){
            return -1; //表示无法从nums按要求得到总和为x的数组
        }

        int left = 0;
        int windowSum = 0;

        for(int right = 0; right < n; right++){
            windowSum += nums[right]; //统计窗口内的总和sum

            //如果滑动窗口的sum值大于了target了，缩小窗口
            while(windowSum > target){
                windowSum -= nums[left];
                left++;
            }

            //如果当前窗口(子数组)的总和等于x，更新长度
            if(windowSum == target){
                res = Math.max(res, right - left + 1);
            }
        }
        
        //如果找不到总和为 nSum - x的子数组，输出-1
        return res == Integer.MIN_VALUE ? -1: n - res;
    }
}
