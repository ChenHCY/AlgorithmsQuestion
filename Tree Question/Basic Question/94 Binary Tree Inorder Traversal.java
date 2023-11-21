/*Leetcode  94 Binary Tree Inorder Traversal

Given the root of a binary tree, return the inorder traversal of its nodes' values.

Example 1:
Input: root = [1,null,2,3]
Output: [1,3,2]

Example 2:

Input: root = []
Output: []

Example 3:

Input: root = [1]
Output: [1]
 

Constraints:

The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100
*/

//Solution 1: Recursive solution 递归 （类似于 DFS）
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfsRecursion(root, res);
        return res;
    }
    public static void dfsRecursion(TreeNode node, List<Integer> list){
        //exit condition
        if(node == null){
            return;
        }
        //中序遍历：左子数 -> 根节点 -> 右子树
        dfsRecursion(node.left, list);
        list.add(node.val);
        dfsRecursion(node.right, list);
    }
}


// Solution 2: iteratively 迭代 （类似于 BFS）
// 中序遍历顺序: 左-中-右 入栈顺序： 左-右
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode currNode = root;

        //中序遍历：从最左边的节点开始添加，左子树 -> 根节点 -> 右子树
        while(!stack.isEmpty() || currNode != null){
            // 首先找到最左边的节点，把路径上的所有非null的节点添加进stack队列中
            if(currNode != null){
                stack.addLast(currNode);
                currNode = currNode.left;
            } else{ //如果发现null节点，开始检查stack队列中的每个节点，是否存在右节点，如果有 则加入
                currNode = stack.pollLast();
                res.add(currNode.val);
                currNode = currNode.right;
            }
        }

        return res;
    }
}
