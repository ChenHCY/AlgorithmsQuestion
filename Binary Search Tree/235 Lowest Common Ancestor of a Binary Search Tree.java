/* Leetcode 235. Lowest Common Ancestor of a Binary Search Tree

Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T 
that has both p and q as descendants (where we allow a node to be a descendant of itself).”

Example 1:
Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
Output: 6
Explanation: The LCA of nodes 2 and 8 is 6.

Example 2:
Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
Output: 2
Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.

Example 3:
Input: root = [2,1], p = 2, q = 1
Output: 2
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int max = Math.max(p.val, q.val); //find the max treenode value in between p and q
        int min = Math.min(p.val, q.val); //find the min treenode value in between p and q 
        return BST(root, max, min); // call BST small part to find the lowest common ancestor
    }
    
    public TreeNode BST(TreeNode root, int max, int min){
        //Based on Binary Search Tree Rules, we can think the tree node value between the min and max value range is the lowest common ancestor (LCA) of two given nodes
        if(root.val >= min && root.val <= max){
            return root; 
        } 
        
        //if the root.val larger than max value, Based on Binary Search Tree Rules => recursive to check left side(small value part)
        if(root.val > max){
            return BST(root.left, max, min);
        }
        
        //if the root.val larger than min value, Based on Binary Search Tree Rules => recursive to check right side(large value part)
        if(root.val < min){
            return BST(root.right, max, min);
        }
        
        return null;
    }
}
