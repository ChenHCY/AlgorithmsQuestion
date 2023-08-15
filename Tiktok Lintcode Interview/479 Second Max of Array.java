/*Lintcode 479 · Second Max of Array
Find the second max number in a given array.

Example1:

Input: [1,3,2,4]
Output: 3
Example2:

Input: [1,1,2,2]
Output: 2

*/

public class Solution {
    /**
     * @param nums: An integer array
     * @return: The second max number in the array.
     */
    public int secondMax(int[] nums) {
        // write your code here
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);

        for(int num : nums){
            pq.add(num);
            if(pq.size() > 2){
                pq.poll();
            }
        }

        return pq.peek();
    }
}
