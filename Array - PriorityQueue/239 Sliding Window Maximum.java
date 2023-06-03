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

//PriorityQueue 优先队列可以自动按照单调递减进行排序
//Time: O(nlogn)  Space: O(n)
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
       
        //储存每个滑动窗口的可能最大数字的index, 和对应的值
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return b[1] - a[1];
        });

        int resIndex = 0;
        //然遍历这个nums[]数组，找到每一个滑动的最大值
        for(int i = 0; i < n; i++){
          //PriorityQueue 优先队列可以自动按照单调递减进行排序, 所以我们只用把number 加入到优先队列中，不用像双向队列那样进行查找和删除
            pq.add(new int[]{i, nums[i]}); //然后把当前数字的值, 和在nums[]中的index放入到pq优先队列中
            
            //然后因为滑动窗口是k的长度，当遍历的i超过k时，每次统计最大值（队列顶端）
            if(i >= k - 1){
                //然后需要判断优先队列顶端的这个数字是否在当前的滑动窗口中 
                //如果pq优先队列顶端数字的index小于 遍历到位置 - 长度k，说明队列顶端数字不在当前的窗口内 
                while(pq.peek()[0] < i - k + 1){
                    pq.poll();
                }
                res[resIndex] = pq.peek()[1]; //把优先队列顶端的nums[]值存入最后结果，这个顶端值就是当前窗口的最大值
                resIndex++;
            }
        }
        return res;
    }
}
