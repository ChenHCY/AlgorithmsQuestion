/* 94. Binary Tree Inorder Traversal
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
class Solution {
    public static void traversal(TreeNode root, List<Integer> list) {
        if(root == null){
            return;
        }
       // Beacuse this is Inorder Traversal, 
      //so follow the 1.Visit the left node 2. Visit the root node 3. Visit the right node
        traversal(root.left, list); // 1.Visit the left node
        list.add(root.val);                 // 2. Visit the root node
        traversal(root.right, list);// 3. Visit the right node
    }
    
    public List<Integer> inorderTraversal(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<>();
    
        traversal(root, result);
        return result; // need return a Integer List
    }
}
