/* Leetcode (Contest 332) 2562 Find the Array Concatenation Value

You are given a 0-indexed integer array nums.

The concatenation of two numbers is the number formed by concatenating their numerals.

For example, the concatenation of 15, 49 is 1549.
The concatenation value of nums is initially equal to 0. Perform this operation until nums becomes empty:

If there exists more than one number in nums, pick the first element and last element in nums respectively and add the value 
of their concatenation to the concatenation value of nums, then delete the first and last element from nums.
If one element exists, add its value to the concatenation value of nums, then delete it.
Return the concatenation value of the nums.

Example 1:

Input: nums = [7,52,2,4]
Output: 596
Explanation: Before performing any operation, nums is [7,52,2,4] and concatenation value is 0.
 - In the first operation:
We pick the first element, 7, and the last element, 4.
Their concatenation is 74, and we add it to the concatenation value, so it becomes equal to 74.
Then we delete them from nums, so nums becomes equal to [52,2].
 - In the second operation:
We pick the first element, 52, and the last element, 2.
Their concatenation is 522, and we add it to the concatenation value, so it becomes equal to 596.
Then we delete them from the nums, so nums becomes empty.
Since the concatenation value is 596 so the answer is 596.


Example 2:

Input: nums = [5,14,13,8,12]
Output: 673
Explanation: Before performing any operation, nums is [5,14,13,8,12] and concatenation value is 0.
 - In the first operation:
We pick the first element, 5, and the last element, 12.
Their concatenation is 512, and we add it to the concatenation value, so it becomes equal to 512.
Then we delete them from the nums, so nums becomes equal to [14,13,8].
 - In the second operation:
We pick the first element, 14, and the last element, 8.
Their concatenation is 148, and we add it to the concatenation value, so it becomes equal to 660.
Then we delete them from the nums, so nums becomes equal to [13].
 - In the third operation:
nums has only one element, so we pick 13 and add it to the concatenation value, so it becomes equal to 673.
Then we delete it from nums, so nums become empty.
Since the concatenation value is 673 so the answer is 673.
 

Constraints:

1 <= nums.length <= 1000
1 <= nums[i] <= 104
*/

/* 思路 双指针逻辑

左指针从第一个数字开始，右指针从最后一位数字开始

通过使用String和String.valueOf把这两个数字拼接起来

然后通过Long.parseLong把这个String 再转回成Long的值

进行一次，把两个指针移动一次

直到左指针越过右指针 ==》 while-loop 循环结束

****特殊情况： 如果nums[]仅剩下一个元素，则直接将值添加到的串联值
 if(left == right){//there is still have onlt one number
    res += nums[left];
    break;
}**********

最后 ==》 输出一个Long类型值的res
*/
//Time: O(n)   Space: O(1)
class Solution {
    public long findTheArrayConcVal(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        long res = 0;
        
        while(left <= right){
            if(left == right){//there is still have onlt one number
                res += nums[left];
                break;
            }
            String temp = "";
            
            temp = String.valueOf(nums[left]) + String.valueOf(nums[right]);
            res += Long.parseLong(temp);
            
            left++;
            right--;
        }
        
        return (long)res;
    }
}
