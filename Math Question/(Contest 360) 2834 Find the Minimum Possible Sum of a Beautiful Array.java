/*Leetcode (Contest 360) 2834 Find the Minimum Possible Sum of a Beautiful Array

You are given positive integers n and target.

An array nums is beautiful if it meets the following conditions:

  · nums.length == n.
  · nums consists of pairwise distinct positive integers.
  · There doesn't exist two distinct indices, i and j, in the range [0, n - 1], such that nums[i] + nums[j] == target.

Return the minimum possible sum that a beautiful array could have.

Example 1:

Input: n = 2, target = 3
Output: 4
Explanation: We can see that nums = [1,3] is beautiful.
- The array nums has length n = 2.
- The array nums consists of pairwise distinct positive integers.
- There doesn't exist two distinct indices, i and j, with nums[i] + nums[j] == 3.
It can be proven that 4 is the minimum possible sum that a beautiful array could have.

Example 2:

Input: n = 3, target = 3
Output: 8
Explanation: We can see that nums = [1,3,4] is beautiful.
- The array nums has length n = 3.
- The array nums consists of pairwise distinct positive integers.
- There doesn't exist two distinct indices, i and j, with nums[i] + nums[j] == 3.
It can be proven that 8 is the minimum possible sum that a beautiful array could have.

Example 3:

Input: n = 1, target = 1
Output: 1
Explanation: We can see, that nums = [1] is beautiful.
 

Constraints:

1 <= n <= 10^5
1 <= target <= 10^5
*/

class Solution {
    public long minimumPossibleSum(int n, int target) {
        //basic question
        if(n == 1){
            return 1;
        }
        
        
        int size = 0; // [0, n-1] ==> 用来记录产生的array size
        long res = 0; // 满足要求的nums array总和

         //用来记录 满足 nums[i] + nums[j] == target 要求 ==》不能储存的数字 
        HashSet<Integer> set = new HashSet<>();
        
        int num = 1;
        
        //main travser
        while(size < n){
            //如果满足 nums[i] + nums[j] != target 要求
            //则不进行储存
            if(!set.contains(num)){
                res += num;
                set.add(target - num);
                size += 1;
            }
            num += 1;
        }
        
        return res;
    }
}
