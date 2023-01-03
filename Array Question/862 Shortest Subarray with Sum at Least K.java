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

//My Solution: Two Ponter ====> Wrong (Time Limit Exceeded)
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


//Correct Solution: Used Deque + Prefix sum
//Prefix sum[] save the sum of range in the nums[] array ==> can save much time
//Deque: save the index of prefixSum ==> Used to find the range length
class Solution {
    public int shortestSubarray(int[] nums, int k) {
        int minLength = Integer.MAX_VALUE;
        int n = nums.length;
        Deque<Integer> deque = new LinkedList<>();
        long[] prefixSum = new long[n + 1];

        deque.offerLast(0); //every time add in the last position of deque
        
        for(int i = 0; i < n; i++){
            prefixSum[i + 1] = prefixSum[i] + nums[i]; //save the prefixSum of nums[]

            //
            while(!deque.isEmpty() && prefixSum[deque.peekLast()] >= prefixSum[i + 1]){
                deque.pollLast();
            }

            deque.offerLast(i + 1);

            while (!deque.isEmpty() && prefixSum[i + 1] - prefixSum[deque.peekFirst()] >= k) {
                minLength = Math.min(minLength, i - deque.peekFirst() + 1);
                deque.pollFirst();
            }
        }

        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }
}
