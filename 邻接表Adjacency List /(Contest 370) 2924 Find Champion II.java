/* 2924. Find Champion II

There are n teams numbered from 0 to n - 1 in a tournament; each team is also a node in a DAG.

You are given the integer n and a 0-indexed 2D integer array edges of length m representing the DAG, where edges[i] = [ui, vi] indicates that there is a directed edge from team ui to team vi in the graph.

A directed edge from a to b in the graph means that team a is stronger than team b and team b is weaker than team a.

Team a will be the champion of the tournament if there is no team b that is stronger than team a.

Return the team that will be the champion of the tournament if there is a unique champion, otherwise, return -1.

Notes:

A cycle is a series of nodes a1, a2, ..., an, an+1 such that node a1 is the same node as node an+1, the nodes a1, a2, ..., an are distinct, and there is a directed edge from the node ai to node ai+1 for every i in the range [1, n].
A DAG is a directed graph that does not have any cycle.
 
Example 1:
Input: n = 3, edges = [[0,1],[1,2]]
Output: 0
Explanation: Team 1 is weaker than team 0. Team 2 is weaker than team 1. So the champion is team 0.

Example 2:
Input: n = 4, edges = [[0,2],[1,3],[1,2]]
Output: -1
Explanation: Team 2 is weaker than team 0 and team 1. Team 3 is weaker than team 1. But team 1 and team 0 are not weaker than any other teams. So the answer is -1.
 

Constraints:
1 <= n <= 100
m == edges.length
0 <= m <= n * (n - 1) / 2
edges[i].length == 2
0 <= edge[i][j] <= n - 1
edges[i][0] != edges[i][1]
The input is generated such that if team a is stronger than team b, team b is not stronger than team a.
The input is generated such that if team a is stronger than team b and team b is stronger than team c, then team a is stronger than team c.
*/

// 使用邻接表Adjacency list来保存每个队伍之间的 胜负 关系
// 因为存在于叠加的关系 =》 1 队比 0 队弱。2 队比 1 队弱。所以冠军是 0 队。很难直接统计到 每个队伍到底比谁强
// ==> 所以我们思考去统计每个队伍输给哪些球队，如果一个队伍 一场没输 ==> 代表这就是最强队伍
class Solution {
    // 有向无环图 ==> 邻接表
    public int findChampion(int n, int[][] edges) {
        int res = 0; // 最强队伍
        List<Integer>[] map = new ArrayList[n + 1];
        Arrays.setAll(map, e -> new ArrayList<>()); //首先在邻接表中创建空的集合

        // 把有向无环图中所有的路径按照 起点[终点]的形式 存入邻接表中
        for(int[] edge : edges){
            int winTeam = edge[0];
            int loseTeam = edge[1];
            map[loseTeam].add(winTeam); // 所以我们思考去统计每个队伍输给哪些球队，如果一个队伍 一场没输 
        }

        // 检查有哪些队伍一场没输 ==> 也就是最强队伍
        int count = 0; // 统计有多少个队伍一场没输
        for(int i = 0; i < n; i++){
          //==> 如果一个队伍 一场没输， 代表这就是最强队伍
            if(map[i].size() == 0){
                res = i;
                count++;
            }

            // 如果这场比赛存在 唯一 一个冠军，则返回将会成为冠军的队伍。否则，返回 -1 。
            // 如果存在有多个冠军的队伍，输出-1
            if(count > 1){
                return -1;
            }
        }

        return res;
    }
}
