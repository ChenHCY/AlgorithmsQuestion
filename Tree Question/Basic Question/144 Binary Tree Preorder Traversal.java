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
        helper(root, res); //recursion function
        return res;
    }
  
    //helper function
    public static void helper(TreeNode root, List<Integer> list){
        //exit condition
        if(root == null){
            return;
        }

        list.add(root.val); //add every node value into list
        //move to next level
        helper(root.left, list);
        helper(root.right, list);
    }
}


//Solution 2:  iteratively method (similar with BFS)
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>(); // Stack是后进先出的集合
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                res.add(node.val);
                //Stack是后进先出的集合, 所以先加右边 再加左边
                stack.push(node.right);
                stack.push(node.left);
            }
        }
        return res;
    }
}
