/* Leetcode 410. Split Array Largest Sum

Given an integer array nums and an integer k, split nums into k non-empty subarrays such that the largest sum of any subarray is minimized.

Return the minimized largest sum of the split.

A subarray is a contiguous part of the array.

Example 1:

Input: nums = [7,2,5,10,8], k = 2
Output: 18
Explanation: There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8], where the largest sum among the two subarrays is only 18.


Example 2:

Input: nums = [1,2,3,4,5], k = 2
Output: 9
Explanation: There are four ways to split nums into two subarrays.
The best way is to split it into [1,2,3] and [4,5], where the largest sum among the two subarrays is only 9.
 

Constraints:

1 <= nums.length <= 1000
0 <= nums[i] <= 106
1 <= k <= min(50, nums.length)
*/

/*
思路： 此题是找到nums[]中拆分成K个数组，能形成的最小的数组最大值

“使……最大值尽可能小” 是二分搜索题目常见的问法。

因为数组的顺序不能改变，所以我们通过二分法来遍历查询，通过这个数值(mid)拆分，是不是符合K个数组的要求

所以在这个题目中，当我们选定一个值 x，我们可以线性地验证是否存在一种分割方案，满足其最大分割子数组和不超过 x。策略如下：

贪心地模拟分割的过程，从前到后遍历数组，sum表示当前分割子数组的和，count表示已经分割出的子数组的数量（包括当前子数组），

==> 那么每当sum加上当前值超过了 mid，
==> 我们就把当前取的值作为新的一段分割子数组的开头，并将 count 加 1。遍历结束后验证是否 count 不超过 K。

如果使用这个值来拆分数组的数量，小于等于K，则表示符合要求

最后一个符合这个要求的数值(mid)，就是拆分K个数组后，最小的最大值
*/
//时间复杂度：O(n×log(sum−maxNumber))
//空间复杂度：O(1)
class Solution {
    public int splitArray(int[] nums, int k) {
        //此题是找到nums[]中符合要求的最小的数组最大值
        //因为数组的顺序不能改变，所以我们通过二分法来遍历查询，通过这个数值(mid)拆分，是不是符合K个数组的要求
        //最后一个符合要求的数值(mid)，就是拆分K个数组后，最小的最大值
        int sum = 0;
        int max = 0;

        //首先找到二分法的左右区间。因为拆分之后的数组最少一个数值，最多全部数字
        //所以左区间（小区间）为最大数字的值，右区间为全部数字的sum总和
        for(int num : nums){
            sum += num;
            if(num > max){
                max = num;
            }
        }

        //Start Binary Serach
        int left = max;
        int right = sum;
        while(left < right){
            int mid = left + (right - left) / 2;

            ////检查mid的值是否符合题目的拆分数组的数量要求
            if(check(nums, mid, k)){ //符合
                right = mid;
            } else{//不符合
                left = mid + 1;
            }
        }

        return left; // 输出符合要求最小的数组最大值
    }

    //检查mid的值是否符合题目的拆分数组的数量要求
    public static boolean check(int[] nums, int mid, int k){
        int count = 1;
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            if(sum + nums[i] > mid){ //如果大于了mid值，则需要拆分数组
                count++; //统计拆分数组的数量
                sum = nums[i]; //更新sum的值
            } else{
                sum += nums[i]; //计算每个拆分数组的sum
            }
        }

        //如果使用这个值来拆分数组的数量，小于等于K，则表示符合要求
        if(count <= k){
            return true;
        }

        return false;
    }
}
