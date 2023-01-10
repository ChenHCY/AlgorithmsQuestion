/* Leetcode 100. Same Tree

Given the roots of two binary trees p and q, write a function to check if they are the same or not.

Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.

Example 1:
Input: p = [1,2,3], q = [1,2,3]
Output: true

Example 2:
Input: p = [1,2], q = [1,null,2]
Output: false

Example 3:
Input: p = [1,2,1], q = [1,1,2]
Output: false
 

Constraints:

The number of nodes in both trees is in the range [0, 100].
-104 <= Node.val <= 104
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

/* 解题思路：
      递归调用的方法 ==》 检查每一级的节点是否一样，和是否存在。
*/
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        return sameTree(p, q);
    }

    //small function to check every level of Tree is Same or not
    public static boolean sameTree(TreeNode p, TreeNode q){
        //base case
        if(p == null && q == null){
            return true;
        }

        if((p != null && q == null) || (p == null && q != null)){
            return false;
        }

        if(p.val != q.val){
            return false;
        }

        return sameTree(p.left, q.left) && sameTree(p.right, q.right);
    }
}
