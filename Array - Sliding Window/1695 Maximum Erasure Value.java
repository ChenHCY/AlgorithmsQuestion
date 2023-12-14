/* Leetcode 1695. Maximum Erasure Value

You are given an array of positive integers nums and want to erase a subarray containing unique elements. The score you get by erasing the subarray is equal to the sum of its elements.

Return the maximum score you can get by erasing exactly one subarray.

An array b is called to be a subarray of a if it forms a contiguous subsequence of a, that is, if it is equal to a[l],a[l+1],...,a[r] for some (l,r).

Example 1:
Input: nums = [4,2,4,5,6]
Output: 17
Explanation: The optimal subarray here is [2,4,5,6].

Example 2:
Input: nums = [5,2,1,2,5,2,1,2,5]
Output: 8
Explanation: The optimal subarray here is [5,2,1] or [1,2,5].
 
Constraints:
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^4
*/
class Solution {
    // 滑动窗口：保证窗口内所有数字是不同的，且计算长度
    public int maximumUniqueSubarray(int[] nums) {
        int n = nums.length;
        HashSet<Integer> set = new HashSet<>(); //使用hashset记录数字在窗口的次数
        int max = 0;
        int sum = 0;
        int left = 0;

        //移动右指针 遍历每一个元素
        for(int right = 0; right < n; right++){
            //因为每一个数字只能出现一次
            //如果遍历到已经出现过的数字，移动左指针缩小窗口，直到set为空，或者不在包含当前数字
            while(!set.isEmpty() && set.contains(nums[right])){
                sum -= nums[left];
                set.remove(nums[left]);
                left++;
            }
            sum += nums[right]; //计算当前窗口(子数组)的总和
            set.add(nums[right]); //记录到滑动窗口的组成
            max = Math.max(max, sum); //更新窗口内(子数组)的总和
        }

        return max;
    }
}
