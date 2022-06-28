/* 110. Balanced Binary Tree
Given a binary tree, determine if it is height-balanced.
For this problem, a height-balanced binary tree is defined as:
a binary tree in which the left and right subtrees of every node differ in height by no more than 1.

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: true

Example 2:
Input: root = [1,2,2,3,3,null,null,4,4]
Output: false

Example 3:
Input: root = []
Output: true
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
    public boolean isBalanced(TreeNode root) {
        if(root == null){
            return true;
        }
        
        int lnum = height(root.left);
        int rnum = height(root.right);
        
        int result = Math.abs(lnum - rnum); // get a Positive number 
      
      /*int resutlt = (lnum - rnum) * (lnum - rnum);
      if(result <= 1){
          return true
    }*/
           // need care whether root left and right also are balanced
        if(result <= 1 && isBalanced(root.left) && isBalanced(root.right)){ 
            return true; 
        }
        
        return false; 
    }
    
     public int height(TreeNode root) {
         if(root == null)
             return 0; 
         return Math.max(height(root.left), height(root.right)) + 1;
    }
}
