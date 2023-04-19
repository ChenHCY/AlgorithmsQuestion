/* Leetcode 373. Find K Pairs with Smallest Sums

You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.

Define a pair (u, v) which consists of one element from the first array and one element from the second array.

Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.

Example 1:

Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
Output: [[1,2],[1,4],[1,6]]
Explanation: The first 3 pairs are returned from the sequence: [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]

Example 2:

Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
Output: [[1,1],[1,1]]
Explanation: The first 2 pairs are returned from the sequence: [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]

Example 3:

Input: nums1 = [1,2], nums2 = [3], k = 3
Output: [[1,3],[2,3]]
Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]
 

Constraints:

1 <= nums1.length, nums2.length <= 10^5
-10^9 <= nums1[i], nums2[i] <= 10^9
nums1 and nums2 both are sorted in ascending order.
1 <= k <= 10^4
*/

/* 思路：此题是输出nums1[] 和 nums2[]中所有能组成的对中 前K个 总和值小的

蛮力解法就是：两个for-loop, 把nums1 和 nums2 两个数组的数两两组合，然后排个序，取前 k 个返回。

==》但因为数据范围是 1 <= nums1.length, nums2.length <= 10^5， 所以两两组合后的数据大小为 10^8 ==》 一定超时 

并且因为nums1[] 和 nums2[] 都是按升序排列的，所以最小的组合值肯定是 nums1[0] + nums2[0]。

==》 **** 但我们无法知道第二小的值是 nums1[0] + nums2[1] 还是 nums1[1] + nums2[0] ******* 

所以我们要采用PriorityQueue优先队列 来优化时间复杂度 和 进行比较：PriorityQueue 里面存的是nums1[] nums2[]中的index位置值

首先把 nums1[] array中的所有数 和 nums2[]第一个数能组成的 对Pairs加入到优先队列中 ==》 [0,0]、[1, 0]、[2, 0]、…… 入队
==> 让nums2[] 的index索引全部从 0 开始 

==》然后while-loop 每次弹出 nums1[index1] + nums2[index2] 较小者。

==》再把 index2 后移一位继续添加到优先级队列中，依次往复，最终弹出 k 次就是我们的结果 ==》 前K个 总和值小的。

For Example: nums1 index is 0,  nums2 index: 1 

运行过程：

1. 先把所有的 nums1 的索引入队，即入队的元素有 [0, 0]、[1, 0]、[2, 0]、[3, 0]

2. 然后弹出[0, 0] ==> 因为nums1[] 和 nums2[] 都是按升序排列的，所以最小的组合值肯定是 nums1[0] + nums2[0]。

3. 再把[0, 1] 加入到优先队列，这样就可以通过优先级队列比较 [0, 1] 和 [1, 0] 的结果，再弹出较小者；

4. 依次进行，进行 k 轮。 ==》 最后得到nums1[] 和 nums2[] 能组成的前K对小的组合
*/

//Time: O(klogk)  Space: O(k)
class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();

        //对是nums1[]和nums2[]各一个数组成，优先队列按照 对 的总和值的大小进行升序排列
        //pq里面存的是nums1[] nums2[]中的index位置值
        PriorityQueue<int[]> pq = new PriorityQueue<>(k, (a, b) -> {
            return (nums1[a[0]] + nums2[a[1]]) - (nums1[b[0]] + nums2[b[1]]);
        });

        //首先把nums1[] array中的所有数 和 nums2[]第一个数能组成的 对Pairs加入到优先队列中
        //nums2 的索引全部从 0 开始 
        for(int i = 0; i < nums1.length; i++){
            pq.add(new int[]{i, 0}); //pq里面存的是nums1[] nums2[]中的index位置值
        }

        //然后进行遍历，弹出 k 次就可以找到matirx中 K 对 总和最小的Pairs
        while(k != 0 && !pq.isEmpty()){
            //每次弹出 nums1[index1] + nums2[index2] 较小者
            int[] pairs = pq.poll(); //首先弹出当前能拿到的最小的对值
            //paris[0] 是nums1[]中的index位置  paris[1] 是nums2[]中的index位置 

            k--; //弹出一个数，K值-1

            //题目是要输出前k对总和最小的值, 所以每次弹出的Pairs 都存入res list中 ==》最后输出
            res.add(List.of(nums1[pairs[0]], nums2[pairs[1]]));

            //得到下一个对值，弹出之后，再把 index2 后移一位继续添加到优先级队列中
            int nextIndex = pairs[1] + 1; //因为我们是以nums1[]为基础建立的映射

            //如果nums2[]中还有数字可以继续组成Pairs，则继续加入到优先队列中
            if(nextIndex < nums2.length){
                pq.offer(new int[]{pairs[0], nextIndex});
            }
        }

        return res;
    }
}
