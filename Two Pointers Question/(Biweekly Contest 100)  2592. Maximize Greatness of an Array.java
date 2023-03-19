/*Leetcode (Biweekly Contest 100)  2592. Maximize Greatness of an Array

You are given a 0-indexed integer array nums. You are allowed to permute nums into a new array perm of your choosing.

We define the greatness of nums be the number of indices 0 <= i < nums.length for which perm[i] > nums[i].

Return the maximum possible greatness you can achieve after permuting nums.

Example 1:

Input: nums = [1,3,5,2,1,3,1]
Output: 4
Explanation: One of the optimal rearrangements is perm = [2,5,1,3,3,1,1].
At indices = 0, 1, 3, and 4, perm[i] > nums[i]. Hence, we return 4.


Example 2:

Input: nums = [1,2,3,4]
Output: 3
Explanation: We can prove the optimal perm is [2,3,4,1].
At indices = 0, 1, and 2, perm[i] > nums[i]. Hence, we return 3.
 

Constraints:

1 <= nums.length <= 105
0 <= nums[i] <= 109
*/


 //Two pointer Method
  /* 思路： perm[] 是 nums[] 重新排序之后的array
        因为要尽可能多的 让排序之后的 perm[i] > nums[i]
        所以我们将尝试将最小的 A[i] 放在左边。
        
        然后开始遍历，并检查它是否大于 A[index]。
        如果大于，则统计数量，并且移动index指针。
*/


class Solution {
    public int maximizeGreatness(int[] nums) {
        //sort the nums[] array first
        Arrays.sort(nums);
        int index = 0;
        int res = 0;

        for(int a : nums){
            if(a >  nums[index]){ //perm[i] > nums[i]
                res++;
                index++;
            }
        }

        return res;
    }
}
