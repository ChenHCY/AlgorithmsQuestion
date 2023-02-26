/* (Contest 334) Leetcode 2576 Find the Maximum Number of Marked Indices

You are given a 0-indexed integer array nums.

Initially, all of the indices are unmarked. You are allowed to make this operation any number of times:

Pick two different unmarked indices i and j such that 2 * nums[i] <= nums[j], then mark i and j.
Return the maximum possible number of marked indices in nums using the above operation any number of times.

Example 1:

Input: nums = [3,5,2,4]
Output: 2
Explanation: In the first operation: pick i = 2 and j = 1, the operation is allowed because 2 * nums[2] <= nums[1]. Then mark index 2 and 1.
It can be shown that there's no other valid operation so the answer is 2.

Example 2:

Input: nums = [9,2,5,4]
Output: 4
Explanation: In the first operation: pick i = 3 and j = 0, the operation is allowed because 2 * nums[3] <= nums[0]. Then mark index 3 and 0.
In the second operation: pick i = 1 and j = 2, the operation is allowed because 2 * nums[1] <= nums[2]. Then mark index 1 and 2.
Since there is no other operation, the answer is 4.

Example 3:

Input: nums = [7,6,8]
Output: 0
Explanation: There is no valid operation to do, so the answer is 0.

 

Constraints:

1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
*/

/*  

题意是检查一个nums[]中存在多少数字能形成 2 * nums[i] <= nums[j] 的关系

思路：n代表 nums[].length

这个问题通过使用两个for-loop检查和boolean[]标记的方法，无法查找到最大能配对的数量

所以方法是双指针 + arrays.sort()方法

==》因为两个数字成一对，所以我们最多能配成 n / 2 对

==》所以在排序之后，我们可以考虑把nums[]分成两半来考虑 ==》2 * nums[i] <= nums[j]

==》使用while-loop进行遍历， ==》 因为我们是排序+确认了left指针和right指针的界限，所以不会存在重复计算的可能
    1. left指针从0开始，到n/2结束 ==》 0 <= left < n / 2
    2. right指针从n/2的位置开始, 到n最后结束 ==》 n / 2 <= right < n

==》如果存在 nums[left] * 2 <= nums[right]，两个指针都向前移动，res += 2 可以标记两个数

    如果不存在，说明nums[right]不够大，right指针向前移动
 
==》直到两个指针有一个越界，循环结束。得到的res的值 就是题目要求找到最多数量的数字可以形成配对关系。
*/
//Time：O(N*Log(N))  Space: O(1)
class Solution {
    public int maxNumOfMarkedIndices(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums); //Time: O(n)
        int res = 0;

        //two pointer: Time: O(logn)
        int left = 0;
        int right = n / 2;
        
        while(left < n / 2 && right < n){
            if(nums[left] * 2 <= nums[right]){
                left++;
                right++;
                res += 2;
            } else{
                right++;
            }
        }

        return res;
    }
}
