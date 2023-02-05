/* Leetcode (Contest 331) 2558 Take Gifts From the Richest Pile

You are given an integer array gifts denoting the number of gifts in various piles. Every second, you do the following:

    1. Choose the pile with the maximum number of gifts.
    2. If there is more than one pile with the maximum number of gifts, choose any.
    3. Leave behind the floor of the square root of the number of gifts in the pile. Take the rest of the gifts.
    
Return the number of gifts remaining after k seconds.

Example 1:

Input: gifts = [25,64,9,4,100], k = 4
Output: 29
Explanation: 
The gifts are taken in the following way:
- In the first second, the last pile is chosen and 10 gifts are left behind.
- Then the second pile is chosen and 8 gifts are left behind.
- After that the first pile is chosen and 5 gifts are left behind.
- Finally, the last pile is chosen again and 3 gifts are left behind.
The final remaining gifts are [5,8,9,4,3], so the total number of gifts remaining is 29.


Example 2:

Input: gifts = [1,1,1,1], k = 4
Output: 4
Explanation: 
In this case, regardless which pile you choose, you have to leave behind 1 gift in each pile. 
That is, you can't take any pile with you. 
So, the total gifts remaining are 4.
 

Constraints:
1 <= gifts.length <= 103
1 <= gifts[i] <= 109
1 <= k <= 103
*/

/*
使用PriorityQueue进行排序， 然后每次提取第一个数（也是值最大的数）

然后进行开方，然后再存入PriorityQueue中 重新排序

重复K次 ==》 最后得到一个list, 相加得到总和

*/
class Solution {
    public long pickGifts(int[] gifts, int k) {
        long res = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((n1, n2) -> n2 - n1);
        
        for(int num : gifts){
            pq.offer(num);
        }
        
        for(int i = 0; i < k; i++){
            double temp = pq.poll();
            int sqrtNumber = (int)Math.sqrt(temp);
            pq.offer(sqrtNumber);
        }
        
        while(!pq.isEmpty()){
            res += pq.poll();
        }
        
        return res;
    }
    
    
}
