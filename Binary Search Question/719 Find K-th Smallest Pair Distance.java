/* Leetcode 719. Find K-th Smallest Pair Distance

The distance of a pair of integers a and b is defined as the absolute difference between a and b.

Given an integer array nums and an integer k, return the kth smallest distance among all the pairs nums[i] and nums[j] where 0 <= i < j < nums.length.

Example 1:

Input: nums = [1,3,1], k = 1
Output: 0
Explanation: Here are all the pairs:
(1,3) -> 2
(1,1) -> 0
(3,1) -> 2
Then the 1st smallest distance pair is (1,1), and its distance is 0.

Example 2:

Input: nums = [1,1,1], k = 2
Output: 0
Example 3:

Input: nums = [1,6,1], k = 3
Output: 5
 

Constraints:

n == nums.length
2 <= n <= 104
0 <= nums[i] <= 106
1 <= k <= n * (n - 1) / 2
*/
/* 思路：
题意是找到nums[] array中排名第kth的距离对数

注意题目中的关键字：整数数组；==》 数对的「距离」定义为：两个数的差的绝对值。由于输入数组是整数数组，因此数对的距离一定为非负整数；

**** 根据「示例 2」，数组中所有的数都是 1，距离只能为 0，但题目偏偏要问 k = 2，说明：「第 k 小的距离对」不是「第 k 小的不同距离对」。

在搜索范围 [left，right] 里先猜测答案是某个整数 mid（mid = (left + right + 1) / 2），

然后遍历输入数组，遍历输入数组计算所有数对中距离 小于等于 mid 的数对个数。根据这个数对个数与 k 的大小关系，分 3 种

1. 如果小于等于 mid 的距离对的个数 严格小于 k，此时 mid 肯定不是第 k 小的距离，

第 k 小的距离肯定比 mid 大，因此移动左区间，下一轮搜索的区间是 [mid + 1, right]，此时设置 left = mid + 1。

2. 如果小于等于 mid 的距离对的个数 等于 k，此时 mid 有可能是第 k 小的距离。

如果小于等于 mid - 1 的距离对的个数 也等于 k，此时说明 mid 并不出现在输入数组定义的距离中；
如果小于等于 mid - 1 的距离对的个数 严格小于 k，此时可以说 mid 是第 k 小的距离。

因此不可以把 mid 排除掉，下一轮搜索的区间是 [left，mid]，此时设置 right = mid。

3. 如果小于等于 mid 的距离对的个数 严格大于 k，此时 mid 有可能是第 k 小的距离。

但「第 k 小的距离对」不是「第 k 小的不同距离对」。

我们也不能排除mid就是第Kth小的 ==》 所以这种情况mid值也不能被排除掉
*/
class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        //Binary Search + 滑动窗口
        Arrays.sort(nums);
        int n = nums.length;
        int left = 0;
        int right = nums[n - 1] - nums[0];
        
        while(left < right){
            int mid = left + (right - left) / 2;
            //「第 k 小的距离对」不是「第 k 小的不同距离对」。
         //所以这里的mid值 不能舍去
            if(countPairs(nums, mid) >= k){
                right = mid;
            } else{
                left = mid + 1;
            }
        }

        return left;
    }

    //Function to check there is how many pairs distance is small than curr pair
    //子程序 用来统计距离小于等于mid值的对数
 // 如果一个 整数区间 里，「最小数」和「最大数」的差值为 k，那么这个整数区间里的 所有 数对的差值都小于等于 k。
    public static int countPairs(int[] nums, int target){
        int count = 0;
        int len = nums.length;
        for(int left = 0, right = 0; right < len; right++){
            //代表这个对数的距离大于了mid值
            while(nums[right] - nums[left] > target){
                left++;
            }
            // 此时满足 nums[right] - nums[left] <= target
            // right 与 [left..right - 1] 里的每一个元素的「距离」都小于等于 threshold
            // 并且， [left..right - 1] 里元素的个数为 right - left
            count += right - left;
        }

        return count;
    }
}
