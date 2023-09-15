/*Leetcode 310. Minimum Height Trees

A tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.

Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi] indicates that there is 
an undirected edge between the two nodes ai and bi in the tree, you can choose any node of the tree as the root. 

When you select a node x as the root, the result tree has height h. Among all possible rooted trees, those with minimum 
height (i.e. min(h))  are called minimum height trees (MHTs).

Return a list of all MHTs' root labels. You can return the answer in any order.

The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.

Example 1:
Input: n = 4, edges = [[1,0],[1,2],[1,3]]
Output: [1]
Explanation: As shown, the height of the tree is 1 when the root is the node with label 1 which is the only MHT.

Example 2:
Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
Output: [3,4]

 
Constraints:
· 1 <= n <= 2 * 104
· edges.length == n - 1
· 0 <= ai, bi < n
· ai != bi
· All the pairs (ai, bi) are distinct.
· The given input is guaranteed to be a tree and there will be no repeated edges.
*/


class Solution {
    //Bulid Graph 建图问题
    //从只有一条连接的节点，开始剪枝节点，最后剩下的就是最小高度的root
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        //base case, 如果没有节点
        if(edges == null || edges.length == 0){
            res.add(0);
            return res;
        }

        //建图，把每个节点 和 其所有链接点的关系 存在graph中
        List<HashSet<Integer>> graph = new ArrayList<>();

        //首先给每个节点先创建单独的空间
        for(int i = 0; i < n; i++){
            graph.add(new HashSet<>());
        }

        //然后把每个节点的链接 关系存入 graph中
        //因为是无向图，所以对应的两个点都要出入
        for(int[] edge : edges){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        //然后开始减枝（节点）
        for(int i = 0; i < graph.size(); i++){
            //因为要从只有一条连接的节点，统计最开始只有一条链接的节点
            if(graph.get(i).size() == 1){
                res.add(i);
            }
        }

        //main travser: 从只有一条连接的节点，开始剪枝节点
        //如果最后只剩下两个节点 或者 一个节点，则高度不会产生任何改变，都可以作为最小高度的root
        while(n > 2){

            n -= res.size(); //计算删除只有一条链接的节点后，还剩余几个节点
            List<Integer> list = new ArrayList<>(); //记录下一层需要剪枝的节点

            //res现在存入的是只有一条链接的节点，需要删除
            //所以提取删除节点的链接 之后，也要删除
            //比如 现在需要删除 0, 0节点和1节点是链接的，=> 0：[1]
            //所以 removeNode = 0 || sub = [1] => 删除1节点中的0节点，表示删除了对应的链接关系
            for(int removeNode : res){
                //所以提取剪枝节点的链接节点
                for(int sub : graph.get(removeNode)){
                    graph.get(sub).remove(removeNode); //删除1节点中的0节点，表示剪枝了对应的链接关系
                    //如果删除之后，sub节点也只剩下一条链接 则加入到list 下一层继续剪枝
                    if(graph.get(sub).size() == 1){
                        list.add(sub);
                    }
                }
            }

            res = list; //给res赋值下一层需要剪枝的节点
        }


        return res;
    }
}
