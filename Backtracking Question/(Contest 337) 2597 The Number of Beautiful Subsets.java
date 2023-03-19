/*Leetcode (Contest 337) 2597 The Number of Beautiful Subsets

You are given an array nums of positive integers and a positive integer k.
A subset of nums is beautiful if it does not contain two integers with an absolute difference equal to k.
Return the number of non-empty beautiful subsets of the array nums.

A subset of nums is an array that can be obtained by deleting some (possibly none) elements from nums. 
Two subsets are different if and only if the chosen indices to delete are different.

Example 1:

Input: nums = [2,4,6], k = 2
Output: 4
Explanation: The beautiful subsets of the array nums are: [2], [4], [6], [2, 6].
It can be proved that there are only 4 beautiful subsets in the array [2,4,6].


Example 2:

Input: nums = [1], k = 1
Output: 1
Explanation: The beautiful subset of the array nums is [1].
It can be proved that there is only 1 beautiful subset in the array [1].
 

Constraints:

1 <= nums.length <= 20
1 <= nums[i], k <= 1000
*/
/* 思路： 此题是统计nums[]array中有多少个完美子数组

也就是如果的子集不包含绝对差等于k的两个整数，则它这个子数组就是美丽的。

所以我们使用Backtracking的方法，==》 生成数组的所有可能子集，并检查每个子集是否漂亮。

1. 我们首先按升序对数组进行排序（Arrays.sort()), 然后通过Backtracking来生成所有可能的子集

2. 通过使用HashSet来检查是否存在两个数的差等于K的情况

3. 如果新子集符合要求，则count变量会增加。
==》最后将当前元素从元素集中移除，回溯生成下一个子集。

4. 因为需要返回非空的完美子集，所以要在最后的结果上减一
*/
//Time: O(nlogn + 2^n)    Space: O(n)
class Solution {
    int count = 0;
    public int beautifulSubsets(int[] nums, int k) {
        Arrays.sort(nums); //sort the nums[] array first
        HashSet<Integer> set = new HashSet<>();
        backtracking(nums, k, 0, set);

        //Return the number of non-empty beautiful subsets of the array nums.
        return count - 1; //空集不算完美子集
    }

    public void backtracking(int[] nums, int k, int index, HashSet<Integer> set){
        count++;
        for(int i = index; i < nums.length; i++){
            if(!set.contains(nums[i] - k)){
                set.add(nums[i]);
                System.out.println("Add: " + nums[i]);
                backtracking(nums, k, i + 1, set);
                set.remove(nums[i]);
                System.out.println("remove: " + nums[i]); //remove current elem before backtracking
            }
        }
    }
}
