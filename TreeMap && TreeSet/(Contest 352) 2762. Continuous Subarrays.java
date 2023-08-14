/*Leetcode (Contest 352) 2762. Continuous Subarrays

You are given a 0-indexed integer array nums. A subarray of nums is called continuous if:

Let i, i + 1, ..., j be the indices in the subarray. Then, for each pair of indices i <= i1, i2 <= j, 0 <= |nums[i1] - nums[i2]| <= 2.
Return the total number of continuous subarrays.

A subarray is a contiguous non-empty sequence of elements within an array.

Example 1:

Input: nums = [5,4,2,4]
Output: 8
Explanation: 
Continuous subarray of size 1: [5], [4], [2], [4].
Continuous subarray of size 2: [5,4], [4,2], [2,4].
Continuous subarray of size 3: [4,2,4].
Thereare no subarrys of size 4.
Total continuous subarrays = 4 + 3 + 1 = 8.
It can be shown that there are no more continuous subarrays.
 
Example 2:

Input: nums = [1,2,3]
Output: 6
Explanation: 
Continuous subarray of size 1: [1], [2], [3].
Continuous subarray of size 2: [1,2], [2,3].
Continuous subarray of size 3: [1,2,3].
Total continuous subarrays = 3 + 2 + 1 = 6.
 

Constraints:

1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
*/

/*思路：
    因为 i <= i1, i2 <= j, 0 <= |nums[i1] - nums[i2]| <= 2.
    所以里面可能组成的连续子数组 Continuous Subarrays，的最大长度为3， 并且里面所有数对的差不超过2

    1. 左右指针滑动窗口，用treemap可以维护区间最值（也可以用线段树维护）
       =》 treemap: TreeMap类是接口的实现Map，它根据其键或自定义比较器的自然顺序按排序里面的element
       ==》 因为TreeMap是按照key值的大小自然排序的，所以这里lastKey()就是当前区域内的最大值，firstKey()是最小值
        然后进行比较，因为题目要求所有数对的差不能超过2 =》 如果超过2，就要缩小这个滑动窗口

    2. 移动指针right，找到每一个右端点对应的满足条件的最左边的左端点，从而可以算出i作为右端点时，对答案的贡献。
*/

class Solution {
    public long continuousSubarrays(int[] nums) {    
        long res = 0;
        int n = nums.length;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int left = 0;
        //开始遍历
        for(int right = 0; right < n; right++){

            //key = right对应的nums值， value = 出现的次数
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);

            //因为TreeMap是按照key值的大小自然排序的，所以这里lastKey()就是当前区域内的最大值，firstKey()是最小值
            //然后进行比较，因为题目要求所有数对的差不能超过2 =》 如果超过2，就要缩小这个滑动窗口
            while(left <= right && map.lastKey() - map.firstKey() > 2){
                map.put(nums[left], map.getOrDefault(nums[left], 0) - 1);
                //如果删完之前，出现次数为0，则代表这个数值不再存在于滑动窗口中
                if(map.get(nums[left]) == 0){
                    map.remove(nums[left]);
                }
                left++; //同时缩小滑动窗口，移动左区间
            }
            res += (long) right - left + 1; //计算每次滑动窗口能满足要求的子数组数量
        }

        return res;
    }
}
