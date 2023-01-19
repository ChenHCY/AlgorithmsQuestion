/* Leetcode 1443. Minimum Time to Collect All Apples in a Tree

Given an undirected tree consisting of n vertices numbered from 0 to n-1, which has some apples in their vertices. 
You spend 1 second to walk over one edge of the tree. Return the minimum time in seconds you have to spend to collect 
all apples in the tree, starting at vertex 0 and coming back to this vertex.

The edges of the undirected tree are given in the array edges, where edges[i] = [ai, bi] means that exists an edge connecting 
the vertices ai and bi. Additionally, there is a boolean array hasApple, where hasApple[i] = true means that vertex i has an apple; 
otherwise, it does not have any apple.

Example 1:
Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,true,true,false]
Output: 8 
Explanation: The figure above represents the given tree where red vertices have an apple. 
One optimal path to collect all apples is shown by the green arrows.  

Example 2:
Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,false,true,false]
Output: 6
Explanation: The figure above represents the given tree where red vertices have an apple. 
One optimal path to collect all apples is shown by the green arrows.  

Example 3:

Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,false,false,false,false,false]
Output: 0
 

Constraints:
1 <= n <= 105
edges.length == n - 1
edges[i].length == 2
0 <= ai < bi <= n - 1
hasApple.length == n
*/

/* 思路：
问题条件： 主函数“minTime”有 3 个参数：

1. n：树中的节点数
2. edges：表示树的边的二维数组
3. hasApple：一个布尔值列表，指示每个节点是否有苹果

思路：
1. 用HashMap储存edgs, 也就是node的每一条路径。==> Map<Integer, List<Integer>> adj = new HashMap<>();

2. 然后dfs的递归，计算每个节点的时间  ==> childTime = dfs(child, curNode, adj, hasApple);

3. 然后if-statement检查每个节点是否有苹果 或者 在有苹果的路径上（子节点有苹果)  ==> if(childTime > 0 || hasApple.get(child))

3. 如果有就加到childtime，然后计算得到一个总的时间 ==> 因为是一来一回，所以 totalTime += childTime + 2

*/
//Time: O(n)   Space: O(n)
class Solution {
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        //used the hashMap to save all the root
        Map<Integer, List<Integer>> adj = new HashMap<>();
        //check all the root and save into HashMap
        for(int[] edge : edges){
            int start = edge[0];
            int end = edge[1];
            //computeIfAbsent() 方法对 hashMap 中指定 key 的值进行重新计算，如果不存在这个 key，则添加到 hashMap 中。      
            adj.computeIfAbsent(start, value -> new ArrayList<Integer>()).add(end); //parent node go down child node 
            adj.computeIfAbsent(end, value -> new ArrayList<Integer>()).add(start); //child node go back to parent node
        }
        //call dfs function
        return dfs(0, -1, adj, hasApple);
    }

    //dfs function
    public static int dfs(int curNode, int parentNode, Map<Integer, List<Integer>> adj, List<Boolean> hasApple){
        //exit addition ==> if the curNode did not have any child node
        if(!adj.containsKey(curNode)){
            return 0;
        }

        int totalTime = 0;
        int childTime = 0;
        //main for-loop to travser all the element node one by one (depth first search)
        for(int child : adj.get(curNode)){
            if(child == parentNode){
                continue; // if the child node is parent node, just skip it
            }
            // for each children, get the time needed to collect all apples from its subtree
            childTime = dfs(child, curNode, adj, hasApple);
            // if the children has apples or has a subtree with apples, add the time to total time
            if(childTime > 0 || hasApple.get(child)){ // childTime > 0  means has apples or has a subtree with apples
                totalTime += childTime + 2; //go and back, so the time need add 2
            }
        }

        return totalTime;
    }
}
