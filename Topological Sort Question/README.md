# 拓扑排序解决课程路径问题

**DFS深度优先遍历**

使用一个邻接表 `List<Integer>[] map = new ArrayList[n];` ==》来储存每个课程节点和其前置课要求

然后遍历每节课和其前置课要求，来判断map中是否存在环，如果存在则 无法完成所有课程


**BFS宽度优先遍历**

`int[] indegree = new int[numCourses];·` // 构建入度数组
构建一个`入度数组`，每个index对应每节课自己前置课的数量

然后继续使用一个队列来储存入度为0的课程，也就是表示可以上的课

根据遍历来删除对应课程的前置课数量，最后判断是否完成了所有课程数量

