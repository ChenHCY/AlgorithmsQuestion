/* Leetcode 1. Two Sum

Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.

Example 1:

Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].

Example 2:

Input: nums = [3,2,4], target = 6
Output: [1,2]

Example 3:

Input: nums = [3,3], target = 6
Output: [0,1]
 

Constraints:
2 <= nums.length <= 10^4
-109 <= nums[i] <= 10^9
-109 <= target <= 10^9
Only one valid answer exists
*/

//  求两数之和 的 模板 code
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

// 此题是要找到nums[]中 两个数总和为target的 下标
// 所以首先要copy nums[], 然后按照求两数之后的办法，两个指针从两端想中间遍历，找到符合要求的两个数
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] helper = Arrays.copyOf(nums, nums.length);
        Arrays.sort(helper); 
        int left = 0;
        int right = nums.length - 1; 

        int[] res = new int[2];
        res[0] = -1; // 用来确定添加到最后res中的先后顺序

        while(left < right){
            int sum = helper[left] + helper[right];

            if(sum == target){
                break;
            } else if(sum > target){
                right--;
            } else {
                left++;
            }
        }

        for(int i = 0; i < nums.length; i++){
            if(res[0] == -1 && nums[i] == helper[left]){
                res[0] = i;
            } else if(nums[i] == helper[right]){
                res[1] = i;
            }
        }

        return res;
    }
}
