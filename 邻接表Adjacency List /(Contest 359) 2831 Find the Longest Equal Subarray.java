/*Leetcode (Contest 359) 2831 Find the Longest Equal Subarray

You are given a 0-indexed integer array nums and an integer k.

A subarray is called equal if all of its elements are equal. Note that the empty subarray is an equal subarray.

Return the length of the longest possible equal subarray after deleting at most k elements from nums.

A subarray is a contiguous, possibly empty sequence of elements within an array.

Example 1:

Input: nums = [1,3,2,3,1,3], k = 3
Output: 3
Explanation: It's optimal to delete the elements at index 2 and index 4.
After deleting them, nums becomes equal to [1, 3, 3, 3].
The longest equal subarray starts at i = 1 and ends at j = 3 with length equal to 3.
It can be proven that no longer equal subarrays can be created.

Example 2:

Input: nums = [1,1,2,2,1,1], k = 2
Output: 4
Explanation: It's optimal to delete the elements at index 2 and index 3.
After deleting them, nums becomes equal to [1, 1, 1, 1].
The array itself is an equal subarray, so the answer is 4.
It can be proven that no longer equal subarrays can be created.
 

Constraints:

1 <= nums.length <= 10^5
1 <= nums[i] <= nums.length
0 <= k <= nums.length
*/

lass Solution {
    public int longestEqualSubarray(List<Integer> nums, int k) {
        int n = nums.size();
        int res = 0;

        //邻接表 ==> 里面储存每个数值在nums中所有的index位置需要删除的数字数量
        List<Integer>[] numPos = new ArrayList[n + 1];
        Arrays.setAll(numPos, e -> new ArrayList<>()); //首先在邻接表中创建空的集合

        for(int i = 0; i < n; i++){
            int currNum = nums.get(i); //当前index位置的数值
            numPos[currNum].add(i - numPos[currNum].size()); //里面放入每个index位置需要删除的数字数量
        } // ==》也是删除完之后，剩余数字的新的index位置
        /* For example:  nums = [1,3,2,3,1,3]
           1: [0, 3]    2: [2]     3:[1, 2, 3]
        */

        for(List<Integer> pos : numPos){
            //因为我们只需要找到最长的相等子数组 Equal Subarray
            //==》所以如果数字的数量是小于之前的结果，则可以直接跳过
            if(pos.size() <= res){
                continue;
            }

            //从邻接表中提取每个数值的index位置集合
            int left = 0;
            for(int right = 0; right < pos.size(); right++){
                //[left, right] 区域内需要删除的数字数量 大于了 K，则移动left缩小区间
                while(pos.get(right) - pos.get(left) > k){
                    left++;
                }
                //如果需要删除的数字数量小于K, 则开始计算对应的子数组长度
                //邻接表中存入的就是 =》 删除完之后，剩余数字的新的index位置
                res = Math.max(res, right - left + 1); 
            }
        }

        return res;
    }
}
