/*Leetcode 18. 4Sum

Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:

    · 0 <= a, b, c, d < n
    · a, b, c, and d are distinct.
    · nums[a] + nums[b] + nums[c] + nums[d] == target

You may return the answer in any order.

Example 1:

Input: nums = [1,0,-1,0,-2,2], target = 0
Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]

Example 2:

Input: nums = [2,2,2,2,2], target = 8
Output: [[2,2,2,2]]
 

Constraints:
1 <= nums.length <= 200
-10^9 <= nums[i] <= 10^9
-10^9 <= target <= 10^9
*/
// 和求三数之和 一样的操作，先确定第一个数，然后按照三数之和的思路 在范围内找到满足要求的数组
// ==》重要点：需要确定数组选择的范围，并且target会不会超出integer的界限
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
       List<List<Integer>> res = new ArrayList<>();
       Arrays.sort(nums);

       for(int i = 0; i < nums.length - 3; i++){
            // 如果四个数值的中的第一个数值 与 前面的数一样，则跳过
            if(i > 0 && nums[i] == nums[i-1]){
                continue;
            }

            // 确定第一个数值之后，调用findTwoSum的方法 找到其余两值 (满足和为target=0的)
            List<List<Integer>> sub = findThreeSum(nums, i + 1, (long) target - nums[i]);
            for(List<Integer> arr : sub){
                // 然后组成的list 加入到结果ans中
                res.add(Arrays.asList(nums[i], arr.get(0), arr.get(1), arr.get(2)));
            }
        }

        return res;
    }

    // 找到3数之和的function
    public static List<List<Integer>> findThreeSum(int[] nums, int start, long target){
        List<List<Integer>> ans = new ArrayList<>();

        for(int i = start; i < nums.length - 2; i++){
            // 如果三个数值的中的第一个数值 与 前面的数一样，则跳过
            if(i > start && nums[i] == nums[i-1]){
                continue;
            }

            // 确定第一个数值之后，调用findTwoSum的方法 找到其余两值
            List<List<Integer>> sub = findTwoSum(nums, i + 1, target - nums[i]);
            for(List<Integer> arr : sub){
                // 然后组成的list 加入到结果ans中
                ans.add(Arrays.asList(nums[i], arr.get(0), arr.get(1)));
            }
        }

        return ans;
    }

    public static List<List<Integer>> findTwoSum(int[] nums, int start, long target){
        List<List<Integer>> res = new ArrayList<>();
        int left = start;
        int right = nums.length - 1;
        
        while(left < right){
            long sum = nums[left] + nums[right];
            // 如果找到符合要求的对数，加入到结果中
            if(sum == target){
                res.add(Arrays.asList(nums[left], nums[right]));
                
                // 处理重复元素
                while (left < right && nums[left] == nums[left + 1]) {
                    left++;
                }
                while (left < right && nums[right] == nums[right - 1]) {
                    right--;
                }
              
                //因为要找到总和等于0的所有情况，所以当找到第一组之后，需要继续遍历，直到超出循环
                left++;
                right--;
            } else if(sum > target){
                right--;
            } else {
                left++;
            }
        }
        return res;
    }
}
