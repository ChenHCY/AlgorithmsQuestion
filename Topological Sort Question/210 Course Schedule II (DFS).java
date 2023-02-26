/* Leetcode 210. Course Schedule II

There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. 

You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return the ordering of courses you should take to finish all courses. If there are many valid answers, 

return any of them. If it is impossible to finish all courses, return an empty array.

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].

Example 2:

Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
Output: [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. 

Both courses 1 and 2 should be taken after you finished course 0.
So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].

Example 3:

Input: numCourses = 1, prerequisites = []
Output: [0]
 

Constraints:

1 <= numCourses <= 2000
0 <= prerequisites.length <= numCourses * (numCourses - 1)
prerequisites[i].length == 2
0 <= ai, bi < numCourses
ai != bi
All the pairs [ai, bi] are distinct.
*/

 /* 
    题意和207题一样，给定课程数量，和课程的依赖关系图。
    
    判断课程能否上完（即课程图中是否存在环，导致无法上完所有课程）==》区别在于这题要输出能上完所有课程的路径
    
    
    **** 207题思路：其实这个题目就是检查一个DAG图中 是否存在环，如果有 则代表无法完成全部课程
    
    "如果发现这幅有向图中存在环，那就说明课程之间存在循环依赖，肯定没办法全部上完；反之，如果没有环，那么肯定能上完全部课程。"
    
     1. 那首先第一步：构建一个有向图 ==》 这种有向图的构建 通常使用的是邻接表，比如下面这种结构：List<Integer>[] graph; ==》 graph[s] 是一个列表，存储着节点 s 所指向的节点。
      然后我就得到了一张图，包括每个节点指向的的下一个节点： graph[currCourse].add(nextCourse)
      ==》 for example: graph[0].add(1); graph[0].add(2) 表示 可以通过 0 指向 1 和 2（0 是 1 2 的前缀课）

    2. 然后第二步：遍历所有节点（课程），来检查是否存在 “环”
    =》 注意图中并不是所有节点都相连，所以要用一个 for 循环将所有节点都作为起点调用一次 DFS 搜索算法。
    
    *** 核心就是判断一幅有向图中是否存在环。
    
    **** 210题的延伸思路：因为我们要找到一条能完成所有课程的路径，所以也就是对于这个DAG有向无环图进行拓扑排序
    
    首先我们需要知道，拓扑排序的的意思就是把一幅图「拉平」，而且这个「拉平」的图里面，所有箭头方向都是一致的
    所以有环的情况下是无法进行拓扑排序的，必须是“有向无环图(Directed Acyclic Graph)”

    如果把课程抽象成节点，课程之间的依赖关系抽象成有向边，那么这幅图的拓扑排序结果就是上课顺序。

    Step 1: 判断题目中课程的依赖关系是否存在环，如果存在 不能进行拓扑排序
    Step 2: 如何进行拓扑排序 ==》 其实将后序遍历的结果进行反转，就是拓扑排序的结果。
    ==> graph[from].add(to);
*/

class Solution {
    //全局变量
    List<Integer> list = new ArrayList<>(); //记录后序遍历的结果
    boolean hasCycle = false; // 记录是否存在环
    boolean[] visited; //save the nodes has visited before 这个只记访问过的节点
    boolean[] onePath; // save the traverse path 这个记节点是否在之前的路径上
    
    // 主函数
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //get the graph
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);

        //申明visited[]和onePath[]
        visited = new boolean[numCourses];
        onePath = new boolean[numCourses];

        //遍历所有课程，travserse all the node from numCourses
       //因为图中并不是所有节点都相连，所以要用一个 for 循环将所有节点都作为起点调用一次 DFS 搜索算法。
        for(int i = 0; i < numCourses; i++){
            traverse(graph, i);
        }

        // 有环图无法进行拓扑排序
        if (hasCycle) { //if there is have cycle ==> it means can not finished course
            return new int[]{};
        }

        Collections.reverse(list); // 将后序遍历的结果进行反转，就是拓扑排序的结果。
        //该变形式，change the list to be int[] array
        int[] res = new int[list.size()];
        for(int i = 0; i < res.length; i++){
            res[i] = list.get(i);
        }

        return res;
    }

    //DFS function: 检查DAG图中是否存在环
    public void traverse(List<Integer>[] graph, int currNode){
        //exit condition
        //发现DAG图中存在环
        if(onePath[currNode]){
            hasCycle = true;
        }
        //if has the cycle, stop traverse it ==> 1. the node visited before 2. find the cycle
        if(visited[currNode] || hasCycle){
            return;
        }

        //前序遍历，save the curr node has visited it and the path of node
        //回溯算法的道理： 在进入每个节点的时候将 onPath[节点] visited[节点]标记为 true，
        visited[currNode] = true; 
        onePath[currNode] = true;

        //Move to the next node of the graph path
        //移动到路径的下一个节点
        for(int nextNode : graph[currNode]){
            traverse(graph, nextNode);
        }

        list.add(currNode); //后序遍历统计节点到list

        //change back to false ==> backtracking，离开的时候改变onPath[节点] 回去false 
        // 这样如果发现 onPath[s] 已经被标记true，就说明出现了环。
        onePath[currNode] = false;
    }

    //构建DAG图的函数
    public List<Integer>[] buildGraph(int numCourses, int[][] prerequisites){
        //邻接表 ==》拓扑排序的必用方法 建立图中每个点的链接关系
        List<Integer>[] graph = new LinkedList[numCourses]; 
        
        //首先给graph中的每一个点申明一个空的LinkedList
        for(int i = 0; i < numCourses; i++){
            graph[i] = new LinkedList<>();
        }

        //然后给建立每个点的链接关系
        //traverse all the nodes from prerequisites[][], and find all the next course of the curr course 
        //Then save into graph, every current course with their the next course
        for(int[] edge : prerequisites){
            int start = edge[1];
            int end = edge[0];
            // 添加一条从 当前course 指向 下一届课 的有向边
            // 边的方向是「被依赖」关系，即修完课程 start 才能修课程 end
            graph[start].add(end);
        }

        return graph; //the output the graph about prerequisites
    }
}
