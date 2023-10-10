/* 1192. Critical Connections in a Network

There are n servers numbered from 0 to n - 1 connected by undirected server-to-server connections forming a network where connections[i] = [ai, bi] 
represents a connection between servers ai and bi. Any server can reach other servers directly or indirectly through the network.

A critical connection is a connection that, if removed, will make some servers unable to reach some other server.

Return all critical connections in the network in any order.

Example 1:
Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
Output: [[1,3]]
Explanation: [[3,1]] is also accepted.

Example 2:
Input: n = 2, connections = [[0,1]]
Output: [[0,1]]
 

Constraints:

2 <= n <= 10^5
n - 1 <= connections.length <= 10^5
0 <= ai, bi <= n - 1
ai != bi
There are no repeated connections.
*/
// 时间复杂度 O(V+E) 
class Solution {
    // 邻接表 +  Tarjan 算法 ==> 使用DFS来找到network路径中的环，然后找到不在环上的路径和节点
    // 我们使用DFS来记录访问到每个节点的时间戳(序号)，和 追溯值
    // 如果当前节点的 时间戳 (序号) 大于 下一个节点的追溯值， ==> 表示两个节点存在一个环中
    // 如果当前节点的 时间戳 (序号) 小于 下一个节点的追溯值， ==> 表示不存在环中，是我们需要找的关键边(桥边)
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        //邻接表，储存每个节点 能链接的到的点
        List<Integer>[] nodeMap = new ArrayList[n];

        //给邻接表的每个位置都首先创建空的list队列
        Arrays.setAll(nodeMap, e -> new ArrayList<>());

        for(List<Integer> node : connections){
            int currNode = node.get(0);
            int nextNode = node.get(1);
            nodeMap[currNode].add(nextNode);
            nodeMap[nextNode].add(currNode);
        }

        List<List<Integer>> res = new ArrayList<>();

        // ord记录每个顶点被访问的时间戳，
        // low记录每个顶点能到达的时间戳最小的点的时间戳（这里的到达指的是非回头路的那种到达）
        int[] time = new int[n]; // dfs过程中，初次访问某个节点时的时间戳(每个节点的序号，来判断是否存在节点环)
        int[] low = new int[n]; // 每个节点的最小追溯值
        int[] num = {0}; // 自增数字，用来赋值给节点的时间戳 => 会在每次访问节点时递增，确保每个节点都有一个唯一的访问顺序号。

        //追溯值用来表示从当前节点 x 作为搜索树的根节点出发，能够访问到的所有节点中，时间戳最小的值 —— low[x]
        //==> 即从子节点回溯到祖先节点。如果子节点的最小追溯值小于当前节点的时间戳，那么说明存在回溯路径，这是查找强连通分量的一个关键标志

        //若从图中删除边 e 之后，图将分裂成两个不相连的子图，那么称 e 为图的桥或割边。
        
        //遍历每个节点
        dfs(0, 0, nodeMap, time, low, res, num);

        return res;
    }

    // Tarjan 算法 dfs function
    public static void dfs(int currNode, int preNode, List<Integer>[] map, int[] time, int[] low, List<List<Integer>> res, int[] num){
        low[currNode] = time[currNode] = num[0]++; //改变记录当前节点的 时间戳(序号) 和 追溯值(路径上的最小序号 用来判断是否 存在环) 
        
        //从邻接表中提取，根据路径继续遍历
        for(int nextNode : map[currNode]){
            if(time[nextNode] == 0){ //之前没有访问过下一个节点
                //使用dfs 来得到下一个时间的 时间戳(序号) 和 追溯值
                dfs(nextNode, currNode, map, time, low, res, num);
                //回溯改变，比较当前节点的追溯值和下一个节点的追溯值，确认两个节点是否在一个环中
                low[currNode] = Math.min(low[currNode], low[nextNode]);

                // **** 如果当前节点的 时间戳 (序号) 小于 下一个节点的追溯值， ==> 表示不存在环中，是我们需要找的关键边(桥边) ****
                if(time[currNode] < low[nextNode]){
                    res.add(Arrays.asList(currNode, nextNode));
                }
            } else if(time[nextNode] < time[currNode] && nextNode != preNode){ //如果下一个节点已经被访问过，且下一个节点不是前节点
                //直接进行回溯改变, 得到最小的节点序号，来判断有哪些节点存在于一个环中
                low[currNode] = Math.min(low[currNode], low[nextNode]);
            }
        }
    }
}
