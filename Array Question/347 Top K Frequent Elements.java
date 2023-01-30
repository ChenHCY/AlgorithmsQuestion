/* Leetcode 347. Top K Frequent Elements

Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]

Example 2:

Input: nums = [1], k = 1
Output: [1]
 

Constraints:

1 <= nums.length <= 105
-104 <= nums[i] <= 104
k is in the range [1, the number of unique elements in the array].
It is guaranteed that the answer is unique.
 

Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
*/

/*
思路： HashMap存储number和出现的次数

然后用 PriorityQueue 进行排序，找到频率最大的 k 个数

*/
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        //used hashmap to save all the number element from nusm with their frequent
        HashMap<Integer, Integer> map = new HashMap<>();
        
        //used PriorityQueue to sort the frequent times of number elemnt in Ascending order
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>((n1, n2) -> map.get(n1) - map.get(n2));
        
        int[] res = new int[k]; //return the res
        
        //1. save add the number from nums[] array into HashMap with their frequent
        for(int num : nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        //2.sort the number elements through used PriorityQueue
        for(int key : map.keySet()){
            heap.offer(key);
            if(heap.size() > k){ //if more than k elements
                heap.poll(); //remove the smallest one(oldest)
            } 
        }
        
	    //3. put the k most frequent elements into res[] array 
        for(int j = 0; j < k; j++){
            res[j] = heap.poll();
        }
        
        return res;
    }
}
