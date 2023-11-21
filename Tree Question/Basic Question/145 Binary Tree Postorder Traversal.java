/* Leetcode 145. Binary Tree Postorder Traversal

Given the root of a binary tree, return the postorder traversal of its nodes' values.

Example 1:
Input: root = [1,null,2,3]
Output: [3,2,1]

Example 2:
Input: root = []
Output: []

Example 3:
Input: root = [1]
Output: [1]
 
Constraints:
The number of the nodes in the tree is in the range [0, 100].
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
//Recursive solution ==> 类似于 DFS
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    public static void helper(TreeNode root, List<Integer> list){
        if(root == null){
            return;
        }

        helper(root.left, list);
        helper(root.right, list);
        list.add(root.val);
    }
}

// iteratively 迭代 ==> 类似于 BFS
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        //base case 
        if(root == null){
            return res;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        stack.addLast(root);

        // Deque是一个双向队列，add()从左边加入数值，pop()从左边拿数值，pollLast()从右边拿数值
        while(!stack.isEmpty()){
            TreeNode node = stack.pollLast();
            res.add(node.val);
            if(node.left != null){
                stack.add(node.left);
            }
            if(node.right != null){
                stack.add(node.right);
            }
        }

        Collections.reverse(res);
        return res;
    }
}
