/* 2903. Find Indices With Index and Value Difference II

You are given a 0-indexed integer array nums having length n, an integer indexDifference, and an integer valueDifference.

Your task is to find two indices i and j, both in the range [0, n - 1], that satisfy the following conditions:

      · abs(i - j) >= indexDifference, and
      · abs(nums[i] - nums[j]) >= valueDifference

Return an integer array answer, where answer = [i, j] if there are two such indices, and answer = [-1, -1] otherwise. If there are multiple choices for the two indices, return any of them.

Note: i and j may be equal.

Example 1:

Input: nums = [5,1,4,1], indexDifference = 2, valueDifference = 4
Output: [0,3]
Explanation: In this example, i = 0 and j = 3 can be selected.
abs(0 - 3) >= 2 and abs(nums[0] - nums[3]) >= 4.
Hence, a valid answer is [0,3].
[3,0] is also a valid answer.

Example 2:

Input: nums = [2,1], indexDifference = 0, valueDifference = 0
Output: [0,0]
Explanation: In this example, i = 0 and j = 0 can be selected.
abs(0 - 0) >= 0 and abs(nums[0] - nums[0]) >= 0.
Hence, a valid answer is [0,0].
Other valid answers are [0,1], [1,0], and [1,1].

Example 3:

Input: nums = [1,2,3], indexDifference = 2, valueDifference = 4
Output: [-1,-1]
Explanation: In this example, it can be shown that it is impossible to find two indices that satisfy both conditions.
Hence, [-1,-1] is returned.
 

Constraints:

1 <= n == nums.length <= 10^5
0 <= nums[i] <= 10^9
0 <= indexDifference <= 10^5
0 <= valueDifference <= 10^9
*/
//Time: O(n)  Space: O(1)
class Solution {
    // 在nums[]找到任意一个 对数, 满足要求 
    // 1. Math.abs(i - j) >= indexDifference ==> i ≤ j − indexDifference。
    // 2. Math.abs(nums[i] - nums[j]) >= valueDifference
    // 因为 1 <= n == nums.length <= 10^5, 所以要用O(n)的时间复杂度
    // 有两个变量条件，所以首先固定住一个变量条件，再去验证第二个条件
    
    // 遍历枚举 j 的 ==> i = j − indexDifference
    // 维护和 j 间隔 indexDifference以外 的 i, 可能存在的 最大值 和 最小值 对应的 index
    public int[] findIndices(int[] nums, int indexDifference, int valueDifference) {
        int maxIndex = 0;
        int minIndex = 0;
        int n = nums.length;

        for(int j = indexDifference; j < n; j++){
            //以[i, j]为对数，开始遍历
            int i = j - indexDifference;

            //维护和 j 间隔 indexDifference以外 的 i, 可能存在的最大值 和 最小值 对应的 index
            if(nums[i] > nums[maxIndex]){
                maxIndex = i;
            } else if(nums[i] < nums[minIndex]){
                minIndex = i;
            }

            //然后检查 是否 满足 第二个 valueDifference的条件
            if(nums[maxIndex] - nums[j] >= valueDifference){
                return new int[]{maxIndex, j};
            }

            if(nums[j] - nums[minIndex] >= valueDifference){
                return new int[]{minIndex, j};
            }
        }

        //如果没有符合要求的对数，输出返回[-1, -1]
        return new int[]{-1, -1};
    }
}
