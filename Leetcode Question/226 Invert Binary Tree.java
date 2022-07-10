/* Leetcode 226. Invert Binary Tree
Given the root of a binary tree, invert the tree, and return its root.

Example 1:
Input: root = [4,2,7,1,3,6,9]
Output: [4,7,2,9,6,3,1]

Example 2:
Input: root = [2,1,3]
Output: [2,3,1]

Example 3:
Input: root = []
Output: []
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
    public TreeNode invertTree(TreeNode root) {
        if(root == null){
           return null;
        }
        
        //if left and right side did not have any TreeNode, return root
        if(root.left == null && root.right == null){
            return root; 
        }
        
        TreeNode left = root.left; // Define the root left side part
        TreeNode right = root.right; // Define the root right side part
        
        //change and invert the left side and right side of the Binary Tree 
        root.left = right; 
        root.right = left;
        
        //recursive the left side and right side of the Binary Tree 
        left = invertTree(root.left);
        right = invertTree(root.right);
        
        return root; // get the final result (Invert Binary Tree)      
    }
}
