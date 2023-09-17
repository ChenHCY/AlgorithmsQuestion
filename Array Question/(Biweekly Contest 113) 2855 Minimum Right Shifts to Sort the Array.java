/* (Biweekly Contest 113) Leetcode 2855. Minimum Right Shifts to Sort the Array

You are given a 0-indexed array nums of length n containing distinct positive integers. Return the minimum number of right shifts required to sort nums and -1 if this is not possible.

A right shift is defined as shifting the element at index i to index (i + 1) % n, for all indices.

Example 1:
Input: nums = [3,4,5,1,2]
Output: 2
Explanation: 
After the first right shift, nums = [2,3,4,5,1].
After the second right shift, nums = [1,2,3,4,5].
Now nums is sorted; therefore the answer is 2.

Example 2:
Input: nums = [1,3,5]
Output: 0
Explanation: nums is already sorted therefore, the answer is 0.

Example 3:
Input: nums = [2,1,4]
Output: -1
Explanation: It's impossible to sort the array using right shifts.
 

Constraints:
1 <= nums.length <= 100
1 <= nums[i] <= 100
nums contains distinct integers.
*/

class Solution {
    //寻找断点，然后剩余的长度就是要移动的次数
    public int minimumRightShifts(List<Integer> nums) {
        int index = 0; //断点的位置
        int n = nums.size();
        //寻找断点
        for(int i = 1; i < n; i++){
            //遍历发现了当前值小于前面的值，断点位置
            if(nums.get(i) < nums.get(i-1)){
                index = i;
            }
        }

        //然后从断点的位置开始检查，是否是一个有序递增的数组
        for(int i = 1; i < n; i++){
            int currNum = nums.get((index + i) % n);
            int prevNum = nums.get((index + i - 1) % n);
            //如果不是有序递增的，表示这个数组无法通过 right shift 得到有序递增
            if(prevNum > currNum){
                return -1;
            }
        }

        //如果从断点开始是有序递增的，答案就是断点之后的长度
        return (n - index) % n;
        //取模是因为这个数组原本就是有序递增的，那就不用发生任何改变
    }
}
