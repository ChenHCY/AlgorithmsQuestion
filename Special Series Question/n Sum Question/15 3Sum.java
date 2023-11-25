/* Leetcode 15. 3Sum

Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.

Example 1:

Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
Explanation: 
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
The distinct triplets are [-1,0,1] and [-1,-1,2].
Notice that the order of the output and the order of the triplets does not matter.

Example 2:

Input: nums = [0,1,1]
Output: []
Explanation: The only possible triplet does not sum up to 0.

Example 3:

Input: nums = [0,0,0]
Output: [[0,0,0]]
Explanation: The only possible triplet sums up to 0.
 

Constraints:

3 <= nums.length <= 3000
-105 <= nums[i] <= 10^5
*/

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);

        for(int i = 0; i < nums.length - 2; i++){
            // 如果三个数值的中的第一个数值 与 前面的数一样，则跳过
            if(i > 0 && nums[i] == nums[i-1]){
                continue;
            }
            // 确定第一个数值之后，调用findTwoSum的方法 找到其余两值 (满足和为target=0的)
            List<List<Integer>> sub = findTwoSum(nums, i + 1, -nums[i]);
            for(List<Integer> arr : sub){
                // 然后组成的list 加入到结果ans中
                ans.add(Arrays.asList(nums[i], arr.get(0), arr.get(1)));
            }
        }

        return ans;
    }

    public static List<List<Integer>> findTwoSum(int[] nums, int start, int target){
        List<List<Integer>> res = new ArrayList<>();
        int left = start;
        int right = nums.length - 1;
        
        while(left < right){
            int sum = nums[left] + nums[right];
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
