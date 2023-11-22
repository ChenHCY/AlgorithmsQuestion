/* Leetcode 1846. Maximum Element After Decreasing and Rearranging

You are given an array of positive integers arr. Perform some operations (possibly none) on arr so that it satisfies these conditions:
    · The value of the first element in arr must be 1.
    · The absolute difference between any 2 adjacent elements must be less than or equal to 1. In other words, abs(arr[i] - arr[i - 1]) <= 1 for each i where 1 <= i < arr.length (0-indexed). 
abs(x) is the absolute value of x.

There are 2 types of operations that you can perform any number of times:
    · Decrease the value of any element of arr to a smaller positive integer.
    · Rearrange the elements of arr to be in any order.

Return the maximum possible value of an element in arr after performing the operations to satisfy the conditions.

Example 1:

Input: arr = [2,2,1,2,1]
Output: 2
Explanation: 
We can satisfy the conditions by rearranging arr so it becomes [1,2,2,2,1].
The largest element in arr is 2.

Example 2:

Input: arr = [100,1,1000]
Output: 3
Explanation: 
One possible way to satisfy the conditions is by doing the following:
1. Rearrange arr so it becomes [1,100,1000].
2. Decrease the value of the second element to 2.
3. Decrease the value of the third element to 3.
Now arr = [1,2,3], which satisfies the conditions.
The largest element in arr is 3.

Example 3:

Input: arr = [1,2,3,4,5]
Output: 5
Explanation: The array already satisfies the conditions, and the largest element is 5.
 

Constraints:

1 <= arr.length <= 10^5
1 <= arr[i] <= 10^9
*/

    // 最后形成的数组 需要 满足的条件：
    // 1. arr 中 第一个 元素必须为 1
    // 2. 任意相邻两个元素的差的绝对值 小于等于 1 (arr[i]都是正数)
    // 我们可以执行的操作：
    // 1. 减小 arr 中任意元素的值，使其变为任意一个更小的正整数 。
    // 2. 重新排列 arr 中的元素，你可以以任意顺序重新排列。==> 因为开头是必须是1，所以按照非递减顺序排列

class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr); // 按照非递减顺序排列
        arr[0] = 1;  // 条件1：arr 中 第一个 元素必须为 1

        for(int i = 1; i < arr.length; i++){
            arr[i] = Math.min(arr[i], arr[i - 1] + 1); // 任意相邻两个元素的差的绝对值 小于等于 1
        }

        return arr[arr.length - 1];
    }
}
