/* 886. Possible Bipartition

We want to split a group of n people (labeled from 1 to n) into two groups of any size. 
Each person may dislike some other people, and they should not go into the same group.

Given the integer n and the array dislikes where dislikes[i] = [ai, bi] indicates that the person 
labeled ai does not like the person labeled bi, return true if it is possible to split everyone into two groups in this way.

Example 1:
Input: n = 4, dislikes = [[1,2],[1,3],[2,4]]
Output: true
Explanation: The first group has [1,4], and the second group has [2,3].

Example 2:
Input: n = 3, dislikes = [[1,2],[1,3],[2,3]]
Output: false
Explanation: We need at least 3 groups to divide them. We cannot put them in two groups.
 
Constraints:
1 <= n <= 2000
0 <= dislikes.length <= 10^4
dislikes[i].length == 2
1 <= ai < bi <= n
All the pairs of dislikes are unique.
*/

/* Solution: Used 染色法 + DFS 查找  Time: O(n + m)  Space: O(n + m)*/ 
// 其中 n 题目给定的人数，m 为给定的 dislike 数组的大小
// 题意：有n个人，同时存在dislike关系，检查能否把他们分成两组，其中不能存在dislike关系
// 思路：使用color[]进行染色标明分组情况，每次交替染色 ==> 然后检查dislike关系中 存不存在相同的颜色 
// 如果存在，表示无法完成分组  /  如果不存在，则可完成分组
class Solution {
    private int[] color; //储存染色情况，也就是每个数值的分组情况
    private List<Integer>[] list; //邻接表：储存dislike关系

    public boolean possibleBipartition(int n, int[][] dislikes) {
        color = new int[n + 1];
        list = new List[n + 1];

        //开空间
        for(int i = 1; i <= n; i++){
            list[i] = new ArrayList<>();
        }

        //储存dislike 关系
        for(int[] dis : dislikes){
            list[dis[0]].add(dis[1]);
            list[dis[1]].add(dis[0]);
        }

        //Main travser: 遍历每一个还没染色(分配组)的people, 然后进行染色
        for(int i = 1; i <= n; i++){
            if(color[i] == 0){
                if(!dfs(i, 1)){ // i 表示当前节点，1表示染色，也就是哪个组的
                    return false; //如果得到的dfs结果为false, 表示无法完成分组
                }
            }
        }

        return true; //可以分成两组且不存在dislike关系，输出true
    }

    public boolean dfs(int numberIndex, int currColor){
        color[numberIndex] = currColor; //给当前节点进行染色

        //然后检查当前numberIndex 的dislike关系，是否存在颜色一样(分组相同的)值
        for(int nextDislike : list[numberIndex]){
            //如果这个dislike节点已经染色（分组），检查是否和当前numberIndex的颜色（分组）一样
            if(color[nextDislike] != 0 && color[nextDislike] == color[numberIndex]){
                return false;
            }

            // 如果这个dislike节点还没有染色，DFS递归继续遍历和分组染色 ==> change下一个染色的颜色(分组)
            // 如果得到的dfs是false, 表示无法完成分组
            if(color[nextDislike] == 0 && !dfs(nextDislike, 3 - color[numberIndex])){
                return false;
            }
        }

        return true; //如果都满足要求，输出true
    }
}
