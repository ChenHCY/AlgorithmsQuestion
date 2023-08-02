/*Leetcode 1248. Count Number of Nice Subarrays

Given an array of integers nums and an integer k. A continuous subarray is called nice if there are k odd numbers on it.

Return the number of nice sub-arrays.

Example 1:

Input: nums = [1,1,2,1,1], k = 3
Output: 2
Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].

Example 2:

Input: nums = [2,4,6], k = 1
Output: 0
Explanation: There is no odd numbers in the array.

Example 3:

Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
Output: 16
 

Constraints:

1 <= nums.length <= 50000
1 <= nums[i] <= 10^5
1 <= k <= nums.length

*/

/* 解题思路：因为是统计满足要求的子数组数量，子数组是值数组中元素的连续子集。
            ==》所以中间是不能截断的，我们只需要找到 每个存在k个奇数的 silidng window 然后统计其前后的可以组成的数组
            
若当前滑动窗口包含了 k 个奇数： =》 中间是不能截断的

  1. 统计第 1 个奇数 左边的偶数个数 leftEvenCount, 这些 leftEvenCount 都可以作为 优美子数组的起点，此起点的选择有 leftEvenCount + 1 种（因为可以一个偶数都不取）
  2. 统计第 k 个奇数 右边的偶数个数 rightEvenCount, 这些 rightEvenCount 都可以作为 优美子数组的起点，此起点的选择有 rightEvenCount + 1 种（因为可以一个偶数都不取）

==》 因此「优美子数组」左右起点的选择组合数为 (leftEvenCount + 1) * (rightEvenCount + 1)
  */
//Time: O(N)   Space: O(1)
class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        //双指针，right指针向右扩，然后计算每个滑动窗口里面奇数的数量
        // 不断右移 right 指针来扩大滑动窗口，使其包含 k 个奇数；
        int left = 0;
        int right = 0;
        
        int n = nums.length;
        int oddCount = 0; //统计odd奇数的数量
        int res = 0; //结果 有K个奇数 子数组的数量

        while(right < n){
            //不断右移 right 指针来扩大滑动窗口，使其包含 k 个奇数
            if(nums[right] % 2 == 1){
                oddCount++;
            }

            right++;

            // 若当前滑动窗口 [left, right) 中有 k 个奇数了，统计当前滑动窗口中的优美子数组个数。
            if(oddCount == k){
                //统计当前滑动窗口右边界之后的偶数数量，直到遇到下一个奇数（或出界）
                int currRight = right;
                while(right < n && (nums[right] % 2 == 0)){ //直到遇到下一个奇数（或出界）
                    right++;
                }

                int rightEvenCount = right - currRight; // 得到第K个奇数 右边 偶数的数量

                // 统计当前滑动窗口左边界之后的偶数数量，从0开始 到遇见左边界结束
                int leftEvenCount = 0;
                while(nums[left] % 2 == 0){
                    leftEvenCount++;
                    left++; //left最后到达第一个奇数
                }

                res += (leftEvenCount + 1) * (rightEvenCount + 1); //统计满足要求的子数组数量

                // 此时 left 指向的是第 1 个奇数，因为该区间已经统计完了，因此 left 右移一位，oddCnt--
                left++;
                oddCount--;
            }
        }

        return res;
    }
}
