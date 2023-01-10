/*Leetcode 236. Lowest Common Ancestor of a Binary Tree

Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q 
as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).

Example 1:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.

Example 2:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.

Example 3:

Input: root = [1,2], p = 1, q = 2
Output: 1
 
Constraints:

The number of nodes in the tree is in the range [2, 105].
-109 <= Node.val <= 109
All Node.val are unique.
p != q
p and q will exist in the tree.
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

/* 解题思路：
1. 如果 root 为 null 或者如果 root 是 p 或者如果 root 是 q 那么返回 root
2. 对左子树和右子树进行递归调用
  a. 如果左子树递归调用给出空值，这意味着我们不能在左子树中找到 p或者q， ==> LCA唯有可能存在于右子树部分
  b. 如果右子树递归调用给出空值，这意味着我们不能在右子树上找到 p或者q， ==> LCA唯有可能存在于左子树部分
3. 如果左右子树的递归调用都能得到具体的值（不为null），==> 则表示这个root一定是 Lowest Common Ancestor（LCA）
*/

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //base case
        if(root == null || root == q || root == p){
            return root;
        }
      
        //递归调用，recursion method and move to traverse every level of the Binary Tree
        //connect the every level of binary tree
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        //Final Check: check whether can find p and q from the left and right side of current Node
        if(left != null && right != null){ //if can find the p and q from left side and right side
            return root; //current root must be the Lowest Common Ancestor of a Binary Tree
        }
        
        //check whether left and right side of current node is null 
        if(left != null){ //if can find the p or q from the left side, return the left node back to the previous level of the Binary tree
            return left;
        } else { //if can find the p or q from the right side, return the right node back to the previous level of the Binary tree
            return right;
        }
    }
}
