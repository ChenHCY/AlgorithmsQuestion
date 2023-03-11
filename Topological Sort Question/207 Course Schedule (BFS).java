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

/* 思路：
1. 统计课程安排图中每个节点的前置课数量（入度），生成 入度表 indegrees。

2. 借助一个队列 queue，将所有入度为 0 的节点入队， 也就是用来存储可以选择课（没有前置课，或者已经完成所有前置课

3. 开始进行BFS遍历：

==》 每次提取一节课， 检查当前课程是否是哪节课程（A）的前置课

==》 减少indegree中A课程的前置课数量，如果一个课程的indegrees数组值到0时，表示这节课可以被选择，所以放入queue队列中

==》最后统计上过课程的数量，检查是否与numCourses的数量一样，一样表示图中不存在环，如果不一样，代表图中有环，无法完成全部课程

*/
// 时间复杂度 O(N + M) 遍历一个图需要访问所有节点和所有临边，NN 和 MM 分别为节点数量和临边数量；
// 空间复杂度： O(N + M)
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses]; // 构建入度数组
        //indegree 数组也能实现的 visited 数组的作用
        int index = 0; // pointer, 记录能完成课程的数量

        // 遍历课程的依赖关系，查找哪些课程存在前置课
        for(int[] edge : prerequisites){
            int start = edge[1]; //前置课
            int end = edge[0];  //当前课程
            indegree[end]++; //代表这个课程存在前置课，也记录当前课程 前置课的数量
        }

        //BFS 根据入度初始化和找到队列中的根节点 ==》相当于 queue.add(root);
        Queue<Integer> queue = new LinkedList<>();//用来存储可以选择课（没有前置课，或者已经完成所有前置课）
        for(int i = 0; i < indegree.length; i++){
            if(indegree[i] == 0){ //代表这个课程没有前置课，可以进行
                queue.offer(i);  // ==》这一步相当于BFS中的加入最初的根节点queue.add(root);
                index++;
            }
        }

         // 记录拓扑排序结果, 开始执行 BFS 算法
        while(!queue.isEmpty()){
            int curr = queue.poll(); //Queue中存的都是可以上的课，每次弹出一节课
            for(int[] course : prerequisites){
                if(course[1] == curr){ //检查当前课程是否是哪节课程（A）的前置课
                    indegree[course[0]]--;//减少indegree中A课程的前置课数量
                    if(indegree[course[0]] == 0){ //当A课程的所有前置课都已经完成
                        queue.offer(course[0]);//把A课程加入到queue中
                        index++; //统计上过课程的数量
                    }
                }
            }
        }

        //如果此题DAG图中存在环，则无法完成全部课程
        if(index != numCourses){
            return false;  
        }

        return true; //如果完成的课程数量和numCourse一样，代表没有环，可以上完全部课程
    }
}
