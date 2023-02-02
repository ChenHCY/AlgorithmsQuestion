/* Leetcode 207. Course Schedule

There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. 
You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.

Return true if you can finish all courses. Otherwise, return false.

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0. So it is possible.


Example 2:

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
 

Constraints:

1 <= numCourses <= 2000
0 <= prerequisites.length <= 5000
prerequisites[i].length == 2
0 <= ai, bi < numCourses
All the pairs prerequisites[i] are unique.
*/

class Solution {
    boolean[] visited; //save the nodes has visited before
    boolean[] onePath; // save the traverse path
    boolean hasCycle = false; // save whether the graph have cycle

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //get the graph
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);

        //declra the size of visited[] and onePath[]
        visited = new boolean[numCourses];
        onePath = new boolean[numCourses];

        //travserse all the node from numCourses
        for(int i = 0; i < numCourses; i++){
            traverse(graph, i);
        }

        if(hasCycle){ //if there is have cycle ==> it means can not finished course
            return false;
        }

        return true; //if did not have any cycle ==> return true
    }

    //DFS function: traverse all the elements from prerequisites[][] and check whether have cycle
    public void traverse(List<Integer>[] graph, int currNode){
        //the exit condition ==> meet the node has saved the pervious path before
        if(onePath[currNode]){
            hasCycle = true;
        }

        //if has the cycle, stop traverse it ==> 1. the node visited before 2. find the cycle
        if(visited[currNode] || hasCycle){
            return;
        }

        //save the curr node has visited it and the path of node
        visited[currNode] = true;
        onePath[currNode] = true;

        //move to the next node of the graph path
        for(int nextNode : graph[currNode]){
            traverse(graph, nextNode);
        }

        //change back to false ==> backtracking
        onePath[currNode] = false;
    }

    //the function to used build graph
    public List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
        //邻接表 ==> graph[s] 是一个列表，存储着节点 s 所指向的节点。
        List<Integer>[] graph = new LinkedList[numCourses]; 

        //Declare and add the empty list frist into garph[]
        for(int i = 0; i < numCourses; i++){
            graph[i] = new LinkedList<>();
        }

        //traverse all the nodes from prerequisites[][], and find all the next course of the curr course 
        //Then save into graph, every current course with their the next course
        for(int[] edge : prerequisites){
            int end = edge[0], start = edge[1];
            // 添加一条从 当前course 指向 下一届课 的有向边
            // 边的方向是「被依赖」关系，即修完课程 start 才能修课程 end
            graph[start].add(end);
        }

        return graph; //the output the graph about prerequisites
    }
}
