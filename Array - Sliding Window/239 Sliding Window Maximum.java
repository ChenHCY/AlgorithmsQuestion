/* Leetcode 239. Sliding Window Maximum

You are given an array of integers nums, there is a sliding window of size k which is moving from the very left 
of the array to the very right. 

You can only see the k numbers in the window. Each time the sliding window moves right by one position.

Return the max sliding window.

Example 1:

Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [3,3,5,5,6,7]
Explanation: 
Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7

Example 2:

Input: nums = [1], k = 1
Output: [1]
 

Constraints:

1 <= nums.length <= 10^5
-10^4 <= nums[i] <= 10^4
1 <= k <= nums.length
*/
//Time: O(n)  Space: O(n)
class Solution {
    //此题是输出一个array里面 从左往右。每一个长度为K的滑动窗口最大值，每次滑动一位
    // ArrayDeque + 单调栈 
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
         //base case
        if(n == 1){
            return nums;
        }

        int resLen = 1 + (n - k);
        int[] res = new int[resLen];
       
        //储存每个滑动窗口的可能最大数字的index
        Deque<Integer> stack = new ArrayDeque<>();
        int resIndex = 0;
        for(int i = 0; i < n; i++){
            //因为我们要找滑动窗口内的最大值，所以要保持队列顶端的数是最大值，
            //所以这个队列必须是单调递减的
            //这个是双向队列，如果队尾的数小于当前数nums[i], 这删除进行替换
            while(!stack.isEmpty() && nums[stack.peekLast()] <= nums[i]){
                stack.pollLast();
            }
            stack.addLast(i); //然后把当前值的index放入队列的队尾中
            
            //然后因为滑动窗口是k的长度，当遍历的i超过k时，每次统计最大值（队列顶端）
            if(i >= k - 1){
                //然后需要判断队列顶端的这个数字是否在当前的滑动窗口中 
                //如果队列顶端数字的index小于 遍历到位置 - 长度k，说明队列顶端数字不在当前的窗口内 
                while(!stack.isEmpty() && stack.peekFirst() < i - k + 1){
                    stack.pollFirst();
                }
                res[resIndex] = nums[stack.peek()]; //把队列顶端的index对应的最大值存入最后结果
                resIndex++;
            }
        }
        return res;
    }
}
