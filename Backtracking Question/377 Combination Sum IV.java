/* Leetcode 377. Combination Sum IV

Given an array of distinct integers nums and a target integer target, return the number of possible combinations that add up to target.

The test cases are generated so that the answer can fit in a 32-bit integer.

Example 1:

Input: nums = [1,2,3], target = 4
Output: 7
Explanation:
The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)
Note that different sequences are counted as different combinations.

Example 2:

Input: nums = [9], target = 3
Output: 0
 

Constraints:

1 <= nums.length <= 200
1 <= nums[i] <= 1000
All the elements of nums are unique.
1 <= target <= 1000
*/

import java.util.*;
class HelloWorld {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        int sum = 4;
        int res = 0;
        res = combinationSum4(nums, sum);
        System.out.println(res);
    }
    
    public static int combinationSum4(int[] nums, int target) {
        return helper(nums, target, new HashMap<Integer, Integer>());
    } 
    
    private static int helper(int[] nums, int target, Map<Integer, Integer> map) {
        if (target < 0) return 0;
        if (target == 0) return 1;
        if (map.containsKey(target)) return map.get(target);
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int cnt = helper(nums, target - nums[i], map);
            System.out.println(target - nums[i] + " " + target + " " + nums[i]);
            System.out.println(cnt);
            if (target >= nums[i]) map.put(target - nums[i], cnt);
            res += cnt;
            System.out.println(res);
        }
        return res;
    }    
}
