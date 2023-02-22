/* Leetcode 1046. Last Stone Weight

You are given an array of integers stones where stones[i] is the weight of the ith stone.

We are playing a game with the stones. On each turn, we choose the heaviest two stones and smash them together. 

Suppose the heaviest two stones have weights x and y with x <= y. The result of this smash is:

If x == y, both stones are destroyed, and
If x != y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.
At the end of the game, there is at most one stone left.

Return the weight of the last remaining stone. If there are no stones left, return 0.

Example 1:

Input: stones = [2,7,4,1,8,1]
Output: 1
Explanation: 
We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
we combine 1 and 1 to get 0 so the array converts to [1] then that's the value of the last stone.

Example 2:

Input: stones = [1]
Output: 1
 

Constraints:

1 <= stones.length <= 30
1 <= stones[i] <= 1000
*/

/* 题意是

在每一轮中，我们选择最重的两块石头并将它们砸在一起。假设最重的两块石头的重量x为y且 为x <= y。这次粉碎的结果是：

如果x == y，两块石头都被摧毁，并且
如果x != y，重量之石x被破坏，重量之石y有新的重量y - x。
游戏结束时，最多只剩下一颗棋子。
返回最后剩下的石头的重量。如果没有剩下的石头，返回0。

思路：

我们可以通过使用PriorityQueue优先队列来对于stones[] 进行排序， (a, b) -> b - a 降序排列

然后进行while-loop的遍历循环，直到pq的size()小于等于1

每轮循环，通过使用pq.poll()进行删除并且取队列中最大的两个数，然后进行比较

如果x != y，重量之石x被破坏，重量之石y有新的重量y - x。==》 重新加入（x-y)到队列中 

==》进行重复循环

==》直到pq的size()小于等于1 循环结束

我们就能得到最后剩下的哪一个棋子，或者什么都没有了返回0。

==》return pq.isEmpty() ? 0 : pq.peek();

*/

//Time： O(NlogN)   Space： O(N)
class Solution {
    public int lastStoneWeight(int[] stones) {
        //通过使用优先队列来对于stones[]进行排序 (a, b) -> b - a 降序排列
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        //把num加入到优先队列中，进行排序
        for(int num : stones){
            pq.offer(num);
        }
        //遍历优先队列中的所有元素，进行处理
        while(pq.size() > 1){ //当pq优先只剩下一个或者没有元素时，跳出循环
            int firstLargest = pq.poll();
            int secondLargest = pq.poll();

            //If x != y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.
            if(firstLargest != secondLargest){
                pq.offer(firstLargest - secondLargest);
            }
        } // At the end of the game, there is at most one stone left.

        //Return the weight of the last remaining stone. If there are no stones left, return 0.
        //最后判断pq中是否还存在元素，如果没有，返回 0 / 如果有 返回栈顶的元素
        return pq.isEmpty() ? 0 : pq.peek();
    }
}
