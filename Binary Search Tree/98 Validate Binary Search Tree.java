/* Leetcode 98. Validate Binary Search Tree
Given the root of a binary tree, determine if it is a valid binary search tree (BST).

A valid BST is defined as follows:

  a. The left subtree of a node contains only nodes with keys less than the node's key.
  b. The right subtree of a node contains only nodes with keys greater than the node's key.
  c. Both the left and right subtrees must also be binary search trees.
  
Example 1:
Input: root = [2,1,3]
Output: true

Example 2:
Input: root = [5,1,4,null,null,3,6]
Output: false
Explanation: The root node's value is 5 but its right child's value is 4.
 

Constraints:
The number of nodes in the tree is in the range [1, 104].
-231 <= Node.val <= 231 - 1
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
    public boolean isValidBST(TreeNode root) {
        long min = Long.MIN_VALUE;
        long max = Long.MAX_VALUE;

        return checkBalance(root, min, max);
    }

    //small function
    public static boolean checkBalance(TreeNode root, long min, long max){
        if(root == null){
            return true;
        }

        //if find the node value is not balanced
        // a. The left subtree of a node contains only nodes with keys less than the node's key.
        //b. The right subtree of a node contains only nodes with keys greater than the node's key.
        if(root.val <= min || root.val >= max){
            return false;
        }

        //move to next level ==>   c. Both the left and right subtrees must also be binary search trees.
        return checkBalance(root.left, min, root.val) && checkBalance(root.right, root.val, max);
    }
}
