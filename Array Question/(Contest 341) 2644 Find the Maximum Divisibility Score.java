/* Leetcode (Contest 341) 2644 Find the Maximum Divisibility Score

You are given two 0-indexed integer arrays nums and divisors.

The divisibility score of divisors[i] is the number of indices j such that nums[j] is divisible by divisors[i].

Return the integer divisors[i] with the maximum divisibility score. If there is more than one integer with the maximum score, return the minimum of them.

Example 1:

Input: nums = [4,7,9,3,9], divisors = [5,2,3]
Output: 3
Explanation: The divisibility score for every element in divisors is:
The divisibility score of divisors[0] is 0 since no number in nums is divisible by 5.
The divisibility score of divisors[1] is 1 since nums[0] is divisible by 2.
The divisibility score of divisors[2] is 3 since nums[2], nums[3], and nums[4] are divisible by 3.
Since divisors[2] has the maximum divisibility score, we return it.

Example 2:

Input: nums = [20,14,21,10], divisors = [5,7,5]
Output: 5
Explanation: The divisibility score for every element in divisors is:
The divisibility score of divisors[0] is 2 since nums[0] and nums[3] are divisible by 5.
The divisibility score of divisors[1] is 2 since nums[1] and nums[2] are divisible by 7.
The divisibility score of divisors[2] is 2 since nums[0] and nums[3] are divisible by 5.
Since divisors[0], divisors[1], and divisors[2] all have the maximum divisibility score, we return the minimum of them (i.e., divisors[2]).

Example 3:

Input: nums = [12], divisors = [10,16]
Output: 10
Explanation: The divisibility score for every element in divisors is:
The divisibility score of divisors[0] is 0 since no number in nums is divisible by 10.
The divisibility score of divisors[1] is 0 since no number in nums is divisible by 16.
Since divisors[0] and divisors[1] both have the maximum divisibility score, we return the minimum of them (i.e., divisors[0]).

Constraints:

1 <= nums.length, divisors.length <= 1000
1 <= nums[i], divisors[i] <= 10^9
*/

// 思路： 遍历 divisors 和 nums， 统计divisors中每一个数 在nums[] array 中能被整除的数量
//  ==》 nums[j] % divisors[i] == 0
// 然后进行比较，如果divisors中有两个数在nums[]中能被整除的数量的一样的，则输出小的值
//Time: O(m*n) Space: O(1)
class Solution {
    public int maxDivScore(int[] nums, int[] divisors) {
        int maxCount = 0;
        int res = divisors[0];
        
        for(int i = 0; i < divisors.length; i++){
            int count = 0;
            for(int j = 0; j < nums.length; j++){
                if(nums[j] % divisors[i] == 0){
                    count++;
                }
            }
            
            if(count > maxCount){
                maxCount = count;
                res = divisors[i];
            } else if (count == maxCount) {
                res = Math.min(res, divisors[i]);
            }
        }
        
        return res;
    }
}
