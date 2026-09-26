/* 684. Redundant Connection - Union-Find

In this problem, a tree is an undirected graph that is connected and has no cycles.
You are given a graph that started as a tree with n nodes labeled from 1 to n, with one additional edge added. The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed. The graph is represented as an array edges of length n where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the graph.
Return an edge that can be removed so that the resulting graph is a tree of n nodes. If there are multiple answers, return the answer that occurs last in the input.

Example 1:
Input: edges = [[1,2],[1,3],[2,3]]
Output: [2,3]

Example 2:
Input: edges = [[1,2],[2,3],[3,4],[1,4],[1,5]]
Output: [1,4]

Constraints:
n == edges.length
3 <= n <= 1000
edges[i].length == 2
1 <= ai < bi <= edges.length
ai != bi
There are no repeated edges.
The given graph is connected.

*/

// 时间复杂度是排序的 O(M log M)，Union-Find 部分接近 O(M)。
/**
 * @param {number[][]} edges
 * @return {number[]}
 */
// create a map to store the connections of each edge
// union find: 
// find(edge): find the root of this edge
// union(edge1, edge2): check each edge root and whether it is the same or a cycle 
var findRedundantConnection = function(edges) {
    const parent = new Map();
    let n = edges.length;

    // push each edge connect relationship into map first
    for(let i = 1; i <= n; i++){
        parent.set(i, i);
    }

    // find(edge)
    const find = (edge) => {
        if(parent.get(edge) !== edge){
            parent.set(edge, find(parent.get(edge))); //递归找到root
        }
        return parent.get(edge);
    }

    // union(edgeA, edgeB): check whether it can connect them, or it has a cycle
    const union = (edgeA, edgeB) => {
        const rootA = find(edgeA);
        const rootB = find(edgeB);

        //出现环，所以这个链接是可以删除的 =》 二叉树不能有环
        if(rootA === rootB){
            return false;
        }

        parent.set(rootA, rootB); // 如果不存在环，则继续从root 链接后面的
        return true;
    }

    for(const edge of edges){
        if(!union(edge[0], edge[1])){
            return edge;
        }
    }

    return [];
};
