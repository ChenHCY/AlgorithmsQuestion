/* Leetcode 429. N-ary Tree Level Order Traversal

Given an n-ary tree, return the level order traversal of its nodes' values.

Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).

Example 1:
Input: root = [1,null,3,2,4,null,5,6]
Output: [[1],[3,2,4],[5,6]]

Example 2:
Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
Output: [[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
 

Constraints:
The height of the n-ary tree is less than or equal to 1000
The total number of nodes is between [0, 10^4]
*/

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

//题意：每个节点有多个(>=2)的子节点，我们不知道数量
//思路： 因为每个节点的children节点是一个list，所以我们可以沿用BFS的思路，每次使用addAll()加入所有子节点
//Time: O(h*n) h is the hight level of node tree, n is 节点数量
//Space: O(n)
class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            List<Integer> currLevel = new ArrayList<>();
            int currSize = queue.size(); 

            for(int i = 0; i < currSize; i++){
                Node currNode = queue.poll(); //得到当前节点
                currLevel.add(currNode.val); //把节点值加入到当前层级的list中
                queue.addAll(currNode.children); //List<Node> _children 是个list 所以可直接加入到queue队列中
            }

            res.add(currLevel); //把每一层的节点list加入到最后的结果中    
        }

        return res;
    }
}
