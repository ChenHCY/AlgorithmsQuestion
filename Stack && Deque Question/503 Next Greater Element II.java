/* Leetcode 503. Next Greater Element II

Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]), return the next greater number for every element in nums.

The next greater number of a number x is the first greater number to its traversing-order next in the array, 

which means you could search circularly to find its next greater number. If it doesn't exist, return -1 for this number.

Example 1:

Input: nums = [1,2,1]
Output: [2,-1,2]
Explanation: The first 1's next greater number is 2; 
The number 2 can't find next greater number. 
The second 1's next greater number needs to search circularly, which is also 2.

Example 2:

Input: nums = [1,2,3,4,3]
Output: [2,3,4,-1,4]
 

Constraints:

1 <= nums.length <= 10^4
-10^9 <= nums[i] <= 10^9
*/
//Time: O(n)  Space: O(n)
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        //通过 % 的方式 来达到循环搜索的效果
        int n = nums.length;
        int[] res = new int[n];
        
        //使用单调栈 进行储存和比较, 里面存储的是number 对应的下标
        Deque<Integer> stack = new ArrayDeque<>();

        //存在找不到更大值的情况，所以res[] 里面每个格子都需要放入-1
        Arrays.fill(res, -1);

        //main travser loop, 从前往后进行遍历
        //通过 % 的方式 来达到循环搜索的效果
        for(int i = 0; i < n * 2; i++){
           //当单调栈里面存在数字的index，并且对应的数字小于目前的数字
           //则使用这个index在res[]中完成更新
         //使用while, 存在间隔很多天才获得更大值的情况。所以进行更新时，要更新当前stack里面的所有index值
            while(!stack.isEmpty() && nums[stack.peekLast()] < nums[i % n]){
                //System.out.println(stack.peek());
                res[stack.pollLast()] = nums[i % n]; 
            }
            stack.offer(i % n); //每次把当前数字的index加入到单调栈中
        }

        return res;
    }
}
