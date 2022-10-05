/* Leetcode 45. Jump Game II

You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].

Each element nums[i] represents the maximum length of a forward jump from index i. 
In other words, if you are at nums[i], you can jump to any nums[i + j] where:

1 <= j <= nums[i] and i + j < n
Return the minimum number of jumps to reach nums[n - 1]. The test cases are generated such that you can reach nums[n - 1].

Example 1:
Input: nums = [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.

Example 2:
Input: nums = [2,3,0,1,4]
Output: 2
 
Constraints:
1 <= nums.length <= 104
0 <= nums[i] <= 1000
*/

//Solution 1: Greedy Algorithm ==> save every current position jump maximum value, then check and add into result
class Solution {
    public int jump(int[] nums) {
        if(nums == null || nums.length < 2){
            return 0;
        }
        
        int res = 0; //the actually step arrived last index
        int max = 0; //save the jump position
        int curMax = 0; //i + nums[i] ==> the 
        // The theoretical maximum position that can be jumped to
        
        for(int i = 0; i < nums.length - 1; i++){
            max = Math.max(max, i + nums[i]); //i + nums[i] the maximum jump position of current position
            if(curMax == i){ //current position is previous maximum jump position, so must jump it
                res += 1;
                curMax = max; //this is the maximum position can arrived 
            }
        }
        
        return res; // output the step value
    }
}

//Soulution 2: BFS Method

