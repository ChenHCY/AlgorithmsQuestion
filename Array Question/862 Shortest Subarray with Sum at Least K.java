/* Leetcode 862. Shortest Subarray with Sum at Least K

Given an integer array nums and an integer k, return the length of the shortest non-empty 
subarray of nums with a sum of at least k. If there is no such subarray, return -1.

A subarray is a contiguous part of an array.


Example 1:

Input: nums = [1], k = 1
Output: 1

Example 2:

Input: nums = [1,2], k = 4
Output: -1

Example 3:

Input: nums = [2,-1,2], k = 3
Output: 3
 

Constraints:

1 <= nums.length <= 105
-105 <= nums[i] <= 105
1 <= k <= 109
*/

//Correct Solution: Used Deque + Prefix sum
//Prefix sum[] save the sum of range in the nums[] array start at 0 ==> can save much time
//Deque: save the index of prefixSum ==> Used to find the range length
/*
由于题目要求求连续的子数组, 考虑使用滑动窗口, 但是因为数组中有负数, 不满足单调性, 所以不能使用滑动窗口

假定已经找到 i1 < i2 < j, pre[i1] >= pre[i2], 如果有 pre[j] - pre[i1] >= k, 那么一定有 pre[j] - pre[i2] >= k, 
由于 i1 < i2, 所以 从长度来说：==> j - i1 > j - i2,  这时可以保证 i1 必然不是需要的值.(从i2开始的子数组的长度一定更短）

所以需要一个单调队列来存放, 遍历到比队列尾对应的前缀和小的时候, 弹出队尾 ===> 中心思想：保证deque一定是单调递增的

比如 [1, 3, -2, 4] -> [0, 1, 4, 2, 6], ==> i1 = 1 i2 = 2  Pre[i1] = 4 Pre[i2] = 2 ==> k = 2
6 - 2 > 6 - 4 >= K = 2, 但i2作为开头，所用的长度更短，所以 i1 应该被弹出 ==> i1 = 3 

然后按照滑动窗口的思想, 如果 prefixSum[i + 1] - prefixSum[deque.peekFirst()] >= k, 这时可以弹出队头并且更新结果minLength的值

最后需要判断是否存在结果满足 k ==>  return minLength == Integer.MAX_VALUE ? -1 : minLength;
*/

//Time: O(n)  Space: O(n)
//思路链接 https://www.youtube.com/watch?v=HeFW6EPBGBg 
//这题主要是招nums[] array 里面区间的值，prefixSum是储存nums[] array 每个元素的前缀和（从第一个元素开始）
class Solution {
    public int shortestSubarray(int[] nums, int k) {
        int minLength = Integer.MAX_VALUE;
        int n = nums.length;
        Deque<Integer> deque = new ArrayDeque<>(); //里面储存的是遍历数组的index坐标
        long[] prefixSum = new long[n + 1];

        //first count the prefixsum of nums[] array
        for(int i = 0; i < n; i++){
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }

        //main travser
        //renew the dequeu element and deleate the element not need it ==> 保证deque的单调递增，
        //已知 A < B < endRangePointer, 如果可以找到 A前缀和 >= B前缀和，那么 endRangePointer - B前缀和 >= endRange - A前缀和 更有可能 >= K  
        //所以说明 B作为起点的subarray，sum更大，长度也更短 ==> 所以删除之前保留在deque里面的A点值
        for(int i = 0; i < prefixSum.length; i++){
            long currSum = prefixSum[i]; //get the frist sum element

            // ==> 用来保证符合要求的区域内尽量不要有负数
            while(!deque.isEmpty() && prefixSum[deque.peekLast()] >= currSum){
                deque.pollLast(); //所以需要一个单调队列来存放, 遍历到比队列尾对应的前缀和小的时候, 弹出队尾的index
            } //因为Deque里面储存的计算前缀和的坐标，我们希望找到一个坐标更新: 区域值更大，但index值越小。

            //renew the min length of Shortest Subarray  with Sum at Least K
            while(!deque.isEmpty() && currSum - prefixSum[deque.peekFirst()] >= k){
                minLength = Math.min( minLength, i - deque.peekFirst());
                System.out.println(prefixSum[deque.peekFirst()]);

                //因为Deque里面存的坐标可以看成子数组的起点，当找到第一个符合要求的子数组时，
                // ==> 一定是以这个起点开始的最短子数组，所以更新完最小长度后 可以poll掉
                deque.pollFirst(); //used for check all the element in deque list
            }

            deque.offerLast(i);
        }

        //最后需要判断是否存在结果满足 k
        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }
}


****************************  //First Try: Two Ponter ====> Wrong (Time Limit Exceeded)*******************************************
class Solution {
    public int shortestSubarray(int[] nums, int k) {
        int start = -1;
        int end = -1;
        int minLength = Integer.MAX_VALUE;

        for(int i = 0; i < nums.length; i++){
            int currSum = 0;
            for(int j = i; j < nums.length && (j - i + 1) < minLength; j++){
                currSum += nums[j];
                if(currSum >= k){
                    start = i;
                    end = j;
                    minLength = end - start + 1;
                }
            }
        }

        if(start == -1 || end == -1){
            return -1;
        }

        return minLength;
    }
}
