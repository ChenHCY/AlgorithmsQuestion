/* You are given an integer array nums and an integer target.
You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate all the integers.
For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
Return the number of different expressions that you can build, which evaluates to target.

Example 1:
Input: nums = [1,1,1,1,1], target = 3
Output: 5
Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
-1 + 1 + 1 + 1 + 1 = 3
+1 - 1 + 1 + 1 + 1 = 3
+1 + 1 - 1 + 1 + 1 = 3
+1 + 1 + 1 - 1 + 1 = 3
+1 + 1 + 1 + 1 - 1 = 3

Example 2:
Input: nums = [1], target = 1
Output: 1

Constraints:
1 <= nums.length <= 20
0 <= nums[i] <= 1000
0 <= sum(nums[i]) <= 1000
-1000 <= target <= 1000
*/

/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number}
 */
var findTargetSumWays = function(nums, target) {
    return backtracking(nums, target, 0, 0);
};

// backtracking(DFS): traverse each number in nums, then count how many ways could equal target
const backtracking = (nums, target, currRes, index) => {
    // return condition: if arrived at the last index of nums
    // then check if currRes 当前总和 = target
    if(index === nums.length){
        return currRes === target ? 1 : 0;
    }

    // if not, continue recursion
    let plus = backtracking(nums, target, currRes + nums[index], index + 1);
    let minus = backtracking(nums, target, currRes - nums[index], index + 1);

    return plus + minus;
}
