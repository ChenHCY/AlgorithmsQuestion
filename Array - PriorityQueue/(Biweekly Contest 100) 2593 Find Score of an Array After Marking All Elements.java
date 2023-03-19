/* Leetcode (Biweekly Contest 100) 2593 Find Score of an Array After Marking All Elements

You are given an array nums consisting of positive integers.

Starting with score = 0, apply the following algorithm:

Choose the smallest integer of the array that is not marked. If there is a tie, choose the one with the smallest index.
1. Add the value of the chosen integer to score.
2. Mark the chosen element and its two adjacent elements if they exist.
3. Repeat until all the array elements are marked.
4. Return the score you get after applying the above algorithm.

Example 1:

Input: nums = [2,1,3,4,5,2]
Output: 7
Explanation: We mark the elements as follows:
- 1 is the smallest unmarked element, so we mark it and its two adjacent elements: [2,1,3,4,5,2].
- 2 is the smallest unmarked element, so we mark it and its left adjacent element: [2,1,3,4,5,2].
- 4 is the only remaining unmarked element, so we mark it: [2,1,3,4,5,2].
Our score is 1 + 2 + 4 = 7.


Example 2:

Input: nums = [2,3,5,1,3,2]
Output: 5
Explanation: We mark the elements as follows:
- 1 is the smallest unmarked element, so we mark it and its two adjacent elements: [2,3,5,1,3,2].
- 2 is the smallest unmarked element, since there are two of them, we choose the left-most one, so we mark the one at index 0 and its right adjacent element: [2,3,5,1,3,2].
- 2 is the only remaining unmarked element, so we mark it: [2,3,5,1,3,2].
Our score is 1 + 2 + 2 = 5.
 

Constraints:

1 <= nums.length <= 10^5
1 <= nums[i] <= 10^6
*/

class Solution {
    public long findScore(int[] nums) {
        int n = nums.length;
        long res = 0;

        //使用PriorityQueue对于 nums[] 进行排序
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            if(nums[a] == nums[b]){
                return a - b;
            }
            return nums[a] - nums[b];
        });

        //PriorityQueue里面存入的是nums[] array中每个元素数字的坐标
        for(int i = 0; i < n; i++){
            pq.add(i); // add the index into priorityqueue and sort it
        }
        
        //start to travser all the element
        boolean[] visisted = new boolean[n]; //用来标记元素
        while(!pq.isEmpty()){
            //每次提取出，当前最小元素的坐标
            int currIndex = pq.poll(); // get the first smallest number 
            if(visisted[currIndex]){
                continue; //停止遍历当前元素的后续循环，直接移动到下一个元素
            }
            visisted[currIndex] = true; //标记这个元素，避免再次计算
            res += nums[currIndex]; //把这个最小值加入res中
            
            //因为标记选中的元素及其相邻的两个元素
            if(currIndex > 0){ //所以位置如果不是第一个元素，则左边的元素也要标记
                visisted[currIndex - 1] = true;
            }

            if(currIndex < n - 1){ //所以位置如果不是最后一个元素，则右边的元素也要标记
                visisted[currIndex + 1] = true;
            }
        }

        return res;
    }
}
