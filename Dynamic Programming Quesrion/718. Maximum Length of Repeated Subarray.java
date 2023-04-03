/* Leetcode 718. Maximum Length of Repeated Subarray

Given two integer arrays nums1 and nums2, return the maximum length of a subarray that appears in both arrays.

Example 1:

Input: nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
Output: 3
Explanation: The repeated subarray with maximum length is [3,2,1].

Example 2:

Input: nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
Output: 5
Explanation: The repeated subarray with maximum length is [0,0,0,0,0].
 

Constraints:

1 <= nums1.length, nums2.length <= 1000
0 <= nums1[i], nums2[i] <= 100
*/

/* 此题和 1143.最长公共子序列 大题一样，但因为此题是求这个题目是统计nums1[] 和 nums2[] 中最长相同的子数组，

==》 所以子数组的要求就是必须是连续的， 当中的元素不能像1143中的子序列一样断开。

假设字符串nums1[] 和 nums2[] 的长度分别为 m 和 n，创建 m + 1 行 n + 1 列的二维数组 dp，
==> 其中 dp[i][j] 表示 nums1[] 和 nums2[] 的最长公共子数组的长度。

 ==> 因为DP动态规划，也就是记忆化搜索，所以存在初始化的前置 dp[0] = 0 
 
 ==> 表示第0位时，nums1[] 和 nums2[] 都为空，不存在任何数字是重复的

 1. Dp 数组 和 下标的含义：下标i - 1为结尾的nums1[]子数组，和以下标j - 1为结尾的nums2[]子数组，
 
 ==》 最长重复子数组长度为dp[i][j]。
 
 2. 递推公式：因为必须是要连续的，所以当遇到不一样的时候，dp[i][j] = 0 ==> 截断DP的记忆化链
 
 ==》 当数字是一样的时候， dp[i][j] = dp[i - 1][j - 1] + 1;
 
 ==》 最后最大的dp[i][j]就是，num1[]和nums2[]重复的最长子数组长度

 3. DP数组的初始化：dp[i][0] = 0 / dp[0][j] = 0 表示在 0-index 位置时，nums1[] 和 nums2[]没有重复的
 
 4. 确定遍历的顺序： 从前往后进行遍历
 
 5. 举例推导DP数组：因为这可以看成一个填表格的遍历，当前格子的值 基于 左上 格子的值, 所以要从前向后，从上到下来遍历这个矩阵。
 
*/

//Time: O(n * m)   Space: O(n)
class Solution {
    public int findLength(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int res = 0;
        int[][] dp = new int[m + 1][n + 1];

        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){ //数字是一样的时候，表示重复
                if(nums1[i - 1] == nums2[j - 1]){ //数字是一样的
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }  //数字不一样的时候， dp[i][j] = 0
                res = Math.max(res, dp[i][j]);
            }
        }

        return res;
    }
}
