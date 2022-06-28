/* Leetcode 111. Minimum Depth of Binary Tree

Given a binary tree, find its minimum depth.
The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
Note: A leaf is a node with no children.

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: 2

Example 2:
Input: root = [2,null,3,null,4,null,5,null,6]
Output: 5
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
    public int minDepth(TreeNode root) {
        if(root == null){
            return 0;
        } 
        
        return minHeight(root);
    }
    
    public int minHeight(TreeNode root){
        if(root == null){
            return Integer.MAX_VALUE;
        } 
        
        if(root != null){
            if(root.left == null && root.right == null){
                return 1;
            }
        }
           
        int result = 0;
        int lnum = minHeight(root.left);
        int rnum = minHeight(root.right);
        
        if(lnum == rnum){
            result = rnum + 1;
        } else{
            result = Math.min(lnum, rnum) + 1;
        }
        
        return result;
    }
}
