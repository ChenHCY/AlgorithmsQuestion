/* Leetcode 144. Binary Tree Preorder Traversal
Given the root of a binary tree, return the preorder traversal of its nodes' values.

Example 1:
Input: root = [1,null,2,3]
Output: [1,2,3]

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

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

//Solution 1: recursively method (similar with DFS)
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfsRecursion(root, res); //call the recursion function
        return res;
    }

    //recursion function ==> dfs
    public static void dfsRecursion(TreeNode root, List<Integer> res){
        //exit condition
        if(root == null){
            return;
        }
     
        res.add(root.val);
        //move to next level
        dfsRecursion(root.left, res);
        dfsRecursion(root.right, res);
    }
}


//Solution 2:  iteratively method (similar with BFS)
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root); //首先加入根节点

        //主遍历，类似于BFS
        while(!stack.isEmpty()){
            TreeNode node = stack.pollLast();
            if(node != null){
             // Deque是一个双向队列，push()从左边加入数值，pop()从左边拿数值，pollLast()从右边拿数值
                res.add(node.val);
                stack.add(node.right);
                stack.add(node.left);
            }
        }

        return res;
    }
}
