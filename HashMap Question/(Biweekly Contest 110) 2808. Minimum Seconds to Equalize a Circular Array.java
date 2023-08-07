/*Leetcode (Biweekly Contest 110) 2808. Minimum Seconds to Equalize a Circular Array

You are given a 0-indexed array nums containing n integers.

  · At each second, you perform the following operation on the array:

For every index i in the range [0, n - 1], replace nums[i] with either nums[i], nums[(i - 1 + n) % n], or nums[(i + 1) % n].
Note that all the elements get replaced simultaneously.

Return the minimum number of seconds needed to make all elements in the array nums equal.

Example 1:
Input: nums = [1,2,1,2]
Output: 1
Explanation: We can equalize the array in 1 second in the following way:
- At 1st second, replace values at each index with [nums[3],nums[1],nums[3],nums[3]]. After replacement, nums = [2,2,2,2].
It can be proven that 1 second is the minimum amount of seconds needed for equalizing the array.

Example 2:
Input: nums = [2,1,3,3,2]
Output: 2
Explanation: We can equalize the array in 2 seconds in the following way:
- At 1st second, replace values at each index with [nums[0],nums[2],nums[2],nums[2],nums[3]]. After replacement, nums = [2,3,3,3,3].
- At 2nd second, replace values at each index with [nums[1],nums[1],nums[2],nums[3],nums[4]]. After replacement, nums = [3,3,3,3,3].
It can be proven that 2 seconds is the minimum amount of seconds needed for equalizing the array.

Example 3:
Input: nums = [5,5,5,5]
Output: 0
Explanation: We don't need to perform any operations as all elements in the initial array are the same.
 

Constraints:

1 <= n == nums.length <= 10^5
1 <= nums[i] <= 10^9
*/

/*
    //题目是计算要多少秒，能把nums修改成全部一样的数字，因为根据规则我们只能修改
    
    // 假设: 修改后的数组 nums 的最终值都是x，那么 x 一定在原始输入数组中。
    // For example: i < j，and nums[i] == nums[j] == x 且 nums[k] != x 对于所有 k，存在 i < k < j。
    // 因为根据规则我们只能修改 每个 x 的相邻数字，所以每秒最多可以改变2个 x 之间的2个数字
    // 因此，我们只用统计两个x之间最多的数字个数(j - i - 1)，然后 (j - i - 1 + 1)/2，==》 因为要向上取整，所以需要+1
    //这就是将i和j之间的所有数字更改为x的时间。
*/

class Solution {
    public int minimumSeconds(List<Integer> nums) {
        int n = nums.size();
        int res = Integer.MAX_VALUE;
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

        ///统计nums中所有数字出现的index位置
        for(int i = 0; i < n; i++){
            if(!map.containsKey(nums.get(i))){  //如果之前没有出现过，则创建新的arraylist
                map.put(nums.get(i), new ArrayList<>());
            }  
            map.get(nums.get(i)).add(i); //之前已经出现过，则直接把当前index加入对应的arraylist
        }

        //提取每个数字的index位置list
        for(ArrayList<Integer> arr : map.values()){
            //因为循环，所以要再加入一个新值
            arr.add(arr.get(0) + n);
            int longest = 0;
            for(int i = 1; i < arr.size(); i++){
                //比较找到当前值，最长的一段区域
                longest = Math.max(longest, arr.get(i) - arr.get(i - 1));
            }
            
            res = Math.min(res, longest / 2); //返回使数组中所有元素相等所需的最小秒数 
        }

        return res;
    }
}
