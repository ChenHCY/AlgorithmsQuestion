/* Leetcode 154. Find Minimum in Rotated Sorted Array II

Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,4,4,5,6,7] might become:

    [4,5,6,7,0,1,4] if it was rotated 4 times.
    [0,1,4,4,5,6,7] if it was rotated 7 times.

Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].

Given the sorted rotated array nums that may contain duplicates, return the minimum element of this array.

You must decrease the overall operation steps as much as possible.

Example 1:

Input: nums = [1,3,5]
Output: 1

Example 2:

Input: nums = [2,2,2,0,1]
Output: 0
 

Constraints:
n == nums.length
1 <= n <= 5000
-5000 <= nums[i] <= 5000
nums is sorted and rotated between 1 and n times.
*/

/* 解题思路

二分法的思路，主要还是要明白两个指针怎么移动和缩小范围

rotated：移动最后一位数到第一个，然后依次移动。

因为nums[]是个排序数组通过rotated（反转）得到的，所以可以通过比较nums[mid] 和 右指针的值，来找到可能拥有最小值的一半

1. nums[mid] < nums[right] ==》 说明左半部分可能是值比较小的一半, 移动右指针到mid (因为我们无法确定mid是不是最小值，所以不能删去）

2. nums[mid] > nums[right] ==》 说明右半部分可能是值比较小的一半, 移动左指针到mid + 1, (因为mid对应的值已经大于right所在的值，所以mid没可能是最小的，可以删去）

3. nums[mid] = nums[right] ==》 nums[] array存在重复的number, 所以可以删除一位 然后继续比较。

最后 返回 nums[right]，找到nums[]的最小值
*/

//Time: O(logn)  Space: O(1)
class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while(left < right){
            int mid = left + (right - left) / 2;

            if(nums[mid] < nums[right]){
                right = mid;
            } else if(nums[mid] > nums[right]){
                left = mid + 1;
            } else{ //nums[mid] == nums[right] ==> nums[] array is rotated array with duplicate number
                right -= 1;
            }
        }

        return nums[right];
    }
}
