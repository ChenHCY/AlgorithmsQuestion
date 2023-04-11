/* Leetcode 1129. Shortest Path with Alternating Colors

You are given an integer n, the number of nodes in a directed graph where the nodes are labeled from 0 to n - 1. 

Each edge is red or blue in this graph, and there could be self-edges and parallel edges.

You are given two arrays redEdges and blueEdges where:

redEdges[i] = [ai, bi] indicates that there is a directed red edge from node ai to node bi in the graph, and
blueEdges[j] = [uj, vj] indicates that there is a directed blue edge from node uj to node vj in the graph.

Return an array answer of length n, where each answer[x] is the length of the shortest path from node 0 to node x such that the 
edge colors alternate along the path, or -1 if such a path does not exist.

Example 1:

Input: n = 3, redEdges = [[0,1],[1,2]], blueEdges = []
Output: [0,1,-1]

Example 2:

Input: n = 3, redEdges = [[0,1]], blueEdges = [[2,1]]
Output: [0,1,-1]
 

Constraints:

1 <= n <= 100
0 <= redEdges.length, blueEdges.length <= 400
redEdges[i].length == blueEdges[j].length == 2
0 <= ai, bi, uj, vj < n
*/

/* 

时间复杂度：O(n + r + b)，其中 n 是节点数，r 是红色边的数目，b 是蓝色边的数目。
==> 广度优先搜索最多访问一个节点两次，最多访问一条边一次，因此时间复杂度为 O(n + r + b)

空间复杂度：O(n + r + b)。
==> 队列中最多有 2n 个元素，保存 endNodeList 需要 O(r+b) 的空间，保存 distance[][] list 需要 O(n) 的空间。

*/

class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        //首先使用一个二维的邻接表来储存颜色和开始点能到达的终止点列表 
        // 0 表示 红色， 1表示蓝色
        List<Integer>[][] endNodeList = new List[2][n]; 
        //然后初始化这个邻接表，所有节点都是空的list
        for(int color = 0; color < 2; color++){
            for(int startNode = 0; startNode < n; startNode++){
                endNodeList[color][startNode] = new ArrayList<Integer>();
            }
        }

        //然后把已知的所有颜色边线的起始点和终止点 储存进 邻接表中
        // 从节点redLine[0]到节点redLine[1]有一条红边，即从节点redLine[[0]的红色有向边可以到达节点redLine[[1]
        for(int[] redLine : redEdges){
            endNodeList[0][redLine[0]].add(redLine[1]);
        }
        // 从节点blueLine[0]到节点blueLine[1]有一条红边，即从节点redLine[[0]的红色有向边可以到达节点redLine[[1]
        for(int[] blueLine : blueEdges){
            endNodeList[1][blueLine[0]].add(blueLine[1]);
        }

        //统计和查找题意要求两种颜色交叉使用，然后最短的路径长度
        // distance[c][i] 表示从颜色为c的有向边到达节点i，此时的最短路径长度
        int[][] distance = new int[2][n];
        //初始化distance，因为是找到达某个节点的最短的距离，所以每个空格都存入最大值方便后面比较
        for(int i = 0; i < 2; i++){
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }
        // 初始化为无穷，所以两种颜色的边到达节点0的最短距离都为0 ==》也表示起点
        distance[0][0] = 0;
        distance[1][0] = 0;
        
        //BFS 宽度优先搜索 ==》 0表示红色 1表示蓝色。 1 - 0 = 1;  1 - 1 = 0 
        //队列中的元素 pair = [color, x]表示从颜色为color的有向边到达节点x，
        //然后以x为起点去查找走颜色为 1-color 的颜色边可以到达的节点
        Queue<int[]> queue = new ArrayDeque<int[]>(); //BFS 使用 Queue来储存和遍历 路径

        //初始化 BFS ==》 相当于 queue.offer(root);
        queue.offer(new int[]{0, 0});
        queue.offer(new int[]{1, 0});

        //遍历Queue的所有元素节点 ==》得到能到达每个节点，需要的最短路径
        while(!queue.isEmpty()){
            int[] pair = queue.poll();
            int currColor = pair[0]; //the curr color of line
            int sNode = pair[1]; //the start Node
            for(int nextNode : endNodeList[1 - currColor][sNode]){
                //如果下个节点的距离已经计算过，则直接跳过 ==> 检查值是否更新过
                if(distance[1 - currColor][nextNode] != Integer.MAX_VALUE){
                    continue;
                }
                //如果下个节点没有计算和访问过
                distance[1 - currColor][nextNode] = distance[currColor][sNode] + 1; //更新到达下一个节点的距离
                queue.offer(new int[]{1 - currColor, nextNode});
            }
        }

        int[] res = new int[n]; // 从dist表中得到到达每个节点的最短路径, 储存到一个新的array
        for(int i = 0; i < n; i++){
            res[i] =  Math.min(distance[0][i], distance[1][i]);
            if(res[i] == Integer.MAX_VALUE){ //if the value did not change, no path exist that can arrive this node
                res[i] = -1; //return -1 if such a path does not exist.
            }
        }

        return res;
    }
}
