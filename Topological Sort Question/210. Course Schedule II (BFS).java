/* Leetcode 210. Course Schedule II
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. 

You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. 

If it is impossible to finish all courses, return an empty array.

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].

Example 2:

Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
Output: [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be 
taken after you finished course 0.
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


/* BFS 版本：入度 + 出度

           此题 BFS算法思想就是 把每节课程 看成 一个 节点，然后
           借助 一个 indegree 数组 记录和计算每个节点的「入度」也就是每节课的前置课程数量

           1. 然后从没有任何前置课程的根节点课程开始遍历和进行拓扑排序，
           2. 对 BFS 队列进行初始化，将入度为 0 的节点首先装入队列。
           3. 开始执行 BFS 循环，不断弹出队列中的节点（课程），减少课程的依赖关系（前置课），
           4. 当 这个课程所有前置课都已经完成后，（也就是入度变为 0 的节点），把这节课加入队列。
           5. 如果最终所有节点都被遍历过（index 等于节点数），则说明所有课程都能完成，不存在相互依赖的关系，也就是DAG图中不存在环，反之则说明存在环
*/
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses]; // 构建入度数组
        //indegree 数组也能实现的 visited 数组的作用

        int[] res = new int[numCourses]; //储存课程的路径
        int index = 0; // Pointer，用来记录完成课程的数量

        // 遍历课程的依赖关系，查找哪些课程存在前置课
        for(int[] edge : prerequisites){
            int start = edge[1]; //前置课
            int end = edge[0]; //当前课程
            indegree[end]++; //代表这个课程存在前置课，也记录当前课程 前置课的数量
        }

        // 根据入度初始化和找到队列中的根节点 ==》相当于 queue.add(root);
        Queue<Integer> queue = new LinkedList<>(); //用来存储可以选择课（没有前置课，或者已经完成所有前置课）
        for(int i = 0; i < indegree.length; i++){
            if(indegree[i] == 0){ //代表这个课程没有前置课，可以进行
                queue.offer(i); // ==》这一步相当于BFS中的加入最初的根节点queue.add(root);
                res[index++] = i; //记录当前课程到可以上课的路径 
            }
        }

        // 记录拓扑排序结果, 开始执行 BFS 算法
        while(!queue.isEmpty()){
            int curr = queue.poll(); //Queue中存的都是可以上的课，每次弹出一节课
            for(int[] course : prerequisites){
                if(course[1] == curr){ //检查当前课程是否是哪节课程（A）的前置课
                    indegree[course[0]]--; //减少indegree中A课程的前置课数量
                    if(indegree[course[0]] == 0){ //当A课程的所有前置课都已经完成
                        queue.offer(course[0]); //把A课程加入到queue中
                        res[index++] = course[0]; //记录A课程到上课路径，拓扑排序
                    }
                }
            }
        }

        //如果此题DAG图中存在环，则无法完成全部课程
        if(index != numCourses){ 
            return new int[]{}; //输出一个空的数组
        }

        return res;
    }
}
