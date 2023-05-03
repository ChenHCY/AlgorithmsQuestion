/* Leetcode 442. Find All Duplicates in an Array

Given an integer array nums of length n where all the integers of nums are in the range [1, n] 
and each integer appears once or twice, return an array of all the integers that appears twice.

You must write an algorithm that runs in O(n) time and uses only constant extra space.
 
Example 1:

Input: nums = [4,3,2,7,8,2,3,1]
Output: [2,3]

Example 2:

Input: nums = [1,1,2]
Output: [1]

Example 3:

Input: nums = [1]
Output: []
 

Constraints:

n == nums.length
1 <= n <= 105
1 <= nums[i] <= n
Each element in nums appears once or twice.
*/

/* 思路：此题是给予一个整数数组nums，其中nums所有整数都在范围内[1, n]
找到其中重复的的数字
*/

//Solution 1: Used HashSet or buckSort or HashMap count the frequence of every number
//Time: O(n) Space: O(n)
class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        List<Integer> res = new ArrayList<>();

        for(int i = 0; i < nums.length; i++){
            if(!set.contains(nums[i])){
                set.add(nums[i]);
            }else{
                res.add(nums[i]);
            }
        }

        return res;
    }
}

//Solution 2: 通过标记负数来检查这个值是否重复，优化空间复杂度
//Time: O(n)   Space: O(1)
// 思路： n其中所有整数都nums在范围内[1, n]
// 所以当我们遇到一个数nums[i]时，我们标记这个nums[i]对应的nums[nums[i] - 1] 为负数
// 当我们再遇到和nums[i]相同值的时候，会再次找到这个值对应的nums[nums[i] - 1]，
//==》所以我们直接判断nums[nums[i] - 1] 是否大于 0 ==> 小于0，表示这个值之前重复过，加入到res list中
class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();

        // n其中所有整数都nums在范围内[1, n]
        for(int i = 0; i < nums.length; i++){
            int indexTemp = Math.abs(nums[i]);

            // 所以当我们遇到一个数nums[i]时，我们标记这个nums[i]对应的nums[nums[i] - 1] 为负数
            if(nums[indexTemp - 1] > 0){
                nums[indexTemp - 1] = nums[indexTemp - 1] * -1;
            } else { // 当我们再遇到和nums[i]相同值的时候，会再次找到这个值对应的nums[nums[i] - 1]，
               res.add(indexTemp); // 所以我们直接判断nums[nums[i] - 1] 是否大于 0 ==> 小于0，表示这个值之前重复过，加入到res list中
            }
        }

        return res;
    }
}
