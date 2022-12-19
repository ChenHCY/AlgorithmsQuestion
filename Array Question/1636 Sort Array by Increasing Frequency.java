/* Leetcode 1636 Sort Array by Increasing Frequency

Given an array of integers nums, sort the array in increasing order based on the frequency of the values. 
If multiple values have the same frequency, sort them in decreasing order.

Return the sorted array.

Example 1:
Input: nums = [1,1,2,2,2,3]
Output: [3,1,1,2,2,2]
Explanation: '3' has a frequency of 1, '1' has a frequency of 2, and '2' has a frequency of 3.

Example 2:
Input: nums = [2,3,1,3,2]
Output: [1,3,3,2,2]
Explanation: '2' and '3' both have a frequency of 2, so they are sorted in decreasing order.

Example 3:
Input: nums = [-1,1,-6,4,5,-6,1,4,1]
Output: [5,-1,4,4,-6,-6,1,1,1]
 
Constraints:
1 <= nums.length <= 100
-100 <= nums[i] <= 100

*/

//Though: ===> Used HashMap to save every frequency of each number elements from nums[] array
//==> Used PriorityQueue sort all the element nunbers with their frequency
//==> Finall get and add into the result array

//Time: O(nlogn)  Space: O(n)
class Solution {
    public int[] frequencySort(int[] nums) {
        int[] res = new int[nums.length];
        HashMap<Integer, Integer> map = new HashMap<>();
        //put every number into hashmap with their frequency
        for(int num : nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        //Used PriorityQueue sort all the element nunbers with their frequency
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> map.get(a) != map.get(b) ? map.get(a) - map.get(b) : b - a);
        //map.get(a) - map.get(b) ==> sort increasing order with their frequency
        //b - a ==> it means when multiple values have the same frequency, sort them in decreasing order.
      
        for(int number : nums){
            pq.offer(number);
        } //add all the elmenet number into PriorityQueue to get sort list

        //add the result number into res array
        int i = 0;
        while(!pq.isEmpty()){
            res[i] = pq.poll();
            i++;
        }

        return res;
    }
}
